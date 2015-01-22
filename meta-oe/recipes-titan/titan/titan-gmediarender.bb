DESCRIPTION="Gmediarender DLNA Renderer"
MAINTAINER = "TitanNit Developer"
LICENSE = "GPLv2"
require conf/license/license-gplv2.inc

DEPENDS = " \
	libupnp \
	glib-2.0 \
	gstreamer1.0-plugins-base \
	gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-ugly \
	gstreamer1.0 \
	"

RRECOMMENDS_${PN} = " \
    glib-networking \
    gstreamer1.0-plugin-subsink \
	gstreamer1.0-plugin-dvbmediasink \
    ${@base_contains("TARGET_ARCH", "mipsel", "gst-plugin-libxt" , "", d)} \
    ${GST_BASE_RDEPS} \
    ${GST_GOOD_RDEPS} \
    ${GST_BAD_RDEPS} \
    ${GST_UGLY_RDEPS} \
    "

GST_BASE_RDEPS = " \
    gstreamer1.0-plugins-base-alsa \
    gstreamer1.0-plugins-base-app \
    gstreamer1.0-plugins-base-audioconvert \
    gstreamer1.0-plugins-base-audioresample \
    gstreamer1.0-plugins-base-ivorbisdec \
    gstreamer1.0-plugins-base-ogg \
    gstreamer1.0-plugins-base-playback \
    gstreamer1.0-plugins-base-subparse \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-base-vorbis \
    "

GST_GOOD_RDEPS = " \
    gstreamer1.0-plugins-good-apetag \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-good-autodetect \
    gstreamer1.0-plugins-good-avi \
    gstreamer1.0-plugins-good-flac \
    gstreamer1.0-plugins-good-flv \
    gstreamer1.0-plugins-good-icydemux \
    gstreamer1.0-plugins-good-id3demux \
    gstreamer1.0-plugins-good-isomp4 \
    gstreamer1.0-plugins-good-matroska \
    gstreamer1.0-plugins-good-rtp \
    gstreamer1.0-plugins-good-rtpmanager \
    gstreamer1.0-plugins-good-rtsp \
    gstreamer1.0-plugins-good-souphttpsrc \
    gstreamer1.0-plugins-good-udp \
    gstreamer1.0-plugins-good-wavparse \
    "

GST_BAD_RDEPS = " \
    gstreamer1.0-plugins-bad-cdxaparse \
    gstreamer1.0-plugins-bad-mms \
    gstreamer1.0-plugins-bad-mpegpsdemux \
    gstreamer1.0-plugins-bad-mpegtsdemux \
    gstreamer1.0-plugins-bad-rtmp \
    gstreamer1.0-plugins-bad-faad \
    gstreamer1.0-plugins-bad-fragmented \
    gstreamer1.0-plugins-bad-videoparsersbad \
    "

GST_UGLY_RDEPS = " \
    gstreamer1.0-plugins-ugly-amrnb \
    gstreamer1.0-plugins-ugly-amrwbdec \
    gstreamer1.0-plugins-ugly-asf \
    gstreamer1.0-plugins-ugly-cdio \
    gstreamer1.0-plugins-ugly-dvdsub \
    gstreamer1.0-plugins-ugly-mad \
    "

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "2.0+gitr${SRCPV}"
PR = "r1"
PR = "r2"

SRC_URI="git://github.com/hzeller/gmrender-resurrect.git;protocol=https"

SRC_URI += " \
		file://gmediarener.picture.patch \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN} = "/usr/bin"
FILES_${PN} += "/usr/share/gmediarender"
