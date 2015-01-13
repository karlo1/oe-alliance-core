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
	titannit-version-info \
    curl \
	titannit-bootlogo \
    rtmpdump \
    gstreamer \
    gst-plugins-base \
    gst-plugins-good \
    gst-plugins-bad \
    gst-plugins-ugly \
    gst-plugin-subsink \
    ethtool \
    ${@base_contains("TARGET_ARCH", "mipsel", "gst-plugin-libxt" , "", d)} \
    libdreamdvd \
    openssl \
    tuxtxt-enigma2 \
    ${@base_contains("TARGET_ARCH", "sh4", "libmmeimage " , "", d)} \
    ${@base_contains("MACHINE_FEATURES", "dreambox", "", "ofgwrite", d)} \	
    bash \
    glib-networking \
    libcrypto-compat-0.9.7 \
    libcrypto-compat-0.9.8 \
    procps \
    ntfs-3g \
    zip \
    hddtemp \
    minidlna \
    autofs \
    djmount \
    parted \
    ${@base_contains("MACHINE_FEATURES", "iniwol", "ini-coldboot ini-ethwol", "", d)} \
    ${@base_contains("MACHINE_FEATURES", "no-nmap", "" , "nmap", d)} \
    ${@base_contains("TARGET_ARCH", "sh4", "alsa-utils-amixer-conf" , "", d)} \
    avahi-daemon \
    dropbear \
    early-configure \
    e2fsprogs-mke2fs \
    e2fsprogs-e2fsck \
    e2fsprogs-tune2fs \
    fakelocale \
    libavahi-client \
    libcrypto-compat-0.9.8 \
    modutils-loadscript \
    ntpdate \
    opkg \
    sdparm \
    curlftpfs \
    packagegroup-core-boot \
    tzdata tzdata-europe tzdata-australia tzdata-asia tzdata-pacific tzdata-africa tzdata-americas \
    util-linux-sfdisk \
    util-linux-blkid \
    volatile-media \
    vsftpd \
    titan-bin \
    titan-plugins \
    ${@base_contains("MACHINE_FEATURES", "dvbc-only", "", "", d)} \
    ${@base_contains("MACHINE_FEATURES", "singlecore", "", \
    " \
    packagegroup-base-smbfs \
    packagegroup-base-nfs \
    ", d)} \
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
