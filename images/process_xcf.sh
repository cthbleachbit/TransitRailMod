#!/bin/bash

rm -rf png
rm -rf ../src/main/resources/assets/transitrailmod/textures/*
mkdir `find -type d | sed -e "s|xcf|png|g"`

for xcf_name in `find | grep -e "\w.xcf$"`; do
	png_name=`echo $xcf_name | sed -e "s|\./xcf/||g" -e "s|\.xcf||g"`
	echo $png_name
	xcf2png $xcf_name -o png/${png_name}.png
done

cp -R png/* ../src/main/resources/assets/transitrailmod/textures/
