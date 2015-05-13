SUMMARY = "XBMC Media Center"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=930e2a5f63425d8dd72dbd7391c43c46"

#DEPENDS = "libusb1 libcec libplist expat yajl gperf-native libxmu fribidi mpeg2dec ffmpeg samba fontconfig curl python libass libmodplug libmicrohttpd wavpack libmms cmake-native libsdl-image libsdl-mixer virtual/egl mysql5 sqlite3 libmms faad2 libcdio libpcre boost lzo enca avahi libsamplerate0 libxinerama libxrandr libxtst bzip2 virtual/libsdl jasper zip-native zlib libtinyxml libmad"
DEPENDS = "libvorbis libusb1 libcec libplist expat yajl gperf-native libxmu fribidi mpeg2dec ffmpeg samba fontconfig curl python libass libmodplug libmicrohttpd wavpack libmms cmake-native libsdl-image libsdl-mixer mysql5 sqlite3 libmms faad2 libcdio libpcre boost lzo enca avahi libsamplerate0 libxinerama libxrandr libxtst bzip2 virtual/libsdl jasper zip-native zlib libtinyxml libmad"

#require recipes/egl/egl.inc

#SRCREV = "7cc53a9a3da77869d1d5d3d3d9971b4bd1641b50"
SRCREV = "${AUTOREV}"

# multiple issues
PNBLACKLIST[xbmc] ?= "/usr/include/c++/ctime:70:11: error: '::gmtime' has not been declared"

PV = "auto+helix+gitr${SRCPV}"
PR = "r14"

SRC_URI = "git://github.com/xbmc/xbmc.git;branch=Helix \
           file://0001-configure-don-t-run-python-distutils-to-find-STAGING.patch \
           file://configure.in-helix.patch \
"

inherit autotools gettext python-dir

S = "${WORKDIR}/git"

# breaks compilation
CCACHE = ""

CACHED_CONFIGUREVARS += " \
    ac_cv_path_PYTHON="${STAGING_BINDIR_NATIVE}/python-native/python" \
"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'opengl', 'opengl', 'openglesv2', d)}"
PACKAGECONFIG[opengl] = "--enable-gl,--enable-gles,glew"
PACKAGECONFIG[openglesv2] = "--enable-gles,--enable-gl,"

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

EXTRA_OECONF = " \
	--with-arch=${TARGET_ARCH} \
	--disable-rpath \
	--disable-gles \
	--enable-libusb \
	--enable-airplay \
	--disable-optical-drive \
	--disable-ssh \
	--disable-x11 \
	--enable-sdl \
	--disable-joystick \
	--disable-alsa \
	--disable-libcec \
	--enable-rtmp	\
	--enable-external-libraries \
	--enable-external_ffmpeg \
	--disable-gnutls \
	--disable-texturepacker \
	--with-platform=dvbbox \
"

RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-ibm850 glibc-gconv-ibm850"

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export PYTHON_DIR

EXTRA_OECONF_FFMPEG = " \
        --enable-shared \
        --enable-pthreads \
        --disable-stripping \
        --enable-gpl \
        --enable-nonfree \
        --enable-postproc \
        \
        --cross-prefix=${TARGET_PREFIX} \
        --prefix=${prefix} \
        \
        --arch=${TARGET_ARCH} \
        --target-os=linux \
        --host-os=x86_64 \
        --pkg-config="pkg-config" \
        --enable-cross-compile \
        --disable-shared --enable-static \
"

FFMPEG_CONFIGURE  = "--disable-static --enable-shared --enable-small --disable-runtime-cpudetect"
FFMPEG_CONFIGURE += "--disable-ffserver --disable-ffplay --disable-ffprobe"
FFMPEG_CONFIGURE += "--disable-doc --disable-htmlpages --disable-manpages --disable-podpages --disable-txtpages"
FFMPEG_CONFIGURE += "--disable-asm --disable-altivec --disable-amd3dnow --disable-amd3dnowext --disable-mmx --disable-mmxext"
FFMPEG_CONFIGURE += "--disable-sse --disable-sse2 --disable-sse3 --disable-ssse3 --disable-sse4 --disable-sse42 --disable-avx --disable-fma4"
#FFMPEG_CONFIGURE += "--disable-armv5te --disable-armv6 --disable-armv6t2 --disable-vfp --disable-neon --disable-vis --disable-inline-asm"
FFMPEG_CONFIGURE += "--disable-armv5te --disable-armv6 --disable-armv6t2 --disable-vfp --disable-neon --disable-inline-asm"

FFMPEG_CONFIGURE += "--disable-yasm --disable-mips32r2 --disable-mipsdspr1 --disable-mipsdspr2 --disable-mipsfpu --disable-fast-unaligned"
FFMPEG_CONFIGURE += "--disable-muxers"
FFMPEG_CONFIGURE += "--enable-muxer=flac --enable-muxer=mp3 --enable-muxer=h261 --enable-muxer=h263 --enable-muxer=h264"
FFMPEG_CONFIGURE += "--enable-muxer=image2 --enable-muxer=mpeg1video --enable-muxer=mpeg2video --enable-muxer=ogg"
FFMPEG_CONFIGURE += "--disable-encoders"
FFMPEG_CONFIGURE += "--enable-encoder=aac --enable-encoder=h261 --enable-encoder=h263 --enable-encoder=h263p --enable-encoder=ljpeg"
FFMPEG_CONFIGURE += "--enable-encoder=mjpeg --enable-encoder=mpeg1video --enable-encoder=mpeg2video --enable-encoder=png"
FFMPEG_CONFIGURE += "--disable-decoders"
FFMPEG_CONFIGURE += "--enable-decoder=aac --enable-decoder=dvbsub --enable-decoder=dvdsub --enable-decoder=flac --enable-decoder=h261 --enable-decoder=h263"
FFMPEG_CONFIGURE += "--enable-decoder=h263i --enable-decoder=h264 --enable-decoder=iff_byterun1 --enable-decoder=mjpeg"
FFMPEG_CONFIGURE += "--enable-decoder=mp3 --enable-decoder=mpeg1video --enable-decoder=mpeg2video --enable-decoder=png"
FFMPEG_CONFIGURE += "--enable-decoder=theora --enable-decoder=vorbis --enable-decoder=wmv3 --enable-decoder=pcm_s16le"
FFMPEG_CONFIGURE += "--enable-decoder=rawvideo --enable-decoder=wmapro --enable-decoder=wmav1 --enable-decoder=wmav2 --enable-decoder=wmavoice"
FFMPEG_CONFIGURE += "--enable-decoder=iff_byterun1 --enable-decoder=ra_144 --enable-decoder=ra_288"
FFMPEG_CONFIGURE += "--enable-demuxer=mjpeg --enable-demuxer=wav --enable-demuxer=rtsp"
FFMPEG_CONFIGURE += "--enable-parser=mjpeg"
FFMPEG_CONFIGURE += "--disable-indevs --disable-outdevs --disable-bsfs --disable-debug"
FFMPEG_CONFIGURE += "--enable-pthreads --enable-bzlib --enable-zlib --enable-librtmp --enable-stripping"

# configuration settings
#ffmpg_config = "--prefix=$(PREFIX) --extra-version="xbmc-$(VERSION)"
#ffmpg_config += "--cc=$(CC) --cxx=$(CXX)"
ffmpg_config = "--disable-devices --disable-doc"
ffmpg_config += "--disable-ffplay --disable-ffmpeg"
ffmpg_config += "--disable-ffprobe --disable-ffserver"
ffmpg_config += "--enable-gpl --enable-runtime-cpudetect"
ffmpg_config += "--enable-postproc --enable-pthreads"
ffmpg_config += "--enable-muxer=spdif --enable-muxer=adts"
ffmpg_config += "--enable-muxer=asf --enable-muxer=ipod"
ffmpg_config += "--enable-encoder=ac3 --enable-encoder=aac"
ffmpg_config += "--enable-encoder=wmav2 --enable-protocol=http"
#  ffmpg_config += "--arch=$(CPU) --enable-cross-compile"
#  ffmpg_config += "--target-os=$(OS) --cpu=$(CPU)"
ffmpg_config += "--enable-vdpau --enable-vaapi --enable-gnutls"
ffmpg_config += "--enable-libvorbis --enable-muxer=ogg --enable-encoder=libvorbis"
ffmpg_config += "--disable-mips32r2 --disable-mipsdspr1 --disable-mipsdspr2"
#  ffmpg_config += "--disable-debug"


EXTRA_OECONF_FFMPEG = " \
        \
        --cross-prefix=${TARGET_PREFIX} \
        --prefix=${prefix}/ \
        \
        --extra-cflags="--sysroot=${STAGING_DIR_TARGET}" \
        --extra-ldflags="--sysroot=${STAGING_DIR_TARGET}" \
        --enable-hardcoded-tables \
		--pkg-config="pkg-config" \
		--target-os=linux \
		--arch=${TARGET_ARCH} \
		--enable-postproc \
        ${EXTRA_FFCONF} \
"

do_configure() {
    ${S}/tools/depends/target/ffmpeg/autobuild.sh -d --arch=${TARGET_ARCH} --prefix=${S}/tools/depends/target/ffmpeg/ffmpeg-2.4.6-Helix
	tar -zxvf ${S}/tools/depends/target/ffmpeg/ffmpeg-2.4.6-Helix.tar.gz -C ${S}/tools/depends/target/ffmpeg
	cd ${S}/tools/depends/target/ffmpeg/FFmpeg-2.4.6-Helix
	${S}/tools/depends/target/ffmpeg/FFmpeg-2.4.6-Helix/configure ${EXTRA_OECONF_FFMPEG} ${ffmpg_config}
	make -j 8
	make install DESTDIR=${STAGING_DIR_TARGET}
    cd ${S}

#via root
#cd /home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihdp/tmp/work/mips32el-oe-linux/titan-xbmc/helix+gitrAUTOINC+7cc53a9a3d-r14/git/tools/depends/native
#git clean -xfd
#/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/work-shared/gcc-4.9.1-r0/gcc-4.9.1/configure --build=x86_64-linux --host=x86_64-linux --target=mipsel-oe-linux --prefix=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr --exec_prefix=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr --bindir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/bin/mipsel-oe-linux --sbindir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/bin/mipsel-oe-linux --libexecdir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/libexec/mipsel-oe-linux --datadir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/share --sysconfdir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/etc --sharedstatedir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/com --localstatedir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/var --libdir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/lib/mipsel-oe-linux --includedir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/include --oldincludedir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/include --infodir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/share/info --mandir=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr/share/man --disable-silent-rules --disable-dependency-tracking --with-libtool-sysroot=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux --enable-clocale=generic --with-gnu-ld --enable-shared --enable-languages=c,c++ --enable-threads=posix --disable-multilib --enable-c99 --enable-long-long --enable-symvers=gnu --enable-libstdcxx-pch --program-prefix=mipsel-oe-linux- --without-local-prefix --enable-target-optspace --enable-lto --enable-libssp --disable-bootstrap --disable-libmudflap --with-system-zlib --with-linker-hash-style=sysv --enable-linker-build-id --with-ppl=no --with-cloog=no --enable-checking=release --enable-cheaders=c_global --with-gxx-include-dir=/not/exist/usr/include/c++/4.9.1 --with-sysroot=/not/exist --with-build-sysroot=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/inihde --enable-poison-system-directories --with-mpfr=/home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihde/tmp/sysroots/x86_64-linux/usr --with-system-zlib --disable-nls --enable-__cxa_atexit
#make -C /home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihdp/tmp/work/mips32el-oe-linux/titan-xbmc/helix+gitrAUTOINC+7cc53a9a3d-r14/git/tools/depends/native/JsonSchemaBuilder/

#    mkdir ${S}/tools/depends/native/JsonSchemaBuilder/bin/
#    ln -s /bin/JsonSchemaBuilder ${S}/tools/depends/native/JsonSchemaBuilder/bin/
#    touch ${S}/tools/depends/native/JsonSchemaBuilder/bin/.installed-native

#    mkdir /home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihdp/tmp/work/mips32el-oe-linux/titan-xbmc/helix+gitrAUTOINC+7cc53a9a3d-r14/git/tools/depends/native/JsonSchemaBuilder/bin/
#    ln -s /bin/JsonSchemaBuilder /home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihdp/tmp/work/mips32el-oe-linux/titan-xbmc/helix+gitrAUTOINC+7cc53a9a3d-r14/git/tools/depends/native/JsonSchemaBuilder/bin/
#	 touch /home/aaf-svn/flashimg/BUILDGIT/checkout_mips360/builds/titannit/inihdp/tmp/work/mips32el-oe-linux/titan-xbmc/helix+gitrAUTOINC+7cc53a9a3d-r14/git/tools/depends/native/JsonSchemaBuilder/bin/.installed-native
    ./bootstrap
    oe_runconf
}

PARALLEL_MAKE = " "

do_compile_prepend() {
    for i in $(find . -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' $i
    done

    for i in $(find . -name "*.mak*" -o    -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir):-rpath ${libdir}:g' $i
    done
}

INSANE_SKIP_${PN} = "rpaths"

do_install_prepend(){
    cd ${S}
}

do_compile_prepend(){
    cd ${S}
}

# on ARM architectures xbmc will use GLES which will make the regular wrapper fail, so start it directly
do_install_append_arm() {
    sed -i -e 's:Exec=xbmc:Exec=${libdir}/xbmc/xbmc.bin:g' ${D}${datadir}/applications/xbmc.desktop
}

FILES_${PN} += "${datadir}/xsessions ${datadir}/icons"
FILES_${PN}-dbg += "${libdir}/xbmc/.debug ${libdir}/xbmc/*/.debug ${libdir}/xbmc/*/*/.debug ${libdir}/xbmc/*/*/*/.debug"

# xbmc uses some kind of dlopen() method for libcec so we need to add it manually
# OpenGL builds need glxinfo, that's in mesa-demos
RRECOMMENDS_${PN}_append = " libcec \
                             python \
                             python-lang \
                             python-re \
                             python-netclient \
                             libcurl \
                             xdpyinfo \
                             ${@base_contains('DISTRO_FEATURES', 'opengl', 'mesa-demos', '', d)} \
"
RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-ibm850 glibc-gconv-ibm850"