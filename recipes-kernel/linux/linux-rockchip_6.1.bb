# Copyright (C) 2024, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-rockchip.inc

inherit local-git

FILESEXTRAPATHS:prepend := "${THISDIR}/frl-rk3566:"
FILESEXTRAPATHS:prepend := "${THISDIR}/opi3b-rk3566:"
FILESEXTRAPATHS:prepend := "${THISDIR}/sdr-rk3588s:"

SRCREV = "${AUTOREV}"
SRC_URI = " \
	git://github.com/radxa/kernel.git;protocol=https;branch=linux-6.1-stan-rkr4.1-buildroot; \
	file://${THISDIR}/files/cgroups.cfg \
	file://rk3566-frl-v1.0.dts \
	file://rk3566-frl.dtsi \
	file://rk3566-frl.cfg \
	file://rk3566-opi3b-v1.0.dts \
	file://rk3566-opi3b.dtsi \
	file://rk3566-opi3b.cfg \
	file://rk3588s-sdr-board2.dts \
	file://rk3588s-sdr-board2.cfg \
	file://0001-arm64-dts-rockchip-Makefile-Add-support-RK3588S-Bo.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "6.1"

SRC_URI:append = " ${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', \
		   'file://${THISDIR}/files/ext4.cfg', \
		   '', \
		   d)}"

EXTRA_OEMAKE += "KCFLAGS=-Wno-error"

do_configure:prepend() {
    cp ${WORKDIR}/rk3566-frl-v1.0.dts ${S}/arch/arm64/boot/dts/rockchip/
    cp ${WORKDIR}/rk3566-frl.dtsi ${S}/arch/arm64/boot/dts/rockchip/
	cp ${WORKDIR}/rk3566-opi3b-v1.0.dts ${S}/arch/arm64/boot/dts/rockchip/
    cp ${WORKDIR}/rk3566-opi3b.dtsi ${S}/arch/arm64/boot/dts/rockchip/
    cp ${WORKDIR}/rk3588s-sdr-board2.dts ${S}/arch/arm64/boot/dts/rockchip/
}
