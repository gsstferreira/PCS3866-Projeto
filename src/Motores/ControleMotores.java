package Motores;

import Classes.Evento;
import Classes.MotorEvento;

import java.io.FileInputStream;

public abstract class ControleMotores {

    public static boolean ErroDeLeitura;    // flag para erros durante a análise léxica
    public static String DescricaoErro;     // descrição do erro

    static MotorEvento MotorNivel_1;        // motor nível 1
    static MotorEvento MotorNivel_2;        // motor nível 2
    static MotorEvento MotorNivel_3;        // motor nível 3
    static MotorEvento MotorNivel_4;        // motor nível 4
    static MotorEvento MotorNivel_5;        // motor nível 5
    static MotorEvento MotorNivel_6;        // motor nível 6
    static FileInputStream ArquivoOrigem;   // ponteiro para leitor de arquivos

    // instancia os motores de evento e lança o evento inicial de leitura de arquivo
    public static void InicializaMotores(FileInputStream fileInputStream) {

        ErroDeLeitura = false;
        MotorNivel_1 = new MotorEvento();
        MotorNivel_2 = new MotorEvento();
        MotorNivel_3 = new MotorEvento();
        MotorNivel_4 = new MotorEvento();
        MotorNivel_5 = new MotorEvento();
        MotorNivel_6 = new MotorEvento();

        ArquivoOrigem = fileInputStream;

        Evento eventoInicial = new Evento(Evento.EV_LER_ARQUIVO,0,0);
        MotorNivel_1.AdicionarEvento(eventoInicial);
    }
    // verifica se a análise léxica deve continuar (eventos a serem executados)
    public static boolean ContinuarAnalise() {

        int n = 0;
        n += MotorNivel_1.EventosNaFila();
        n += MotorNivel_2.EventosNaFila();
        n += MotorNivel_3.EventosNaFila();
        n += MotorNivel_4.EventosNaFila();
        n += MotorNivel_5.EventosNaFila();
        // eventos de nível 6 são executados somente
        // após o término dos eventos de nível 5.
        return n > 0;
    }

    // verifica se a análise léxica do nível 6 deve continuar (eventos na fila)
    public static boolean ContinuarAnaliseNivel6() {
        return MotorNivel_6.EventosNaFila() > 0;
    }
}
