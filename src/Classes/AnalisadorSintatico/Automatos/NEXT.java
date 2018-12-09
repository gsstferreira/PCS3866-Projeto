package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class NEXT {

    public static Automato automato;

    // configura o autômato NEXT
    public static void InicializarAutomato() {

        Transicao t;

        Automato next = new Automato();

        Estado next1 = new Estado();
        Estado next2 = new Estado();
        Estado next3 = new Estado();
        next3.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "NEXT";
        t.ProximoEstado = next2;
        next1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = next3;
        next2.addTransicao(t);

        next.EstadoInicial = next1;
        next.Estados.add(next1);
        next.Estados.add(next2);
        next.Estados.add(next3);

        automato = next;
    }
}
