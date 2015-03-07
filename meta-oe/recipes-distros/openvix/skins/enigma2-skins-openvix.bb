SUMMARY = "Custom Skins for Enigma2"
MAINTAINER = "openvix"
PACKAGES = "${PN}-meta ${PN}"
PACKAGES_DYNAMIC = "enigma2-plugin-skins-openvix-*"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://README.md;startline=1;endline=6;md5=d87dcebda7b395f6f541992adbb03d9d"

inherit gitpkgv

PACKAGE_ARCH := "${MACHINE_ARCH}"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r5"

SRC_URI = "git://github.com/OpenViX/skins.git;protocol=git"

RCONFLICTS_enigma2-plugin-skins-openvix-vix-day-hd = "enigma2-plugin-skins-vix-day-hd"
RREPLACES_enigma2-plugin-skins-openvix-vix-day-hd = "enigma2-plugin-skins-vix-day-hd"
RCONFLICTS_enigma2-plugin-skins-openvix-vix-night-hd = "enigma2-plugin-skins-vix-night-hd"
RREPLACES_enigma2-plugin-skins-openvix-vix-night-hd = "enigma2-plugin-skins-vix-night-hd"
RCONFLICTS_enigma2-plugin-skins-openvix-magic-sd = "enigma2-plugin-skins-vix-magic-sd"
RREPLACES_enigma2-plugin-skins-openvix-magic-sd = "enigma2-plugin-skins-vix-magic-sd"
RCONFLICTS_enigma2-plugin-skins-openvix-magic-hd = "enigma2-plugin-skins-vix-magic-hd"
RREPLACES_enigma2-plugin-skins-openvix-magic-hd = "enigma2-plugin-skins-vix-magic-hd"
RCONFLICTS_enigma2-plugin-skins-openvix-magic-hd-night = "enigma2-plugin-skins-vix-magic-hd-night"
RREPLACES_enigma2-plugin-skins-openvix-magic-hd-night = "enigma2-plugin-skins-vix-magic-hd-night"
RCONFLICTS_enigma2-plugin-skins-openvix-magic-hd-noire = "enigma2-plugin-skins-vix-magic-hd-noire"
RREPLACES_enigma2-plugin-skins-openvix-magic-hd-noire = "enigma2-plugin-skins-vix-magic-hd-noire"
RCONFLICTS_enigma2-plugin-skins-openvix-vixbmc-slim-hd = "enigma2-plugin-skins-vix-vixbmc-slim-hd"
RREPLACES_enigma2-plugin-skins-openvix-vixbmc-slim-hd = "enigma2-plugin-skins-vix-vixbmc-slim-hd"
RCONFLICTS_enigma2-plugin-skins-openvix-vixbmc-night-hd = "enigma2-plugin-skins-vix-vixbmc-night-hd"
RREPLACES_enigma2-plugin-skins-openvix-vixbmc-night-hd = "enigma2-plugin-skins-vix-vixbmc-night-hd"
RCONFLICTS_enigma2-plugin-skins-openvix-vixbmc-metropolis = "enigma2-plugin-skins-vix-vixbmc-metropolis"
RREPLACES_enigma2-plugin-skins-openvix-vixbmc-metropolis = "enigma2-plugin-skins-vix-vixbmc-metropolis"
RCONFLICTS_enigma2-plugin-skins-openvix-pli-full-hd-night = "enigma2-plugin-skins-pli-full-hd-night"
RREPLACES_enigma2-plugin-skins-openvix-pli-full-hd-night = "enigma2-plugin-skins-pli-full-hd-night"
RCONFLICTS_enigma2-plugin-skins-openvix-metrixhd = "enigma2-plugin-skins-metrixhd"
RREPLACES_enigma2-plugin-skins-openvix-metrixhd = "enigma2-plugin-skins-metrixhd"
RCONFLICTS_enigma2-plugin-skins-openvix-blue-hd = "enigma2-plugin-skins-blue-hd"
RREPLACES_enigma2-plugin-skins-openvix-blue-hd = "enigma2-plugin-skins-blue-hd"
RCONFLICTS_enigma2-plugin-skins-openvix-red-hd = "enigma2-plugin-skins-red-hd"
RREPLACES_enigma2-plugin-skins-openvix-red-hd = "enigma2-plugin-skins-red-hd"

RCONFLICTS_enigma2-plugin-skins-openvix-youvix-blue = "enigma2-plugin-skins1080-youvix-blue"
RREPLACES_enigma2-plugin-skins-openvix-youvix-blue = "enigma2-plugin-skins1080-youvix-blue"
RCONFLICTS_enigma2-plugin-skins-openvix-youvix-green = "enigma2-plugin-skins1080-youvix-green"
RREPLACES_enigma2-plugin-skins-openvix-youvix-green = "enigma2-plugin-skins1080-youvix-green"
RCONFLICTS_enigma2-plugin-skins-openvix-youvix-purple = "enigma2-plugin-skins1080-youvix-purple"
RREPLACES_enigma2-plugin-skins-openvix-youvix-purple = "enigma2-plugin-skins1080-youvix-purple"
RCONFLICTS_enigma2-plugin-skins-openvix-youvix-red = "enigma2-plugin-skins1080-youvix-red"
RREPLACES_enigma2-plugin-skins-openvix-youvix-red = "enigma2-plugin-skins1080-youvix-red"
RCONFLICTS_enigma2-plugin-skins-openvix-vixbmc-1080 = "enigma2-plugin-skins1080-vixbmc-1080"
RREPLACES_enigma2-plugin-skins-openvix-vixbmc-1080 = "enigma2-plugin-skins1080-vixbmc-1080"
RCONFLICTS_enigma2-plugin-skins-openvix-vixbmc-1080-bello = "enigma2-plugin-skins1080-vixbmc-1080-bello"
RREPLACES_enigma2-plugin-skins-openvix-vixbmc-1080-bello = "enigma2-plugin-skins1080-vixbmc-1080-bello"
RCONFLICTS_enigma2-plugin-skins-openvix-metrixfhd = "enigma2-plugin-skins1080-metrixhd-1080"
RREPLACES_enigma2-plugin-skins-openvix-metrixfhd = "enigma2-plugin-skins1080-metrixhd-1080"
RCONFLICTS_enigma2-plugin-skins-openvix-mynovum-fhd2-black = "enigma2-plugin-skins1080-mynovum-fhd2-black-1080"
RREPLACES_enigma2-plugin-skins-openvix-mynovum-fhd2-black = "enigma2-plugin-skins1080-mynovum-fhd2-black-1080"
RCONFLICTS_enigma2-plugin-skins-openvix-mynovum-fhd2 = "enigma2-plugin-skins1080-mynovum-fhd2-1080"
RREPLACES_enigma2-plugin-skins-openvix-mynovum-fhd2 = "enigma2-plugin-skins1080-mynovum-fhd2-1080"
RCONFLICTS_enigma2-plugin-skins-openvix-novum-fhd-slim = "enigma2-plugin-skins1080-novum-fhd-slim-1080"
RREPLACES_enigma2-plugin-skins-openvix-novum-fhd-slim = "enigma2-plugin-skins1080-novum-fhd-slim-1080"


# note that enigma2-skins is just an empty package to satisfy silly dependencies.
ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = "${datadir}/enigma2"
FILES_${PN}-meta = "${datadir}/meta"
RDEPENDS_${PN}-meta = ""

inherit autotools-brokensep

S = "${WORKDIR}/git"

EXTRA_OECONF += "\
    ${@base_contains("MACHINE_FEATURES", "skins1080", "--with-skins1080" , "", d)} \
    "

python populate_packages_prepend() {
    enigma2_skindir = bb.data.expand('${datadir}/enigma2', d)
    do_split_packages(d, enigma2_skindir, '(.*?)/.*', 'enigma2-plugin-skins-openvix-%s', 'Enigma2 Skin Pack: %s', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', '%s (source files)', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    enigma2_convdir = bb.data.expand('${libdir}/enigma2/python/Components/Converter', d)
    do_split_packages(d, enigma2_convdir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-convertor-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_convdir, '^(\w+/\w+)/.*\.py$', 'enigma2-convertor-%s-src', '%s (source files)', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
}