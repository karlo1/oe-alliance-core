SUMMARY = "TitanNit is a fast Linux Framebuffer Gui"
MAINTAINER = "TitanNit Team"
SECTION = "multimedia"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/license-gplv2.inc

SRCREV = "${AUTOREV}"
PV = "2.0+svnr${SRCPV}"
PR = "r1"

SRC_URI = "svn://sbnc.dyndns.tv/svn/;module=titan;protocol=http"

DEPENDS = "libpng jpeg rtmpdump gstreamer gst-plugins-base gst-plugins-good gst-plugins-bad gst-plugins-ugly gst-plugin-subsink libdreamdvd"

S = "${WORKDIR}/titan"

CFLAGS = "\
	-I${STAGING_DIR_TARGET}/usr/include \
	-I${STAGING_DIR_TARGET}/usr/include/gstreamer-0.10 \
	-I${STAGING_DIR_TARGET}/usr/include/glib-2.0 \
	-I${STAGING_DIR_TARGET}/usr/include/libxml2 \
	-I${STAGING_DIR_TARGET}/usr/lib/glib-2.0/include \
	-I${STAGING_DIR_TARGET}/usr/include/freetype2 \
	-I${STAGING_DIR_TARGET}/usr/include/dreamdvd \
	-I${STAGING_DIR_TARGET}/usr/include/libdreamdvd \	
	-I${WORKDIR}/titan/libdreamdvd \
	-I${WORKDIR}/titan/titan"

do_compile() {
	cd ${WORKDIR}/titan/titan

	SVNVERSION=`svn info http://sbnc.dyndns.tv/svn/titan | grep Revision | sed s/'Revision: '//g`
	echo SVNVERSION: ${SVNVERSION}

    svn update
	cp Makefile.am.mipsel Makefile.am

	libtoolize --force
	aclocal -I ${STAGING_DIR_TARGET}/usr/share/aclocal
	autoconf
	automake --foreign --add-missing
	./configure --host=${HOST_SYS} --build=${BUILD_SYS}

	make -f Makefile titan
    ${STRIP} titan
}

FILES_${PN} = "/usr/local/bin"

do_install_append() {
    install -d ${D}/usr/local/bin
    install -m 0755 titan/titan ${D}/usr/local/bin/titan   
}
