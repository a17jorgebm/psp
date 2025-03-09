package practicarExamen.ejer1NetworkInterfaces;


import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * DU3 - Exercise 1 - Network interfaces
 *
 * Create a program in Java to list all the network interfaces and their IP addresses on your computer. Use the class Java.net.NetworkInterface.
 */
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
