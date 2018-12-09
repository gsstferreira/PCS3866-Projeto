package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class ASSIGN {

    public static Automato automato;

    // configura o autômato ASSIGN
    public static void InicializarAutomato(Automato exp) {

        Transicao t;

        Automato assign = new Automato();

        Estado assign1 = new Estado();
        Estado assign2 = new Estado();
        Estado assign3 = new Estado();
        Estado assign4 = new Estado();
        Estado assign5 = new Estado();
        assign5.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "LET";
        t.ProximoEstado = assign2;
        assign1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = assign3;
        assign2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "=";
        t.ProximoEstado = assign4;
        assign3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = exp;
        t.ProximoEstado = assign5;
        assign4.addTransicao(t);

        assign.EstadoInicial = assign1;
        assign.Estados.add(assign1);
        assign.Estados.add(assign2);
        assign.Estados.add(assign3);
        assign.Estados.add(assign4);
        assign.Estados.add(assign5);

        automato = assign;
    }
}
