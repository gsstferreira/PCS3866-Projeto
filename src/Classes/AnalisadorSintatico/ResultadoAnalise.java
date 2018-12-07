package Classes.AnalisadorSintatico;

import Classes.Token;

import java.util.List;

public class ResultadoAnalise {

    public List<Token> linhaRestante;
    public boolean Ok;


    public ResultadoAnalise(List<Token> restante, boolean ok) {
        linhaRestante = restante;
        Ok = ok;
    }
}
