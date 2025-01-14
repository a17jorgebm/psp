## Cousas que dixo
`//` -> comentario de unha linea

`/* texto... */` -> comentario de varias liñas

javadoc:
```
/**
* Returns an Image object that can then be painted on the screen. 
* The url argument must specify an absolute <a href="#{@link}">{@link URL}</a>. The name
* argument is a specifier that is relative to the url argument. 
* <p>
* This method always returns immediately, whether or not the 
* image exists. When this applet attempts to draw the image on
* the screen, the data will be loaded. The graphics primitives 
* that draw the image will incrementally paint on the screen. 
*
* @param  url  an absolute URL giving the base location of the image
* @param  name the location of the image, relative to the url argument
* @return      the image at the specified URL
* @see         Image
*/
```

## Compilar
### Con jar
ejecutar un jar:
```bash
java -cp target/BasicBithday-1.0-SNAPSHOT.jar org.example.BirthDayStdIn
```
### Normal
#### Compilar
Esto ejecutado dende a raiz do proyecto:
````bash
javac -cp [directorioClasesCompiladas] -d [directorioGardado] nomeArchivoACompilar
javac -cp target/classes/ -d target/classes/ src/main/java/org/example/Pasos.java
````
* `-cp` : class path, donde estan as clases compiladas que usa o arquivo que queremos compilar
* `-d` : onde queremos que se garde o arquivo compilado
#### Ejecutar
O mismo que a execución pero indicando o paquete en vez de o directorio do arquivo, seguimos indicando onde estan as clases compiladas que usa
````bash
javac -cp target/classes/ org.example.Pasos
````


## Tareas en windows
### Listar
```cmd
tasklist /NH | findstr /I java.exe
```
tasklist -> lista as tareas en ejecución
* /NH -> non mostra as liñas que teñen o nome das columnas dos campos

| -> pasalle a salida do comando ao da dereita

findstr -> encontra o string indicado
    /I -> non ten en contra minusc ou mayusc


### Matar
```cmd
taskkill /F /PID idProceso
taskkill /F /IM nomeProceso
```
taskkill -> mata o proceso
* /F -> forzao
* /PID -> para indicar o id
* /IM -> para indicar o nome

## Procesos en java
Hai que saber usar os procesos con runtime e con processbuilder.