package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class GOSUB {

    public static Automato automato;

    // configura o autômato GOSUB
    public static void InicializarAutomato() {

        Transicao t;

        Automato gosub = new Automato();

        Estado gosub1 = new Estado();
        Estado gosub2 = new Estado();
        Estado gosub3 = new Estado();
        gosub3.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "GOSUB";
        t.ProximoEstado = gosub2;
        gosub1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = gosub3;
        gosub2.addTransicao(t);

        gosub.EstadoInicial = gosub1;
        gosub.Estados.add(gosub1);
        gosub.Estados.add(gosub2);
        gosub.Estados.add(gosub3);

        automato = gosub;
    }
}
