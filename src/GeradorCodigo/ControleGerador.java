package GeradorCodigo;

import Classes.GeradorCodigo.LinhaAssembly;
import Classes.GeradorCodigo.LinhaCodInter;
import Classes.Memoria;
import Classes.Token;
import GeradorCodigo.Metodos.*;
import Metodos.Geral;

import java.util.ArrayList;
import java.util.List;

public abstract class ControleGerador {

    public static List<LinhaAssembly> codigoAssembly = new ArrayList<>();

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

//        CodigoIntermediario.NovasLinhas.sort((o1, o2) -> {
//            if (o1.Tipo == LinhaCodInter.DATA) {
//                return 1;
//            }
//            else if(o2.Tipo == LinhaCodInter.DATA) {
//                return -1;
//            }
//            return 0;
//        });

        Geral.PrintNeutral("\tIniciando geração de código Assembly...");

        codigoAssembly.add(new LinhaAssembly("!","JP","\\004"));
        codigoAssembly.add(new LinhaAssembly("STOP","HM","\\004"));

        for (LinhaCodInter l:CodigoIntermediario.NovasLinhas) {
            codigoAssembly.addAll(TraduzirAssembly.TraduzirLinha(l));
        }
        codigoAssembly.add(new LinhaAssembly("!","JP","STOP"));
        codigoAssembly = TraduzirAssembly.IsolarSubrotinas(codigoAssembly);
        codigoAssembly.addAll(TraduzirAssembly.ConfigurarMemoria(codigoAssembly));

        Geral.PrintNeutral("\tCódigo Assembly gerado.\n");

        for(int i = 0; i < codigoAssembly.size();i ++) {
            LinhaAssembly l = codigoAssembly.get(i);
            l.Linha = 2*i;
        }

    }

    public static void ParaMaquina(){

        LinguagemMaquina.Montar(codigoAssembly);
    }
}
