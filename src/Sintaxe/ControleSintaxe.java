package Sintaxe;

import Classes.AnalisadorSintatico.Automatos.*;
import Classes.Memoria;
import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class ControleSintaxe {

    public static boolean ErroSintaxe = false;
    public static String DescricaoErro;

    public static void InicializarAutomatos() {

        EXP.InicializarAutomato();
        ASSIGN.InicializarAutomato(EXP.automato);
        DATA.InicializarAutomato();
        GOSUB.InicializarAutomato();
        GOTO.InicializarAutomato();
        IF.InicializarAutomato(EXP.automato);
        NEXT.InicializarAutomato();
        PRINT.InicializarAutomato(EXP.automato);
        READ.InicializarAutomato();
        REMARK.InicializarAutomato();
        RETURN.InicializarAutomato();
    }
}
