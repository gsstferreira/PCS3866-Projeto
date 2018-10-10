package Metodos;

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

            String s = sb.toString();

            if(!s.contains("\r\n") && !s.isEmpty()) {

                s = s.replace("\r","");
                s = s.replace("\n","");
                s = s.concat("\r\n");
            }

            return s;
        }
        catch (Exception e) {
            return null;
        }
    }

}
