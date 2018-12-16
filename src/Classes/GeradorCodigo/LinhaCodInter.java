package Classes.GeradorCodigo;

import Classes.AnalisadorSintatico.Identificador;
import Classes.Token;
import GeradorCodigo.Metodos.EXPtoRPN;

import java.util.ArrayList;
import java.util.List;

public class LinhaCodInter {

    public String Label = "!";
    public int Tipo;
    public List<List<Token>> Expressoes = new ArrayList<>();
    public List<Token> Variaveis = new ArrayList<>();

    public LinhaCodInter(){}

    public static final int ASSIGN = 0;
    public static final int PRINT = 1;
    public static final int FOR = 2;
    public static final int NEXT = 3;
    public static final int IF = 4;
    public static final int GOTO = 5;
    public static final int GOSUB = 6;
    public static final int RETURN = 7;
    public static final int DATA = 8;
    public static final int READ = 9;

}
