package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class DATA {

    public static Automato automato;

    // configura o autômato DATA
    public static void InicializarAutomato() {

        Transicao t;

        Automato data = new Automato();

        Estado data1 = new Estado();
        Estado data2 = new Estado();
        Estado data3 = new Estado();
        Estado data4 = new Estado();
        Estado data5 = new Estado();
        data5.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "DATA";
        t.ProximoEstado = data2;
        data1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.REGEX;
        t.valorTransicao = "[\\+|\\-]";
        t.ProximoEstado = data3;
        data2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.NUMERO;
        t.ProximoEstado = data4;
        data2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.NUMERO;
        t.ProximoEstado = data4;
        data3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.VAZIO;
        t.ProximoEstado = data5;
        data4.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = ",";
        t.ProximoEstado = data2;
        data4.addTransicao(t);


        data.EstadoInicial = data1;
        data.Estados.add(data1);
        data.Estados.add(data2);
        data.Estados.add(data3);
        data.Estados.add(data4);
        data.Estados.add(data5);

        automato = data;
    }
}
