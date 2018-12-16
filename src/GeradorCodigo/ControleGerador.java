package GeradorCodigo;

import Classes.GeradorCodigo.LinhaCodInter;
import Classes.Memoria;
import Classes.Token;
import GeradorCodigo.Metodos.CodigoIntermediario;
import GeradorCodigo.Metodos.Simplificador;
import GeradorCodigo.Metodos.SubstituicaoFuncoes;
import Metodos.Geral;
import Testes.Traducao.TesteSimplificador;

import java.util.ArrayList;
import java.util.List;

public abstract class ControleGerador {

    public static void ParaAssembly() {

        List<List<Token>> ll = new ArrayList<>();

        for (List<Token> lt: Memoria.TokensReclassificados) {
            ll.add(new ArrayList<>());
            ll.get(ll.size()-1).addAll(lt);
        }

        ll = Simplificador.SimplificarCodigo(ll);
        ll = SubstituicaoFuncoes.SubstituirFuncoes(ll);

        Geral.PrintNeutral("\tIniciando geração de código intermediário...");

        for (List<Token> lt:ll) {
            CodigoIntermediario.GerarLinha(lt);
        }

        Geral.PrintNeutral("\tCódigo intermediário gerado.\n");

    }
}
