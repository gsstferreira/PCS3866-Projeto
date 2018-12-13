package Classes.AnalisadorLexico;

public class Evento {
    public int Tipo;
    public long TempoDeOrigem;
    public long TempoAgendado;

    public Evento(int tipo, long tOrigem, long tExec) {

        this.Tipo = tipo;

        this.TempoDeOrigem = tOrigem;
        this.TempoAgendado = tExec;

    }

    // lista de possíveis eventos
    public static final int VAZIO = -1;
    public static final int LER_ARQUIVO = 0;
    public static final int LER_LINHA = 1;
    public static final int LER_SIMBOLOS = 2;
    public static final int LER_SIMBOLOS_UTEIS = 3;
    public static final int CLASSIFICAR_TOKENS = 4;
    public static final int RECLASSIFICAR_TOKENS = 5;
}
