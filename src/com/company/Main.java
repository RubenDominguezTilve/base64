package com.company;

public class Main {

    public static void main(String[] args) {

        String cadena = "Man is distinguished, not only by his reason, but by this singular passion from other animals, which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure.";
        char[] base = codificar(cadena);

        System.out.println(String.copyValueOf(base));

    }

    public static char[] codificar(String cadena) {
        String binarios = "";
        for (int i = 0; i < cadena.length(); i++) {
            //System.out.println((int)cadena.charAt(i));
            binarios += formatear(Integer.toBinaryString((int) cadena.charAt(i)));
        }

        char[] sext = sextetos(binarios, binarios.length() % 6);
        return sext;
    }

    public static String formatear(String binario) {
        //al traducir a binario
        // traduce usando el numero minimo
        // de posiciones para cada bit(101 en vez de 0000 0101)
        String result = binario;
        while (result.length() < 8) {
            // System.out.println("entro aqui");
            result = "0" + result;
            //result=result+"0";
        }
        return result;
    }

    public static char[] sextetos(String binarios, int resto) {
        //System.out.println(resto);
        binarios = (resto != 0) ? binarios + "0000":binarios;
        int length = (binarios.length() / 6) + 1;//!=0)?binarios.length()/6+1:binarios.length()/6;
        if(resto==2){
            length++;
        }
        int[] sextetos = new int[length];
        for (int i = 0; i < length; i++) {
            if (canTake(binarios, i)) {
                sextetos[i] = Integer.parseInt(binarios.substring(i * 6, (i + 1) * 6), 2);
            }
        }
        String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        char[] codif = new char[length];
        for (int k = 0; k < sextetos.length; k++) {
            // System.out.println(sextetos[i]);
            codif[k] = base64.charAt(sextetos[k]);
        }
        if (resto == 2) {
            codif[codif.length - 1] = '=';
           codif[codif.length - 2] = '=';
        } else if (resto == 4) {
            codif[codif.length - 1] = '=';

        }else if (resto == 0) {
            codif[codif.length - 1] = ' ';
        }

        return codif;
    }

    public static boolean canTake(String binarios, int i) {
        try {
            binarios.substring(i * 6, (i + 1) * 6);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
