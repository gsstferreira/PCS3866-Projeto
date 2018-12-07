package Metodos;

import Classes.Caractere;

public abstract class CategorizadorCaractere {

    private static final String ESPECIAIS = "\"*()-=+/<>,.:^";
    private static final String CONTROLES = "\n\r";

    public static void FiltragemSimbolo(Caractere s) {

        char c = s.Caractere;

        if(c == ' ' || c == '\t') {
            s.Util = false;
        }
        else {
            s.Util = true;
        }
    }

    public static void IdentificarSimbolo(Caractere s) {

        char c = s.Caractere;

        if(Character.isLetter(c)) {
            s.Tipo = Caractere.LETRA;
        }
        else if(Character.isDigit(c)){
            s.Tipo = Caractere.DIGITO;
        }
        else if(c == ' ' || c == '\t') {
            s.Tipo = Caractere.ESPACO;
        }
        else if(ESPECIAIS.contains(String.format("%c",c))) {
            s.Tipo = Caractere.ESPECIAL;
        }
        else if(CONTROLES.contains(String.format("%c",c))) {
            s.Tipo = Caractere.CONTROLE;
        }
        else {
            s.Tipo = Caractere.OUTRO;
        }
    }

}
