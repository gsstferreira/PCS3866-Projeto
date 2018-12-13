package Lexico.Motores;

import Classes.AnalisadorLexico.Evento;
import Classes.Memoria;
import Lexico.Metodos.SeparadorLinha;

public abstract class Nivel1 {

    private static int NumeroLinha = 0;
    public static boolean eof = false;

    public static void RealizarEvento(int tempo) {

        Evento e = ControleMotores.AcessoMotor(1).ObterProximoEvento(tempo);

        if(e.Tipo == Evento.LER_ARQUIVO) {

            NumeroLinha++;

            String linha = SeparadorLinha.lerLinha(ControleMotores.ArquivoOrigem);

            if(linha == null) {
                ControleMotores.DescricaoErro = String.format("Erro: Leitura de Linha (%d), interrompendo análise.\n",NumeroLinha);
                ControleMotores.ErroDeLeitura = true;
            }
            else {

                String vazia = linha.replace("\r","").replace("\n","");

                if(!vazia.isBlank()) {
                    Memoria.Linhas.add(linha);

                    for(int i = 0; i < linha.length(); i++) {
                        Evento ee = new Evento(Evento.LER_LINHA,tempo,tempo+1);
                        ControleMotores.AcessoMotor(2).AdicionarEvento(ee);
                    }
                }

                if(!eof) {
                    Evento e2 = new Evento(Evento.LER_ARQUIVO,tempo,tempo+1);
                    ControleMotores.AcessoMotor(1).AdicionarEvento(e2);
                }
            }
        }

    }

}
