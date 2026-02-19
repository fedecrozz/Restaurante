@echo off
title Sistema Restaurante
cd /d "%~dp0"

REM Configurar classpath con todas las librerías
set CLASSPATH=bin;lib\*

REM Buscar Java 21 o superior
set JAVA_CMD=javaw.exe

REM Intentar con Java del PATH primero
for /f "tokens=*" %%i in ('where java 2^>nul') do (
    for /f "tokens=3" %%v in ('%%i -version 2^>^&1 ^| findstr /i "version"') do (
        set JAVA_VERSION=%%v
    )
)

REM Si no encuentra Java, buscar en ubicaciones comunes
if not defined JAVA_VERSION (
    if exist "C:\Program Files\Java\jdk-21\bin\javaw.exe" set JAVA_CMD="C:\Program Files\Java\jdk-21\bin\javaw.exe"
    if exist "C:\Program Files\Java\jdk-23\bin\javaw.exe" set JAVA_CMD="C:\Program Files\Java\jdk-23\bin\javaw.exe"
    if exist "C:\Program Files\Java\jdk-22\bin\javaw.exe" set JAVA_CMD="C:\Program Files\Java\jdk-22\bin\javaw.exe"
)

REM Ejecutar la aplicación
%JAVA_CMD% -classpath "%CLASSPATH%" Principal

REM Si la aplicación falla, mostrar mensaje
if errorlevel 1 (
    echo.
    echo ========================================
    echo ERROR: No se pudo ejecutar la aplicacion
    echo ========================================
    echo.
    echo Este proyecto requiere Java 21 o superior.
    echo Los archivos fueron compilados con Java 21.
    echo.
    echo Por favor instala Java JDK 21 desde:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
)
