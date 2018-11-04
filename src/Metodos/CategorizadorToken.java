package Metodos;

import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class CategorizadorToken {

    private static List<String> OPERADORES_SIMPLES;
    private static List<String> OPERADORES_COMPLETOS;
    private static List<String> PREDEFINIDOS;
    private static List<String> RESERVADOS;
    private static List<String> RESERVADOS_SIMPLES;

    public static void InicializaRecategoriador() {
        RESERVADOS = new ArrayList<>();
        PREDEFINIDOS = new ArrayList<>();
        OPERADORES_SIMPLES = new ArrayList<>();
        OPERADORES_COMPLETOS = new ArrayList<>();
        RESERVADOS_SIMPLES = new ArrayList<>();

        PREDEFINIDOS.add("SIN");        PREDEFINIDOS.add("COS");
        PREDEFINIDOS.add("TAN");        PREDEFINIDOS.add("ATN");
        PREDEFINIDOS.add("EXP");        PREDEFINIDOS.add("SQR");
        PREDEFINIDOS.add("ABS");        PREDEFINIDOS.add("LOG");
        PREDEFINIDOS.add("INT");        PREDEFINIDOS.add("RND");

        RESERVADOS_SIMPLES.add("GO");   RESERVADOS_SIMPLES.add("TO");
        RESERVADOS_SIMPLES.add("DEF");

        RESERVADOS.add("IF");           RESERVADOS.add("THEN");
        RESERVADOS.add("GOTO");         RESERVADOS.add("LET");
        RESERVADOS.add("END");          RESERVADOS.add("STOP");
        RESERVADOS.add("FN");           RESERVADOS.add("PRINT");
        RESERVADOS.add("DEF");          RESERVADOS.add("READ");
        RESERVADOS.add("DATA");         RESERVADOS.add("FOR");
        RESERVADOS.add("FOR");          RESERVADOS.add("TO");
        RESERVADOS.add("STEP");         RESERVADOS.add("NEXT");
        RESERVADOS.add("DIM");          RESERVADOS.add("DEF FN");
        RESERVADOS.add("GOSUB");        RESERVADOS.add("RETURN");
        RESERVADOS.add("REM");

        OPERADORES_SIMPLES.add(":");    OPERADORES_SIMPLES.add("=");
        OPERADORES_SIMPLES.add(">");    OPERADORES_SIMPLES.add("<");

        OPERADORES_COMPLETOS.add("+");  OPERADORES_COMPLETOS.add("-");
        OPERADORES_COMPLETOS.add("*");  OPERADORES_COMPLETOS.add("/");
        OPERADORES_COMPLETOS.add(":="); OPERADORES_COMPLETOS.add("<>");
    }

    public static int RecategorizarToken(Token t1) {

        String s = t1.Token;

        if(PREDEFINIDOS.contains(s)) {
            return Token.PREDEFINIDO;
        }
        else if(RESERVADOS_SIMPLES.contains(s)) {
            return Token.LER_PROXIMO;
        }
        else if(RESERVADOS.contains(s)) {
            return Token.RESERVADO;
        }
        else if(OPERADORES_SIMPLES.contains(s)) {
            return Token.LER_PROXIMO;
        }
        else if(OPERADORES_COMPLETOS.contains(s)) {
            return Token.OPERADOR;
        }
        else if(s.chars().allMatch(Character::isLetter)){
            return Token.IDENTIFICADOR;
        }
        else if(s.matches("*[a-zA-z]\\d\\d"));

    }


    public static int RecategorizarToken(Token t1, Token t2) {

    }


}
