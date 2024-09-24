package org.example;

public class OpenChrome {
    public static void main(String[] args) {
        Runtime runtime=Runtime.getRuntime();
        String[] comando={"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe","youtube.es"};
        try{
            Process procesoChrome=runtime.exec(comando);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
