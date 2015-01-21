DESCRIPTION="Gmediarender DLNA Renderer"
MAINTAINER = "TitanNit Developer"
LICENSE = "GPLv2"
require conf/license/license-gplv2.inc

DEPENDS = "libupnp glib-2.0 gstreamer gst-plugins-base gst-plugins-good gst-plugins-bad gst-plugins-ugly gst-plugin-subsink libdreamdvd"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "2.0+gitr${SRCPV}"
PR = "r1"
PR = "r2"

SRC_URI="git://github.com/hzeller/gmrender-resurrect.git;protocol=https"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
