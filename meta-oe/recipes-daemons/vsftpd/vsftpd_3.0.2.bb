SUMMARY = "Very Secure FTP server"
HOMEPAGE = "https://security.appspot.com/vsftpd.html"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6067ad950b28336613aed9dd47b1271"
PR = "r3"

DEPENDS = "libcap openssl"

SRC_URI = "https://security.appspot.com/downloads/vsftpd-${PV}.tar.gz \
           file://makefile-destdir.patch \
           file://makefile-libs.patch \
           file://makefile-strip.patch \
           file://vsftpd.conf \
           file://vsftpd.ftpusers \
           file://login-blank-password.patch \
"


LIC_FILES_CHKSUM = "file://COPYING;md5=a6067ad950b28336613aed9dd47b1271 \
                        file://COPYRIGHT;md5=04251b2eb0f298dae376d92454f6f72e \
                        file://LICENSE;md5=654df2042d44b8cac8a5654fc5be63eb"
SRC_URI[md5sum] = "8b00c749719089401315bd3c44dddbb2"
SRC_URI[sha256sum] = "be46f0e2c5528fe021fafc8dab1ecfea0c1f183063a06977f8537fcd0b195e56"

PACKAGECONFIG ??= ""
PACKAGECONFIG[tcp-wrappers] = ",,tcp-wrappers"
SRC_URI +="${@base_contains('PACKAGECONFIG', 'tcp-wrappers', 'file://vsftpd-tcp_wrappers-support.patch', '', d)}"

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"
RDEPENDS_${PN} += "${@base_contains('DISTRO_FEATURES', 'pam', 'pam-plugin-listfile', '', d)}"
PAMLIB = "${@base_contains('DISTRO_FEATURES', 'pam', '-L${STAGING_BASELIBDIR} -lpam', '', d)}"
NOPAM_SRC ="${@base_contains('PACKAGECONFIG', 'tcp-wrappers', 'file://nopam-with-tcp_wrappers.patch', 'file://nopam.patch', d)}"
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'pam', '', '${NOPAM_SRC}', d)}"

CONFFILES_${PN} = "${sysconfdir}/vsftpd.conf ${sysconfdir}/vsftpd.ftpusers"
LDFLAGS_append =" -lcrypt -lcap"

do_configure() {
    # Fix hardcoded /usr, /etc, /var mess.
    cat tunables.c|sed s:\"/usr:\"${prefix}:g|sed s:\"/var:\"${localstatedir}:g \
    |sed s:\"${prefix}/share/empty:\"${localstatedir}/share/empty:g |sed s:\"/etc:\"${sysconfdir}:g > tunables.c.new
    mv tunables.c.new tunables.c
}

do_compile() {
   oe_runmake "LIBS=-L${STAGING_LIBDIR} -lcrypt -lcap ${PAMLIB}"
}

do_install() {
    install -d ${D}${sbindir}
    install -d ${D}${mandir}/man8
    install -d ${D}${mandir}/man5
    oe_runmake 'DESTDIR=${D}' install
    install -d ${D}${sysconfdir}
    install -m 600 ${WORKDIR}/vsftpd.conf ${D}${sysconfdir}/vsftpd.conf
    install -m 600 ${WORKDIR}/vsftpd.ftpusers ${D}${sysconfdir}/vsftpd.ftpusers
    if ! test -z ${PAMLIB} ; then
        install -d ${D}${sysconfdir}/pam.d/
        cp ${S}/RedHat/vsftpd.pam ${D}${sysconfdir}/pam.d/vsftpd
        sed -i "s:/lib/security:${base_libdir}/security:" ${D}${sysconfdir}/pam.d/vsftpd
        sed -i "s:ftpusers:vsftpd.ftpusers:" ${D}${sysconfdir}/pam.d/vsftpd
    fi
	install -d ${D}${localstatedir}/share/empty
}
