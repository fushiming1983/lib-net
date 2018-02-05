#!/bin/bash

rm -rf release/out/*.apk
rm -rf release/*.apk
sh gradlew clean
sh gradlew release -PENV=PRD
python release.py

echo ""
echo "======================================="
echo "编译完成，请在release/out目录下查看所有apk文件"
echo "======================================="
echo ""
