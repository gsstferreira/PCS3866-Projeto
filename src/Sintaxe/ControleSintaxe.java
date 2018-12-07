package Sintaxe;

import Classes.AnalisadorSintatico.Automatos.ASSIGN;
import Classes.AnalisadorSintatico.Automatos.EXP;
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
    }
}
