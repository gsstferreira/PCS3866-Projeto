package Classes.AnalisadorSintatico;

import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public class Constante {

    public double Valor;
    public List<Integer> Referencias;

    public Constante(Token t) {

        Valor = Double.parseDouble(t.Token);
        Referencias = new ArrayList<>();
        Referencias.add(t.Linha);
    }

    public void AdicionarReferencia(int referencia) {

        if(!Referencias.contains(referencia)) {
            Referencias.add(referencia);
        }
    }
}
