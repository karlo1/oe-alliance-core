SUMMARY = "TuxTerm"
MAINTAINER = "Sven Karschewski <seddi@i-have-a-dreambox.com>"
DEPENDS = "freetype"

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=7968df00b3e6be507316ed9cfc8be290"

PN = "tuxterm"
#PV = "0.2+svn${SRCPV}"
PV = "0.2+git${SRCPV}"

PR = "r1"

#SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/external;module=${PN}"
SRC_URI = "git://github.com/sklnet/tuxterm.git;protocol=git"

#S = "${WORKDIR}/${PN}"
S = "${WORKDIR}/git"

inherit autotools

FILES_${PN} = "/"

EXTRA_OECONF = "${@base_contains("MACHINE_FEATURES", "32bpp", "--with-bpp=32" , "--with-bpp=8", d)}"
