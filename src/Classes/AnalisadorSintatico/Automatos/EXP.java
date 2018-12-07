package Classes.AnalisadorSintatico.Automatos;

import Classes.AnalisadorSintatico.Automato;
import Classes.AnalisadorSintatico.Estado;
import Classes.AnalisadorSintatico.Transicao;
import Classes.Token;

public abstract class EXP {

    public static Automato automato;

    // configura os autômatos EXP e EB, que são interdependentes
    public static void InicializarAutomato() {

        Transicao t = new Transicao();

        Automato EXP = new Automato();
        Automato EB = new Automato();

        // configuração do autômato EXP
        Estado exp1 = new Estado();
        Estado exp2 = new Estado();
        Estado exp3 = new Estado();
        Estado exp4 = new Estado();
        exp4.Final = true;

        t.TipoTransiscao = Transicao.REGEX;
        t.valorTransicao = "[+\\-]";
        t.ProximoEstado = exp1;
        exp1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = EB;
        t.ProximoEstado = exp2;
        exp1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.REGEX;
        t.valorTransicao = "[+\\-*/]";
        t.ProximoEstado = exp3;
        exp2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.VAZIO;
        t.ProximoEstado = exp4;
        exp2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = EB;
        t.ProximoEstado = exp2;
        exp3.addTransicao(t);

        EXP.EstadoInicial = exp1;
        EXP.Estados.add(exp1);
        EXP.Estados.add(exp2);
        EXP.Estados.add(exp3);
        EXP.Estados.add(exp4);

        // configuração do autômato EB
        Estado eb1 = new Estado();
        Estado eb2 = new Estado();
        Estado eb3 = new Estado();
        Estado eb4 = new Estado();
        Estado eb5 = new Estado();
        Estado eb6 = new Estado();
        Estado eb7 = new Estado();
        Estado eb8 = new Estado();
        eb8.Final = true;

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "(";
        t.ProximoEstado = eb2;
        eb1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.NUMERO;
        t.ProximoEstado = eb8;
        eb1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.IDENTIFICADOR;
        t.ProximoEstado = eb8;
        eb1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "FN";
        t.ProximoEstado = eb3;
        eb1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.TOKEN;
        t.TokenTransicao = Token.PREDEFINIDO;
        t.ProximoEstado = eb5;
        eb1.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = EXP;
        t.ProximoEstado = eb4;
        eb2.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = ")";
        t.ProximoEstado = eb8;
        eb4.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.REGEX;
        t.valorTransicao = "[A-Z]";
        t.ProximoEstado = eb5;
        eb3.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = "(";
        t.ProximoEstado = eb6;
        eb5.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.AUTOMATO;
        t.TransicaoAutomato = EXP;
        t.ProximoEstado = eb7;
        eb6.addTransicao(t);

        t = new Transicao();
        t.TipoTransiscao = Transicao.LITERAL;
        t.valorTransicao = ")";
        t.ProximoEstado = eb8;
        eb7.addTransicao(t);

        EB.EstadoInicial = eb1;
        EB.Estados.add(eb1);
        EB.Estados.add(eb2);
        EB.Estados.add(eb3);
        EB.Estados.add(eb4);
        EB.Estados.add(eb5);
        EB.Estados.add(eb6);
        EB.Estados.add(eb7);
        EB.Estados.add(eb8);

        automato = EXP;
    }
}
