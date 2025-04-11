@echo off
REM Compilar los archivos .java
javac -d out -sourcepath src src\App.java

REM Crear el archivo JAR
jar cfe SerealizacionApp.jar App -C out .

REM Ejecutar el archivo JAR
java -jar SerealizacionApp.jar