import Classes.Memoria;
import Classes.MotorEvento;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    private static MotorEvento MotorNivel_0;
    private static MotorEvento MotorNivel_1;
    private static MotorEvento MotorNivel_2;
    private static MotorEvento MotorNivel_3;
    private static MotorEvento MotorNivel_4;
    private static MotorEvento MotorNivel_5;
    private static MotorEvento MotorNivel_6;

    private static int ContadorTempo;

    public static void main(String[] args) {

        Memoria.InializarMemoria();

        MotorNivel_0 = new MotorEvento();
        MotorNivel_1 = new MotorEvento();
        MotorNivel_2 = new MotorEvento();
        MotorNivel_3 = new MotorEvento();
        MotorNivel_4 = new MotorEvento();
        MotorNivel_5 = new MotorEvento();
        MotorNivel_6 = new MotorEvento();

        ContadorTempo = 0;

        try {
            FileInputStream fileInputStream = new FileInputStream("teste.txt");
            fileInputStream.read();
        } catch (IOException e) {
            System.out.print("Arquivo 'teste.txt'não foi encontrado.\n");
        }
    }
}
