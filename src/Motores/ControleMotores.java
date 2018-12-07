package Motores;

import Classes.Evento;
import Classes.MotorEvento;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class ControleMotores {

    // flag para erros durante a análise
    public static boolean ErroDeLeitura;

    // descrição do erro
    public static String DescricaoErro;

    // ponteiro para leitor de arquivos
    static FileInputStream ArquivoOrigem;

    // lista de auxílio
    private static List<MotorEvento> Motores;

    // instancia os motores de evento e lança o evento inicial de leitura de arquivo
    public static void InicializaMotores(FileInputStream fileInputStream) {

        ErroDeLeitura = false;
        Motores = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            // criação dos motores
            Motores.add(new MotorEvento());
        }
        ArquivoOrigem = fileInputStream;

        Evento eventoInicial = new Evento(Evento.LER_ARQUIVO,0,0);
        Motores.get(0).AdicionarEvento(eventoInicial);
    }

    // retorna motor de nível especificado
    public static MotorEvento AcessoMotor(int nivel) { return Motores.get(nivel -1); }
    // verifica se a análise léxica deve continuar (eventos a serem executados)
    public static boolean ContinuarAnalise(int min, int max) {

        int n = 0;
        for(int i = min -1; i <= max - 1; i++) {
            n += Motores.get(i).EventosNaFila();
        }
        return n > 0;
    }
}
