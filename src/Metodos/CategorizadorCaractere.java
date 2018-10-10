package Metodos;

import Classes.Simbolo;

public abstract class CategorizadorCaractere {

    private static final String ESPECIAIS = "\\\'\"!?@#$%¨&*()_-=+{}[]/<>,.:;~^";
    private static final String CONTROLES = "\n\r";

    public static void FiltragemSimbolo(Simbolo s) {

        char c = s.Simbolo;

        if(c == ' ' || c == '\t') {
            s.Util = false;
        }
        else {
            s.Util = true;
        }
    }

    public static void IdentificarSimbolo(Simbolo s) {

        char c = s.Simbolo;

        if(Character.isLetter(c)) {
            if(Character.isUpperCase(c)){
                s.Tipo = Simbolo.S_LETRA_MAI;
            }
            else {
                s.Tipo = Simbolo.S_LETRA_MIN;
            }
        }
        else if(Character.isDigit(c)){
            s.Tipo = Simbolo.S_DIGITO;
        }
        else if(c == ' ' || c == '\t') {
            s.Tipo = Simbolo.S_ESPACO;
        }
        else if(ESPECIAIS.contains(String.format("%c",c))) {
            s.Tipo = Simbolo.S_ESPECIAL;
        }
        else if(CONTROLES.contains(String.format("%c",c))) {
            s.Tipo = Simbolo.S_CONTROLE;
        }
        else {
            s.Tipo = Simbolo.S_INVALIDO;
        }
    }

}
