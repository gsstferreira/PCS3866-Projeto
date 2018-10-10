package Motores;

import Classes.Evento;
import Classes.Memoria;
import Metodos.SeparadorLinha;

public abstract class Nivel1 {

    private static int NumeroLinha = 0;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.MotorNivel_1.ObterProximoEvento(tempo);

        if(e.Tipo == Evento.EV_LER_ARQUIVO) {

            NumeroLinha++;

            String linha = SeparadorLinha.lerLinha(ControleMotores.ArquivoOrigem);

            if(linha == null) {
                System.out.print(String.format("Erro: Leitura de Linha (%d), interrompendo análise.\n",NumeroLinha+1));
                ControleMotores.ErroDeLeitura = true;
            }
            else if(linha.isEmpty()) {
                return;
            }
            else {
                Memoria.Linhas.add(linha);

                Evento e2 = new Evento(Evento.EV_LER_ARQUIVO,tempo,tempo+1);
                ControleMotores.MotorNivel_1.AdicionarEvento(e2);
                for(int i = 0; i < linha.length(); i++) {
                    Evento ee = new Evento(Evento.EV_LER_LINHA,tempo,tempo+1);
                    ControleMotores.MotorNivel_2.AdicionarEvento(ee);
                }
            }

        }

    }

}
