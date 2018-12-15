package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class DEF {

    public static Automato automato;

    // configura o autômato DEF
    public static void InicializarAutomato(Automato exp) {

        Transicao t;

        Automato def = new Automato();

        Estado def1 = new Estado();
        Estado def2 = new Estado();
        Estado def3 = new Estado();
        Estado def4 = new Estado();
        Estado def5 = new Estado();
        Estado def6 = new Estado();
        Estado def7 = new Estado();
        Estado def8 = new Estado();
        Estado def9 = new Estado();
        def9.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "DEF";
        t.ProximoEstado = def2;
        def1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "FN";
        t.ProximoEstado = def3;
        def2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.REGEX;
        t.valorTransicao = "[A-VY-Z]";
        t.ProximoEstado = def4;
        def3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "(";
        t.ProximoEstado = def5;
        def4.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "X";
        t.ProximoEstado = def6;
        def5.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = ")";
        t.ProximoEstado = def7;
        def6.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "=";
        t.ProximoEstado = def8;
        def7.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = def9;
        def8.addTransicao(t);

        def.EstadoInicial = def1;
        def.Estados.add(def1);
        def.Estados.add(def2);
        def.Estados.add(def3);
        def.Estados.add(def4);
        def.Estados.add(def5);
        def.Estados.add(def6);
        def.Estados.add(def7);
        def.Estados.add(def8);
        def.Estados.add(def9);

        automato = def;
    }
}
