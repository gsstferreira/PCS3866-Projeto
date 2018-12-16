package Lexico.Metodos;

import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class CategorizadorToken {

    private static List<String> OPERADORES_SIMPLES = new ArrayList<>();
    private static List<String> OPERADORES_COMP = new ArrayList<>();
    private static List<String> OPERADORES = new ArrayList<>();
    private static List<String> RESERVADOS = new ArrayList<>();
    private static List<String> RESERVADOS_SIMPLES = new ArrayList<>();
    private static List<String> SINALIZADORES = new ArrayList<>();

    // inicializa as listas contendo tokens válidos para linguagem BASIC
    public static void InicializaRecategorizador() {

        RESERVADOS_SIMPLES.add("GO");   RESERVADOS_SIMPLES.add("TO");

        RESERVADOS.add("IF");           RESERVADOS.add("THEN");
        RESERVADOS.add("GOTO");         RESERVADOS.add("LET");
        RESERVADOS.add("END");          RESERVADOS.add("STOP");
        RESERVADOS.add("FN");           RESERVADOS.add("PRINT");
        RESERVADOS.add("DEF");          RESERVADOS.add("FOR");
        RESERVADOS.add("FOR");          RESERVADOS.add("TO");
        RESERVADOS.add("STEP");         RESERVADOS.add("NEXT");
        RESERVADOS.add("REM");          RESERVADOS.add("GOSUB");
        RESERVADOS.add("RETURN");

        OPERADORES_SIMPLES.add(">");    OPERADORES_SIMPLES.add("<");

        OPERADORES.add("+");            OPERADORES.add("-");
        OPERADORES.add("*");            OPERADORES.add("/");

        OPERADORES_COMP.add("<>");      OPERADORES_COMP.add("=");
        OPERADORES_COMP.add(">");       OPERADORES_COMP.add("<");
        OPERADORES_COMP.add("<=");      OPERADORES_COMP.add(">=");

        SINALIZADORES.add("(");         SINALIZADORES.add(")");
        SINALIZADORES.add(",");         SINALIZADORES.add(":");
    }

    // classifica tipo do token lido
    public static int RecategorizarToken(Token t1) {

        String s = t1.Token.toUpperCase();

        if(RESERVADOS_SIMPLES.contains(s)) { return Token.LER_PROXIMO; }

        else if(RESERVADOS.contains(s)) { return Token.RESERVADO; }

        else if(OPERADORES_SIMPLES.contains(s)) { return Token.LER_PROXIMO; }

        else if(OPERADORES.contains(s)) { return Token.OPERADOR; }

        else if(OPERADORES_COMP.contains(s)) { return Token.OPERADOR_COMPARADOR; }

        else if(SINALIZADORES.contains(s)) { return Token.OPERADOR_SINALIZADOR; }

        else if(s.matches("[A-Z]\\d?")) { return Token.IDENTIFICADOR; }

        else if(s.chars().allMatch(Character::isDigit)) { return Token.NUMERO; }

        else if(s.matches("\\d+")) { return Token.NUMERO; }

        else if(s.startsWith("\"") && s.endsWith("\"")) { return Token.STRING; }

        else { return Token.INVALIDO; }
    }

    // classifica tipo dos tokens lidos, em exemplos como "GO TO" -> "GOTO"
    public static int RecategorizarToken(Token t1, Token t2) {

        String s1 = t1.Token;
        String s2 = t2.Token;

        String s3 = s1.concat(s2).replace(" ","");

        if(RESERVADOS.contains(s3)){ return Token.RESERVADO; }

        else if(OPERADORES_COMP.contains(s3)){ return Token.OPERADOR_COMPARADOR; }

        else if(RESERVADOS.contains(s1)){ return Token.SALVAR_SIMPLES_RESERVADO; }

        else if(OPERADORES_COMP.contains(s1)){ return Token.SALVAR_SIMPLES_OPERADOR; }

        else { return Token.INVALIDO; }
    }
}
