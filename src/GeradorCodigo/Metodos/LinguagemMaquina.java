package GeradorCodigo.Metodos;

import Classes.GeradorCodigo.LinhaAssembly;

import java.util.ArrayList;
import java.util.List;

public abstract class LinguagemMaquina {

    public static List<LinhaAssembly> Montar(List<LinhaAssembly> codigo) {

        for (LinhaAssembly l:codigo) {

            l.Instrucao = MnemonicoParaHex(l.Instrucao);

            if(l.Operador.startsWith("\\")) {
                String s = l.Operador.substring(1);

                s = String.format("%03X",Integer.parseInt(s));
                l.Operador = s;
            }
            else {
                for (LinhaAssembly ll:codigo) {

                    if(ll.Label.equals(l.Operador)) {
                        l.Operador = String.format("%03X",ll.Linha);
                        break;
                    }
                }
            }
        }

        return codigo;
    }


    private static String MnemonicoParaHex(String mne) {

        switch (mne) {
            case "JP":
                return "0";
            case "JZ":
                return "1";
            case "JN":
                return "2";
            case "LV":
                return "3";
            case "ADD":
                return "4";
            case "SUB":
                return "5";
            case "MUL":
                return "6";
            case "DIV":
                return "7";
            case "LD":
                return "8";
            case "MM":
                return "9";
            case "SC":
                return "A";
            case "RS":
                return "B";
            case "HM":
                return "C";
            case "GD":
                return "D";
            case "PD":
                return "E";
            case "OS":
                return "F";
            default:
                return "0";
        }
    }
}
