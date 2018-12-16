package Metodos;

import Classes.AnalisadorLexico.Caractere;
import Classes.GeradorCodigo.LinhaCodInter;
import Classes.Token;

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

    public static String FormatStringLinha(String linha) {

        String s = linha;

        while(s.startsWith(" ")) {
            s = s.substring(1);
        }

        s = s.replace("\n","");
        s = s.replace("\r","");

        return s;
    }

    public static void PrintErro(String linha) {
        String s = String.format("%s%s",ANSI_RED,linha);
        System.out.println(s);
    }

    public static void PrintOK(String linha) {
        String s = String.format("%s%s",ANSI_GREEN,linha);
        System.out.println(s);
    }

    public static void PrintWarning(String linha) {
        String s = String.format("%s%s",ANSI_YELLOW,linha);
        System.out.println(s);
    }

    public static void PrintNeutral(String linha) {
        String s = String.format("%s%s",ANSI_RESET,linha);
        System.out.println(s);
    }

    public static void PrintHighlight(String linha) {
        String s = String.format("%s%s",ANSI_PURPLE,linha);
        System.out.println(s);
    }

    public static void PrintToken(Token t) {

        String s = "";

        switch (t.Tipo) {
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
            case Token.OPERADOR_SINALIZADOR:

                if(t.Token.equals(":")) {
                    s = String.format("%s%s\t",ANSI_RESET,t.Token);
                }
                else {
                    s = String.format("%s%s",ANSI_RESET,t.Token);
                }
                break;
            case Token.OPERADOR_COMPARADOR:
                s = String.format("%s %s ",ANSI_RESET,t.Token);
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

    public static void PrintCodigoIntermediario(LinhaCodInter l) {

        if(!l.Label.equals("!")) {
            System.out.print(String.format("%sLabel: %s\t",ANSI_PURPLE,l.Label));
        }
        else {
            System.out.print("\t\t\t");
        }

        switch (l.Tipo) {
            case LinhaCodInter.ASSIGN:

                StringBuilder s = new StringBuilder();

                for (Token t:l.Expressoes.get(0)) {
                    s.append(t.Token);
                    s.append(" ");
                }

                System.out.print(String.format("%sMOVER %s%s%sem %s",ANSI_RESET,ANSI_CYAN,s.toString(),ANSI_RESET,l.Variaveis.get(0).Token));
                break;
            case LinhaCodInter.DATA:
                System.out.print("CONSTANTES\t");
                System.out.print(ANSI_CYAN);
                for (Token t:l.Variaveis) {
                    System.out.print(String.format("%s\t",t.Token));
                }
                break;
            case LinhaCodInter.FOR:
                StringBuilder s1 = new StringBuilder();
                StringBuilder s2 = new StringBuilder();
                StringBuilder s3 = new StringBuilder();

                for (Token t:l.Expressoes.get(0)) {
                    s1.append(t.Token);
                    s1.append(" ");
                }

                for (Token t:l.Expressoes.get(1)) {
                    s2.append(t.Token);
                    s2.append(" ");
                }

                for (Token t:l.Expressoes.get(2)) {
                    s3.append(t.Token);
                    s3.append(" ");
                }

                System.out.print(String.format("%sLAÇO DE %s ( %s%s%s, %s%s%s, %s%s%s)",ANSI_RESET,l.Variaveis.get(0).Token,
                        ANSI_CYAN,s1.toString(),ANSI_RESET,ANSI_YELLOW,s2.toString(),ANSI_RESET,ANSI_RED,s3.toString(),ANSI_RESET));
                break;
            case LinhaCodInter.GOSUB:
                System.out.print(String.format("%sSUBROTINA EM %s%s",ANSI_RESET,ANSI_PURPLE,l.Variaveis.get(0).Token));
                break;
            case LinhaCodInter.GOTO:
                System.out.print(String.format("%sDESVIO PARA %s%s",ANSI_RESET,ANSI_PURPLE,l.Variaveis.get(0).Token));
                break;
            case LinhaCodInter.IF:
                StringBuilder s4 = new StringBuilder();

                for (Token t:l.Expressoes.get(0)) {
                    s4.append(t.Token);
                    s4.append(" ");
                }

                System.out.print(String.format("%sDECISÃO COM %s ( %s%s%s, %s%s%s, %s%s%s )",ANSI_RESET,l.Variaveis.get(0).Token,
                        ANSI_CYAN,l.Variaveis.get(1).Token,ANSI_RESET,ANSI_YELLOW,s4.toString(),ANSI_RESET,ANSI_PURPLE,l.Variaveis.get(2).Token,ANSI_RESET));
                break;
            case LinhaCodInter.NEXT:
                StringBuilder s5 = new StringBuilder();

                for (Token t:l.Expressoes.get(0)) {
                    s5.append(t.Token);
                    s5.append(" ");
                }
                System.out.print(String.format("%sFIM DE LAÇO COM %s ( %s%s%s)",ANSI_RESET,l.Variaveis.get(0).Token,ANSI_RED,s5.toString(),ANSI_RESET));
                break;
            case LinhaCodInter.PRINT:
                System.out.print("IMPRESSAO\t");
                System.out.print(ANSI_CYAN);
                for (Token t:l.Variaveis) {
                    System.out.print(String.format("%s\t",t.Token));
                }
                break;
            case LinhaCodInter.RETURN:
                System.out.print(String.format("%sRETORNO DA SUBROTINA ATUAL",ANSI_RESET));
                break;
            case LinhaCodInter.READ:
                System.out.print("LEITURA\t");
                System.out.print(ANSI_CYAN);
                for (Token t:l.Variaveis) {
                    System.out.print(String.format("%s\t",t.Token));
                }
                break;
        }

        System.out.println();
    }
}

