package Classes.GeradorCodigo;

public class LinhaAssembly {

    public String Label;
    public String Instrucao;
    public String Operador;
    public int Linha;

    public LinhaAssembly(String l, String i, String o) {
        Label = l;
        Instrucao = i;
        Operador = o;
    }

}
