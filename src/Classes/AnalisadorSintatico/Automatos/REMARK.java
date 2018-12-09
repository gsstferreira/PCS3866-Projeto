package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class REMARK {

    public static Automato automato;

    // configura o autômato REMARK
    public static void InicializarAutomato() {

        Transicao t;

        Automato rem = new Automato();

        Estado rem1 = new Estado();
        Estado rem2 = new Estado();
        Estado rem3 = new Estado();
        rem3.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "REM";
        t.ProximoEstado = rem2;
        rem1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.STRING;
        t.ProximoEstado = rem3;
        rem2.addTransicao(t);

        rem.EstadoInicial = rem1;
        rem.Estados.add(rem1);
        rem.Estados.add(rem2);
        rem.Estados.add(rem3);

        automato = rem;
    }
}
