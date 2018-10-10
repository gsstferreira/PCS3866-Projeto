package Motores;

import Classes.Evento;
import Classes.MotorEvento;

import java.io.FileInputStream;

public abstract class ControleMotores {

    public static boolean ErroDeLeitura;

    public static MotorEvento MotorNivel_1;
    public static MotorEvento MotorNivel_2;
    public static MotorEvento MotorNivel_3;
    public static MotorEvento MotorNivel_4;
    public static MotorEvento MotorNivel_5;
    public static MotorEvento MotorNivel_6;

    public static FileInputStream ArquivoOrigem;

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

    public static boolean ContinuarAnalise() {

        int n = 0;
        n += MotorNivel_1.EventosNaFila();
        n += MotorNivel_2.EventosNaFila();
        n += MotorNivel_3.EventosNaFila();
        n += MotorNivel_4.EventosNaFila();
        n += MotorNivel_5.EventosNaFila();
        n += MotorNivel_6.EventosNaFila();

        return n > 0;
    }


}
