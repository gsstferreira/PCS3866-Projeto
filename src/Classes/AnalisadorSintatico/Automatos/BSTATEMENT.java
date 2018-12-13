package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

import java.util.List;

public abstract class BSTATEMENT {

    public static Automato automato;

    // configura o autômato BSTATEMENT
    public static void InicializarAutomato(List<Automato> automatos) {

        Transicao t;

        Automato bstatement = new Automato();

        Estado bstatement1 = new Estado();
        Estado bstatement2 = new Estado();
        Estado bstatement3 = new Estado();
        Estado bstatement4 = new Estado();
        bstatement4.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = bstatement2;
        bstatement1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = ":";
        t.ProximoEstado = bstatement3;
        bstatement2.addTransicao(t);

        for (Automato a:automatos) {
            t = new Transicao();
            t.TipoTransiscao = Transicao.AUTOMATO;
            t.TransicaoAutomato = a;
            t.ProximoEstado = bstatement4;
            bstatement1.addTransicao(t);
        }

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = bstatement2;
        bstatement1.addTransicao(t);

        for (Automato a:automatos) {
            t = new Transicao();
            t.TipoTransiscao = Transicao.AUTOMATO;
            t.TransicaoAutomato = a;
            t.ProximoEstado = bstatement4;
            bstatement3.addTransicao(t);
        }

        bstatement.EstadoInicial = bstatement1;
        bstatement.Estados.add(bstatement1);
        bstatement.Estados.add(bstatement2);
        bstatement.Estados.add(bstatement3);
        bstatement.Estados.add(bstatement4);

        automato = bstatement;
    }
}
