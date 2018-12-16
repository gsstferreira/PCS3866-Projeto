package Sintaxe;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Automatos.*;
import Classes.AnalisadorSintatico.ResultadoAnalise;
import Classes.Memoria;
import Classes.Token;
import Metodos.Geral;

import java.util.ArrayList;
import java.util.List;

public abstract class ControleSintaxe {

    public static String DescricaoErro;
    private static Automato automatoLinha;

    public static int hasDATA = 0;
    public static boolean hasREAD = false;
    public static String LinhasREAD;

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

    public static boolean AnaliseSintatica() {

        InicializarAutomatos();
        List<Token> programa = new ArrayList<>();

        for (List<Token> lt : Memoria.TokensReclassificados) { programa.addAll(lt); }

        ResultadoAnalise a = VerificarSintaxe(programa);

        if (!a.Ok || !a.linhaRestante.isEmpty()) {
            int linha = a.linhaRestante.get(0).Linha;
            DescricaoErro = String.format("Linha %d: Erro de sintaxe: %s", linha, Geral.FormatStringLinha(Memoria.Linhas.get(linha)));
            return false;
        }

        if (!TabelaSimbolos.PreencherTabela(Memoria.TokensReclassificados)) {
            return false;
        }

        if(!TabelaSimbolos.VerificarReferencias()) {
            return false;
        }

        Memoria.TokensReclassificados = AdicaoRedundancia.AdicionarRedundanciaZero(Memoria.TokensReclassificados);
        Memoria.TokensReclassificados = AdicaoRedundancia.AdcionarRedundanciaStep(Memoria.TokensReclassificados);

        if(hasREAD && hasDATA == 0) {
            DescricaoErro = String.format("Erro: Comando \"READ\" (%s) sem a presença de \"DATA\"",LinhasREAD);
            return false;
        }
        else if(hasDATA > 1) {
            DescricaoErro = "Erro: Múltiplos comandos \"DATA\"";
            return false;
        }
        else if(hasDATA == 1 && !hasREAD) {
            Geral.PrintWarning("Alerta: Código contém comando \"DATA\", mas não apresenta comandos \"READ\"");
        }

        return true;
    }

}
