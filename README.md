This is a modified Yocto meta-rockchip layer for the OrangePi-3B. This class depends on meta-openembedded, meta-arm

To use, follow the steps below:
	- Add this class to the project path and bblayers list
	- In build/conf/local.conf (Note: Some values ​​are for reference only and can be edited):
		MACHINE ?= "opi3b-rk3566"
		IMAGE_CLASSES += "image_types_wic"
		WIC_COMPRESSION_EXTENSION = ".xz"
		MACHINE_FEATURES += " rk-u-boot-env"
		RK_IMAGE_INCLUDES_UBOOT_ENV = "1"
		BB_NUMBER_THREADS = "12"
		PARALLEL_MAKE = "-j 12"
	- When building, you will get the error "Orangepi-3b-rk3566_defconfig file not found" and "No rules to make target xxxx.dtb".

		+ For orangepi-3b-rk3566_defconfig error, download u-boot source tree from https://source.denx.de/u-boot/u-boot.git then unzip. Use all 	the unzipped files to replace the files in the path build/tmp/work/opi3b_rk3566-poky-linux/u-boot/2024.01/git/
		+ For xxxx.dtb error, go to build/tmp/ folder and find the xxxx.dtb file that reported the error and copy it to build/tmp/work-shared/opi3b_rk3566/kernel-source/arch/arm64/boot/dts/rockchip. Then rebuild.

Some points edited to be able to build:
	- In meta-rockchip/conf/machine/ add opi3b-rk3566.conf
	- In meta-rockchip/recipes-kernel/linux/linux-yocto_%.bbappend add COMPATIBLE_MACHINE.
