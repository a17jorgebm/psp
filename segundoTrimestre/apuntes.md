# 3. Network communication

## Network Interfaces
Traballaremos cas clases:
- `NetworkInterface`: representar e traballar coa interfaz de red (unha tarjeta de red por ejemplo: enp0s8)
  - `InterfaceAddress`: representar e traballar coa subred(ip (mediante InetAddress), prefijo subred, broadcast...)
  - `InetAddress`: traballar mais especificamente coa ip e os seus datos (ip, host name, isReachable...)

No caso de solo necesitar as **Ip**, usamos directamente `InetAddress`, pero no caso de necesitar saber **información sobre a subred**, como a mascara de subred ou a dirección de broadcast, usaremos `InterfaceAddress`.

````java
public class Ejer1_ListInterfaces {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnumeration.hasMoreElements()){
                NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
                //datos da netWorkInterface
                System.out.println("Nome interfaz: "+networkInterface.getDisplayName());
                System.out.println("Nome feo: "+networkInterface.getName());
                //querendo saber datos da subrede
                ArrayList<InterfaceAddress> interfaceAddressEnumeration = new ArrayList<>(networkInterface.getInterfaceAddresses());
                interfaceAddressEnumeration.forEach(interfaceAddress -> {
                    InetAddress inetAddress = interfaceAddress.getAddress();
                    System.out.println("\tIP: "+inetAddress);
                    System.out.println("\tSubnet prefix: "+interfaceAddress.getNetworkPrefixLength());
                    System.out.println("\tIp broadcast: "+interfaceAddress.getBroadcast());
                });

                //querendo solo saber as IP (directamente InetAddress)
                Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
                while (inetAddressEnumeration.hasMoreElements()){
                    InetAddress inetAddress = inetAddressEnumeration.nextElement();
                    System.out.println("\tIP(sin detalles subred): "+inetAddress.getHostAddress());
                }
            }
        }catch (SocketException e){
            System.out.println(e.getMessage());
        }
    }
}
````

## URL
Coas clases `URI`, `URL` e `URLConnection` xa temos o necesario para facer unha conexion a un stream de datos de calquer pagina.

A partir da URI sacamos a URL e da url sacamos a conexion

````java
URLConnection urlConnection = null;
        try{
            urlConnection = new URI(URL_DIRECTION).toURL().openConnection();
        }catch (URISyntaxException | MalformedURLException e){
            System.out.println("La url tiene un mal formato");
        }catch (IOException e){
            System.out.println("No se ha podido establecer conexion con la URL");
        }

        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                ){
            String line;
            while ((line=bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Error lendo o json");
        }
````

## Sockets
### Sin claves (non seguro)
Usaremos `Socket` para escribir entre cliente e servidor, e `ServerSocket` para escuitar no servidor por peticions, das cales sacaremos o `Socket` de cada unha.

### Con claves
Para generar as claves usamos `keytool`, e para eso hai q abrir a terminal en `C:\Program Files\Java\jdk-21\bin`.

````shell
keytool -genkey -keyalg RSA -alias server -keystore ServerKeys.jks -storepass 12345678 #clave privada
keytool -exportcert -alias server -file server.cer -keystore ServerKeys.jks #certificado
keytool -importcert -trustcacerts -alias server -file server.cer -keystore ClientKeys.jks -storepass 87654321 #clave publica
````

Despois para usalas no programa hai que metelas nas propiedades da JVM, lendo os datos desde un ficheiro coa clase Properties.\
Servidor:
````
javax.net.ssl.keyStore=src/main/java/practicarExamen/ejer6ConSSLGuessANumber/sslKeys/ServerKeys.jks
javax.net.ssl.keyStorePassword=12345678
````
Cliente:
````
javax.net.ssl.trustStore=src/main/java/practicarExamen/ejer6ConSSLGuessANumber/sslKeys/ClientKeys.jks
javax.net.ssl.trustStorePassword=87654321
````
No Programa:
````java
private static Properties readProperties() throws IOException{
    try(FileInputStream fileInputStream = new FileInputStream(Path.of("src/main/java/practicarExamen/ejer6ConSSLGuessANumber/server.config.properties").toFile())){
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties;
    }
}

//ahora hai que meter as propiedades na JVM con System.setProperty
try{
  readProperties().forEach((key, value)->System.setProperty(key.toString(),value.toString()));
}catch (IOException e){
  System.out.println(e.getMessage());
  System.exit(1);
}
````
