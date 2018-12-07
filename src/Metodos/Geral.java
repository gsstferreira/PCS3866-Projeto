package Metodos;

import Classes.Caractere;
import Classes.Token;
import Motores.ControleMotores;

public abstract class Geral {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";


    public static void PrintErro() {
        String s = String.format("%s %s",ANSI_RED,ControleMotores.DescricaoErro);
        System.out.println(s);
    }

    public static void PrintToken(Token t) {

        String s = "";

        switch (t.Tipo) {
            case Token.PREDEFINIDO:
                s = String.format("%s%s",ANSI_PURPLE,t.Token);
                break;
            case Token.END:
                s = String.format("%s%s",ANSI_BLUE,t.Token);
                break;
            case Token.STOP:
                s = String.format("%s %s",ANSI_BLUE,t.Token);
                break;
            case Token.RESERVADO:
                if(t.Token.equals("THEN")){
                    s = String.format("%s %s ",ANSI_CYAN,t.Token);
                }
                else if(t.Token.equals("TO")) {
                    s = String.format("%s %s ",ANSI_CYAN,t.Token);
                }
                else if(t.Token.equals("STEP")) {
                    s = String.format("%s %s ",ANSI_CYAN,t.Token);
                }
                else {
                    s = String.format("%s%s ",ANSI_CYAN,t.Token);
                }
                break;
            case Token.STRING:
                s = String.format("%s%s",ANSI_GREEN,t.Token);
                break;
            case Token.IDENTIFICADOR:
                s = String.format("%s%s",ANSI_YELLOW,t.Token);
                break;
            case Token.NUMERO:
                s = String.format("%s%s",ANSI_RED,t.Token);
                break;
            case Token.OPERADOR:
                if("()".contains(t.Token)) {
                    s = String.format("%s%s",ANSI_RESET,t.Token);
                }
                else {
                    s = String.format("%s %s ",ANSI_RESET,t.Token);
                }

                break;
            case Token.SINALIZADOR:
                s = String.format("%s%s ",ANSI_RESET,t.Token);
                break;
        }

        System.out.print(s);

    }

    public static void PrintSimbolos(Caractere s) {

        String ss = "";

        if(s.Tipo == Caractere.OUTRO) {

            if(s.Caractere == '\n') {
                ss = "\\n";
            }
            else if(s.Caractere == '\r') {
                ss = "\\r";
            }
            else {
                ss = String.format("%c",s.Caractere);
            }

            if(!ss.equals(" ")) {
                ss = String.format("%s %s ",ANSI_WHITE,ss);

                System.out.print(ss);
            }
        }
        else {
            ss = String.format("%c",s.Caractere);
            switch (s.Tipo) {
                case Caractere.LETRA:
                    ss = String.format("%s%s",ANSI_CYAN,ss);
                    System.out.print(ss);
                    break;
                case Caractere.DIGITO:
                    ss = String.format("%s%s",ANSI_PURPLE,ss);
                    System.out.print(ss);
                    break;
                case  Caractere.ESPECIAL:
                    ss = String.format("%s%s",ANSI_RED,ss);
                    System.out.print(ss);
                    break;
                case Caractere.CONTROLE:
                    if(s.Caractere == '\n') {
                        ss = "\\n";
                    }
                    else if(s.Caractere == '\r') {
                        ss = "\\r";
                    }
                    ss = String.format("%s%s",ANSI_YELLOW,ss);
                    System.out.print(ss);
                    break;
                default:
                    ss = String.format("%s%s",ANSI_RESET,ss);
                    System.out.print(ss);
                    break;
            }
        }
    }
}

