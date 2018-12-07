package Classes.AnalisadorSintatico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Estado {

    public boolean Final;
    private List<Transicao> Transicoes;


    public Estado() {
        Transicoes = new ArrayList<>();
        Final = false;
    }

    public List<Transicao> getTransicoes() {
        return Transicoes;
    }

    public void addTransicao(Transicao t) {
        Transicoes.add(t);
        OrdenarTransicoes();
    }

    private void OrdenarTransicoes() {
        Transicoes.sort(Comparator.comparingInt(o -> o.TipoTransiscao));
    }
}
