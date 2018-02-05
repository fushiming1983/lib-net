call del release\*.apk
call del release\out\*.apk
call gradlew clean
call gradlew release -PENV=PRD
call python release.py

echo ""
echo "======================================="
echo "build success，packages all in release/out"
echo "======================================="
echo ""
