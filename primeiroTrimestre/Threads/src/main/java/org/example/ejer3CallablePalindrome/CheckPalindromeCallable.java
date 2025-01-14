package org.example.ejer3CallablePalindrome;

import java.util.concurrent.Callable;

public class CheckPalindromeCallable implements Callable {
    private String palabra;

    public CheckPalindromeCallable(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public Object call() throws Exception {
        String palabraContrario="";
        for (int i=palabra.length()-1;i>=0;i--){
            palabraContrario+=palabra.charAt(i);
        }
        String frase="A palabra %s %s";
        return String.format(frase,palabra,(palabraContrario.equalsIgnoreCase(palabra) ?
                "é un palindromo"
                : "non é un palindromo"));
    }
}
