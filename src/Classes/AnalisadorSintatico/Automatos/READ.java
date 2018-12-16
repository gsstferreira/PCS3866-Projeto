package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class READ {

    public static Automato automato;

    // configura o autômato READ
    public static void InicializarAutomato() {

        Transicao t;

        Automato read = new Automato();

        Estado read1 = new Estado();
        Estado read2 = new Estado();
        Estado read3 = new Estado();
        Estado read4 = new Estado();
        read4.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "READ";
        t.ProximoEstado = read2;
        read1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = read3;
        read2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = ",";
        t.ProximoEstado = read2;
        read3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.VAZIO;
        t.ProximoEstado = read4;
        read3.addTransicao(t);

        read.EstadoInicial = read1;
        read.Estados.add(read1);
        read.Estados.add(read2);
        read.Estados.add(read3);
        read.Estados.add(read4);

        automato = read;
    }
}
