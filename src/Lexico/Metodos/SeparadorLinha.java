package Lexico.Metodos;

import Lexico.Motores.Nivel1;

import java.io.FileInputStream;

public abstract class SeparadorLinha {

    public static String lerLinha(FileInputStream fileInputStream) {

        StringBuilder sb = new StringBuilder();

        try {
            char c = '0';

            while(c != '\n' && fileInputStream.available() > 0) {
                c = (char) fileInputStream.read();
                sb.append(c);
            }

            if(fileInputStream.available() < 1) {
                Nivel1.eof = true;
            }

            String s = sb.toString();

            if(!s.contains("\r\n") && !s.isEmpty()) {

                s = s.replace("\r","");
                s = s.replace("\n","");
                s = s.concat("\n\r");
            }

            return s;
        }
        catch (Exception e) {
            return null;
        }
    }

}
