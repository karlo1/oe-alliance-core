require directfb.inc

RV = "1.7.7"
PR = "r2"

DEPENDS += "sysfsutils mesa"

EXTRA_OECONF = "\
  --enable-freetype=yes \
  --enable-zlib \
  --disable-sdl \
  --disable-vnc \
  --disable-png \
  --disable-gif \
  --enable-fbdev=yes \
  --disable-freetype \
  --disable-x11 \
  --enable-mesa \
  --enable-debug \
  --with-gfxdrivers='gles2' \
  --enable-idirectfbgl-egl \
  "
#  --disable-fusion
#  --enable-egl

LEAD_SONAME = "libdirectfb-1.7.so.7"

SRC_URI[md5sum] = "152cf1863b1a3a28aa1370e9053440bf"
SRC_URI[sha256sum] = "b785c638dc31b8fe3a7c81be1475c89b2255ab3d67b777c747d3fbc52f8027a3"
