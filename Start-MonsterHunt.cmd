@echo off
setlocal
pushd "%~dp0" || exit /b 1
where java.exe >nul 2>&1
if errorlevel 1 (
    echo Bitte installiere das Temurin JDK 21 und fuege Java zum PATH hinzu.
    pause
    popd
    exit /b 1
)
chcp 65001 >nul
java.exe -jar MonsterHunt.jar
set "game_exit=%errorlevel%"
if not "%game_exit%"=="0" (
    echo Voraussetzung: Java 21 oder neuer. Pruefe die Version mit java -version.
    pause
)
popd
exit /b %game_exit%
