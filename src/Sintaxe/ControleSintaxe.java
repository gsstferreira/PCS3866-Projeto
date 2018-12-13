package Sintaxe;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Automatos.*;
import Classes.AnalisadorSintatico.ResultadoAnalise;
import Classes.Memoria;
import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class ControleSintaxe {

    public static boolean ErroSintaxe = false;
    public static String DescricaoErro;
    private static Automato automatoLinha;
    public static void InicializarAutomatos() {

        EXP.InicializarAutomato();
        ASSIGN.InicializarAutomato(EXP.automato);
        DATA.InicializarAutomato();
        DEF.InicializarAutomato(EXP.automato);
        FOR.InicializarAutomato(EXP.automato);
        GOSUB.InicializarAutomato();
        GOTO.InicializarAutomato();
        IF.InicializarAutomato(EXP.automato);
        NEXT.InicializarAutomato();
        PRINT.InicializarAutomato(EXP.automato);
        READ.InicializarAutomato();
        REMARK.InicializarAutomato();
        RETURN.InicializarAutomato();

        List<Automato> automatos = new ArrayList<>();
        automatos.add(ASSIGN.automato);
        automatos.add(DATA.automato);
        automatos.add(DEF.automato);
        automatos.add(FOR.automato);
        automatos.add(GOSUB.automato);
        automatos.add(GOTO.automato);
        automatos.add(IF.automato);
        automatos.add(NEXT.automato);
        automatos.add(PRINT.automato);
        automatos.add(READ.automato);
        automatos.add(REMARK.automato);
        automatos.add(RETURN.automato);

        BSTATEMENT.InicializarAutomato(automatos);

        PROGRAM.InicializarAutomato(BSTATEMENT.automato);

        automatoLinha = PROGRAM.automato;
    }

    public static ResultadoAnalise VerificarSintaxe(List<Token> linha) {
        return automatoLinha.ExecutarAutomato(linha);
    }
}
