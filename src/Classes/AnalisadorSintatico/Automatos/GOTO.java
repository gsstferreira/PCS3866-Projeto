package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class GOTO {

    public static Automato automato;

    // configura o autômato GOTO
    public static void InicializarAutomato() {

        Transicao t;

        Automato _goto = new Automato();

        Estado goto1 = new Estado();
        Estado goto2 = new Estado();
        Estado goto3 = new Estado();
        goto3.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "GOTO";
        t.ProximoEstado = goto2;
        goto1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = goto3;
        goto2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.STOP;
        t.ProximoEstado = goto3;
        goto2.addTransicao(t);

        _goto.EstadoInicial = goto1;
        _goto.Estados.add(goto1);
        _goto.Estados.add(goto2);
        _goto.Estados.add(goto3);

        automato = _goto;
    }
}
