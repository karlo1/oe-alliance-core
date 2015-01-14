SUMMARY = "TitanNit Image"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "TitanNit team"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "r${DATETIME}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

#    ${@base_contains("MACHINE_FEATURES", "dvbc-only", "", "enigma2-plugin-settings-defaultsat", d)}

IMAGE_INSTALL = "\
    alsa-conf \
    autofs \
    avahi-daemon \
    bash \
    curl \
    curlftpfs \
    djmount \
    dropbear \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    early-configure \
    ethtool \
    fakelocale \
    fuse-exfat \
    gettext-native \
    glibc-gconv-iso8859-15 \
    glib-networking \
    gst-ffmpeg \
    gst-plugins-bad \
    gst-plugins-bad-cdxaparse \
    gst-plugins-bad-faad \
    gst-plugins-bad-fragmented \
    gst-plugins-bad-h264parse \
    gst-plugins-bad-mms \
    gst-plugins-bad-mpegdemux \
    gst-plugins-bad-rtmp \
    gst-plugins-bad-vcdsrc \
    gst-plugins-base \
    gst-plugins-base-alsa \
    gst-plugins-base-app \
    gst-plugins-base-audioconvert \
    gst-plugins-base-audioresample \
    gst-plugins-base-decodebin \
    gst-plugins-base-decodebin2 \
    gst-plugins-base-ivorbisdec \
    gst-plugins-base-ogg \
    gst-plugins-base-playbin \
    gst-plugins-base-subparse \
    gst-plugins-base-typefindfunctions \
    gst-plugins-base-vorbis \
    gst-plugins-good \
    gst-plugins-good-apetag \
    gst-plugins-good-audioparsers \
    gst-plugins-good-autodetect \
    gst-plugins-good-avi \
    gst-plugins-good-flac \
    gst-plugins-good-flv \
    gst-plugins-good-icydemux \
    gst-plugins-good-id3demux \
    gst-plugins-good-isomp4 \
    gst-plugins-good-matroska \
    gst-plugins-good-rtp \
    gst-plugins-good-rtpmanager \
    gst-plugins-good-rtsp \
    gst-plugins-good-souphttpsrc \
    gst-plugins-good-udp \
    gst-plugins-good-wavparse \
    gst-plugin-subsink \
    gst-plugins-ugly \
    gst-plugins-ugly-amrnb \
    gst-plugins-ugly-amrwbdec \
    gst-plugins-ugly-asf \
    gst-plugins-ugly-cdio \
    gst-plugins-ugly-dvdsub \
    gst-plugins-ugly-mad \
    gst-plugins-ugly-mpegaudioparse \
    gst-plugins-ugly-mpegstream \
    gstreamer \
    hddtemp \
    kernel-module-usbserial \
    kernel-module-ftdi-sio \
    kernel-module-pl2303 \
    kernel-module-belkin-sa \
    kernel-module-keyspan \
    libavahi-client \
    libcrypto-compat-0.9.7 \
    libcrypto-compat-0.9.8 \
    libdreamdvd \
    libdvdcss \
    minidlna \
    mjpegtools \
    module-init-tools-depmod \
    modutils-loadscript \
    mtd-utils \
    ntfs-3g \
    ntpdate \
    openssl \
    opkg \
    packagegroup-core-boot \
    parted \
    pngquant \
    procps \
    rtmpdump \
    sdparm \
    strace \
    titannit-bootlogo \
    titannit-version-info \
    tuxtxt-enigma2 \
    tzdata tzdata-europe tzdata-australia tzdata-asia tzdata-pacific tzdata-africa tzdata-americas \
    util-linux-blkid \
    util-linux-sfdisk \
    volatile-media \
    vsftpd \
    wakelan \
    wireless-tools \
    wpa-supplicant \
    zip \
    ${@base_conditional('MACHINE', 'dm800', '', 'mtd-utils-ubifs', d)} \
    ${@base_contains("MACHINE_FEATURES", "dreambox", "", "ofgwrite", d)} \
    ${@base_contains("MACHINE_FEATURES", "dvbc-only", "", "", d)} \
    ${@base_contains("MACHINE_FEATURES", "iniwol", "ini-coldboot ini-ethwol", "", d)} \
    ${@base_contains("MACHINE_FEATURES", "libpassthrough", "libpassthrough libdlsym", "", d)} \
    ${@base_contains("MACHINE_FEATURES", "no-nmap", "" , "nmap", d)} \
    ${@base_contains("MACHINE_FEATURES", "singlecore", "", \
    " \
    packagegroup-base-smbfs \
    packagegroup-base-nfs \
    ", d)} \
    ${@base_contains("TARGET_ARCH", "mipsel", "gst-plugin-libxt" , "", d)} \
    ${@base_contains("TARGET_ARCH", "sh4", "alsa-utils-amixer-conf" , "", d)} \
    ${@base_contains("TARGET_ARCH", "sh4", "libmmeimage " , "", d)} \
    titan-bin \
    titan-plugins \
    "

export IMAGE_BASENAME = "titannit-image"
IMAGE_LINGUAS = ""

IMAGE_FEATURES += "package-management"

inherit image

rootfs_postprocess() {
    curdir=$PWD
    cd ${IMAGE_ROOTFS}

    # because we're so used to it
    ln -s opkg usr/bin/ipkg || true
    ln -s opkg-cl usr/bin/ipkg-cl || true

    cd $curdir
}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_postprocess; "

export NFO = '${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.nfo'

do_generate_nfo() {
    VER=`grep Version: "${IMAGE_ROOTFS}/etc/version"`
    echo "TitanNit: ${VER}" > ${NFO}
    echo "Machine: ${MACHINE}" >> ${NFO}
    DATE=`date +%Y-%m-%d' '%H':'%M`
    echo "Date: ${DATE}" >> ${NFO}
    echo "Issuer: openATV" >> ${NFO}
    echo "Link: ${DISTRO_FEED_URI}" >> ${NFO}
    if [ "${DESC}" != "" ]; then
            echo "Description: ${DESC}" >> ${NFO}
            echo "${DESC}" >> ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.desc
    fi
    MD5SUM=`md5sum ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.nfi | cut -b 1-32`
    echo "MD5: ${MD5SUM}" >> ${NFO}
}

addtask generate_nfo after do_rootfs
