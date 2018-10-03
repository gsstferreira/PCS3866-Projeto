package Classes;

public class Evento {

    public MotorEvento Motor;
    public String Descricao;
    public int Tipo;
    public long TempoDeOrigem;
    public long TempoAgendado;

    public Evento(MotorEvento motor, String descr, int tipo, long tOrigem, long tExec) {

        this.Motor = motor;
        this.Descricao = descr;
        this.Tipo = tipo;

        this.TempoDeOrigem = tOrigem;
        this.TempoAgendado = tExec;

    }

    public static final int EV_VAZIO = -1;
    public static final int EV_LER_ARQUIVO = 0;
    public static final int EV_LER_LINHA = 1;
    public static final int EV_LER_SIMBOLOS = 2;
    public static final int EV_LER_SIMBOLOS_UTEIS = 3;
    public static final int EV_CLASSIFICAR_TOKENS = 4;
    public static final int EV_RECLASSIFICAR_TOKENS = 5;


}
