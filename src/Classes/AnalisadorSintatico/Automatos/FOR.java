package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class FOR {

    public static Automato automato;

    // configura o autômato FOR
    public static void InicializarAutomato(Automato exp) {

        Transicao t;

        Automato _for = new Automato();

        Estado for1 = new Estado();
        Estado for2 = new Estado();
        Estado for3 = new Estado();
        Estado for4 = new Estado();
        Estado for5 = new Estado();
        Estado for6 = new Estado();
        Estado for7 = new Estado();
        Estado for8 = new Estado();
        Estado for9 = new Estado();
        for9.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "FOR";
        t.ProximoEstado = for2;
        for1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = for3;
        for2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "=";
        t.ProximoEstado = for4;
        for3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = for5;
        for4.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "TO";
        t.ProximoEstado = for6;
        for5.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = for7;
        for6.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.VAZIO;
        t.ProximoEstado = for9;
        for7.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "STEP";
        t.ProximoEstado = for8;
        for7.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = for9;
        for8.addTransicao(t);

        _for.EstadoInicial = for1;
        _for.Estados.add(for1);
        _for.Estados.add(for2);
        _for.Estados.add(for3);
        _for.Estados.add(for4);
        _for.Estados.add(for5);
        _for.Estados.add(for6);
        _for.Estados.add(for7);
        _for.Estados.add(for8);
        _for.Estados.add(for9);

        automato = _for;
    }
}
