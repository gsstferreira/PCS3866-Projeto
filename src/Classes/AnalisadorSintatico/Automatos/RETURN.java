package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class RETURN {

    public static Automato automato;

    // configura o autômato RETURN
    public static void InicializarAutomato() {

        Transicao t;

        Automato _return = new Automato();

        Estado return1 = new Estado();
        Estado return2 = new Estado();
        return2.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "RETURN";
        t.ProximoEstado = return2;
        return1.addTransicao(t);

        _return.EstadoInicial = return1;
        _return.Estados.add(return1);
        _return.Estados.add(return2);

        automato = _return;
    }
}
