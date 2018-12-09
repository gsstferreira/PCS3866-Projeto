package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class PRINT {

    public static Automato automato;

    // configura o autômato PRINT
    public static void InicializarAutomato(Automato exp) {

        Transicao t;

        Automato print = new Automato();

        Estado print1 = new Estado();
        Estado print2 = new Estado();
        Estado print3 = new Estado();
        Estado print4 = new Estado();
        print4.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "PRINT";
        t.ProximoEstado = print2;
        print1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = print3;
        print2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.STRING;
        t.ProximoEstado = print3;
        print2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = ",";
        t.ProximoEstado = print2;
        print3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.VAZIO;
        t.ProximoEstado = print4;
        print3.addTransicao(t);

        print.EstadoInicial = print1;
        print.Estados.add(print1);
        print.Estados.add(print2);
        print.Estados.add(print3);
        print.Estados.add(print4);

        automato = print;
    }
}
