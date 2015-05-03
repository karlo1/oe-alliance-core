SUMMARY = "Hardware drivers for ${MACHINEBUILD}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINEBUILD}"
require conf/license/license-close.inc

KV = "3.14.2"
SRCDATE = "20150304"

PV = "${KV}+${SRCDATE}"
PR = "r1"

SRC_URI[md5sum] = "656ac495bf0f7b7345a828769a3c05b1"
SRC_URI[sha256sum] = "56b7f21e90dccb6adc59aaeccb9d1a720a5c84e379416351474adc6560477971"

SRC_URI = "http://code-ini.com/software/drivers/ini-442sv-drivers-${KV}-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

do_compile() {
}

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    install -d ${D}/${sysconfdir}/modules-load.d
    for i in dvb; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}/lib/modules/${KV}/extra/dvb_${MACHINEBUILD}.ko
        echo dvb_${MACHINEBUILD} >> ${D}/${sysconfdir}/modules-load.d/_${MACHINEBUILD}.conf
    done
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINEBUILD}.conf"
