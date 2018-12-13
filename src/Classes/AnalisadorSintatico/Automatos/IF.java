package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class IF {

    public static Automato automato;

    // configura o autômato IF
    public static void InicializarAutomato(Automato exp) {

        Transicao t;

        Automato _if = new Automato();

        Estado if1 = new Estado();
        Estado if2 = new Estado();
        Estado if3 = new Estado();
        Estado if4 = new Estado();
        Estado if5 = new Estado();
        Estado if6 = new Estado();
        Estado if7 = new Estado();
        if7.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "IF";
        t.ProximoEstado = if2;
        if1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = if3;
        if2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.OPERADOR_COMPARADOR;
        t.ProximoEstado = if4;
        if3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = if5;
        if4.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "THEN";
        t.ProximoEstado = if6;
        if5.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = if7;
        if6.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.STOP;
        t.ProximoEstado = if7;
        if6.addTransicao(t);

        _if.EstadoInicial = if1;
        _if.Estados.add(if1);
        _if.Estados.add(if2);
        _if.Estados.add(if3);
        _if.Estados.add(if4);
        _if.Estados.add(if5);
        _if.Estados.add(if6);
        _if.Estados.add(if7);

        automato = _if;
    }
}
