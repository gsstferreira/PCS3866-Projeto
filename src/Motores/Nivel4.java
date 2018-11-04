package Motores;

import Classes.Evento;
import Classes.Memoria;
import Classes.Simbolo;
import Metodos.CategorizadorCaractere;

public abstract class Nivel4 {

    private static int NumeroLinha = 0;
    private static int NumeroChar = 0;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.MotorNivel_4.ObterProximoEvento(tempo);

        if(e.Tipo == Evento.EV_LER_SIMBOLOS_UTEIS) {

            Simbolo s = Memoria.Simbolos.get(NumeroLinha).get(NumeroChar);

            if(s != null) {

                NumeroChar++;
                CategorizadorCaractere.IdentificarSimbolo(s);

                if(s.Tipo == Simbolo.S_INVALIDO) {
                    ControleMotores.ErroDeLeitura = true;
                    System.out.print(String.format("Erro: Classificação do Símbolo (linha %d, %d), interrompendo análise.\n",NumeroLinha+1,NumeroChar+1));
                }

                else if(NumeroChar == Memoria.Simbolos.get(NumeroLinha).size()) {
                    NumeroLinha++;
                    NumeroChar = 0;
                }

                Evento e2 = new Evento(Evento.EV_CLASSIFICAR_TOKENS,tempo,tempo+1);
                ControleMotores.MotorNivel_5.AdicionarEvento(e2);
            }
        }
    }
}
