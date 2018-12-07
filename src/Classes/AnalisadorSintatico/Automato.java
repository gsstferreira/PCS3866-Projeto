package Classes.AnalisadorSintatico;

import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public class Automato {

    public Estado EstadoInicial;
    public List<Estado> Estados;

    public Automato() {
        Estados = new ArrayList<>();
    }

    public ResultadoAnalise ExecutarAutomato(List<Token> tokens) {

        boolean Erro = false;

        int parenteses = 0;
        Estado atual = EstadoInicial;

        List<Token> s = new ArrayList<>(tokens);

        while(!atual.Final &&  !Erro) {

            if(!Estados.contains(atual)) {
                return new ResultadoAnalise(s,false);
            }

            for (Transicao t:atual.getTransicoes()) {
                Erro = true;

                if(s.isEmpty()) {
                    if (t.TipoTransiscao == Transicao.VAZIO) {
                        Erro = false;
                        atual = t.ProximoEstado;
                        break;
                    }
                }

                else if(t.TipoTransiscao == Transicao.LITERAL){
                    if(s.get(0).Token.equals(t.valorTransicao)) {

                        if(t.valorTransicao.equals("(")) {
                            parenteses ++;
                        }
                        else if (t.valorTransicao.equals(")")) {
                            parenteses--;
                        }

                        atual = t.ProximoEstado;
                        s.remove(0);
                        Erro = false;
                        break;
                    }
                }
                else if (t.TipoTransiscao == Transicao.TOKEN){
                    if(s.get(0).Tipo == t.TokenTransicao) {
                        atual = t.ProximoEstado;
                        s.remove(0);
                        Erro = false;
                        break;
                    }
                }
                else if (t.TipoTransiscao == Transicao.REGEX){
                    if(s.get(0).Token.matches(t.valorTransicao)) {
                        atual = t.ProximoEstado;
                        s.remove(0);
                        Erro = false;
                        break;
                    }
                }
                else if (t.TipoTransiscao == Transicao.AUTOMATO){
                    ResultadoAnalise r = t.TransicaoAutomato.ExecutarAutomato(s);

                    if(r.Ok) {
                        s = r.linhaRestante;
                        atual = t.ProximoEstado;
                        Erro = false;
                        break;
                    }
                }
                else if (t.TipoTransiscao == Transicao.VAZIO) {
                    atual = t.ProximoEstado;
                    Erro = false;
                    break;
                }
                else {
                    break;
                }
            }
        }

        if(Erro) {
            return new ResultadoAnalise(s,false);
        }
        else {

            if(parenteses != 0) {
                return new ResultadoAnalise(s,false);
            }

            return new ResultadoAnalise(s,true);
        }
    }
}
