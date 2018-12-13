package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

import java.util.List;

public abstract class PROGRAM {

    public static Automato automato;

    // configura o autômato PROGRAM
    public static void InicializarAutomato(Automato bstatement) {

        Transicao t;

        Automato program = new Automato();

        Estado program1 = new Estado();
        Estado program2 = new Estado();
        Estado program3 = new Estado();
        program3.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = bstatement;
        t.ProximoEstado = program2;
        program1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = bstatement;
        t.ProximoEstado = program2;
        program2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.END;
        t.ProximoEstado = program3;
        program2.addTransicao(t);


        program.EstadoInicial = program1;
        program.Estados.add(program1);
        program.Estados.add(program2);
        program.Estados.add(program3);

        automato = program;
    }
}
