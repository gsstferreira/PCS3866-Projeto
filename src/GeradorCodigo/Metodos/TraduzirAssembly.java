package GeradorCodigo.Metodos;

import Classes.GeradorCodigo.LinhaAssembly;
import Classes.GeradorCodigo.LinhaCodInter;
import Classes.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class TraduzirAssembly {

    private static int branch = 0;
    private static Stack<String> returnLoop = new Stack<>();
    private static Stack<String> returnSub = new Stack<>();
    private static List<String> goSubLabels = new ArrayList<>();

//    private static int DataLen = 0;
//    private static int currentData = 0;

    public static List<LinhaAssembly> TraduzirLinha(LinhaCodInter l) {

        List<LinhaAssembly> ll = new ArrayList<>();

        switch (l.Tipo) {
            case LinhaCodInter.ASSIGN:
                ll = RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),l.Variaveis.get(0).Token);
                ll.get(0).Label = l.Label;
                return ll;
//            case LinhaCodInter.DATA:
//                DataLen = l.Variaveis.size();
//                ll.add(new LinhaAssembly("!","JP","\\002"));
//                for(int i = 0; i < l.Variaveis.size(); i++) {
//                    ll.add(new LinhaAssembly(String.format("DATA_%d",i),"JP",l.Variaveis.get(i).Token));
//                }
//                return ll;
            case LinhaCodInter.FOR:
                ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),l.Variaveis.get(0).Token));
                ll.get(0).Label = l.Label;

                String loopLabel = String.format("L_%s_%d",l.Variaveis.get(0).Token, branch);
                branch++;
                returnLoop.push(loopLabel);

                ll.add(new LinhaAssembly(loopLabel,"LV","\\0"));
                return ll;
            case LinhaCodInter.GOSUB:
                String subLabel = String.format("S_%s_%d",l.Variaveis.get(0).Token, branch);
                branch++;
                returnSub.push(subLabel);
                goSubLabels.add(l.Variaveis.get(0).Token);

                ll.add(new LinhaAssembly(l.Label,"JP",l.Variaveis.get(0).Token));
                ll.add(new LinhaAssembly(subLabel,"LV","\\000"));

                return ll;
            case LinhaCodInter.GOTO:
                ll.add(new LinhaAssembly(l.Label,"JP",l.Variaveis.get(0).Token));
                return ll;
            case LinhaCodInter.IF:
                Token t = l.Variaveis.get(1);

                switch (t.Token) {
                    case ">":
                        ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                        ll.add(new LinhaAssembly("!","MM","IF_VAR2"));
                        ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),"IF_VAR1"));


                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","JN",l.Variaveis.get(2).Token));
                        break;
                    case "<":
                        ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),"IF_VAR2"));
                        ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                        ll.add(new LinhaAssembly("!","MM","IF_VAR1"));

                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","JN",l.Variaveis.get(2).Token));
                        break;
                    case ">=":
                        ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                        ll.add(new LinhaAssembly("!","MM","IF_VAR2"));
                        ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),"IF_VAR1"));

                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","JN",l.Variaveis.get(2).Token));
                        ll.add(new LinhaAssembly("!","JP",l.Variaveis.get(2).Token));
                        break;
                    case "<=":
                        ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),"IF_VAR2"));
                        ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                        ll.add(new LinhaAssembly("!","MM","IF_VAR1"));

                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","JN",l.Variaveis.get(2).Token));
                        ll.add(new LinhaAssembly("!","JP",l.Variaveis.get(2).Token));
                        break;
                    case "=":
                        ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),"IF_VAR2"));
                        ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                        ll.add(new LinhaAssembly("!","MM","IF_VAR1"));

                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","JZ",l.Variaveis.get(2).Token));
                        break;
                    case "<>":
                        ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),"IF_VAR2"));
                        ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                        ll.add(new LinhaAssembly("!","MM","IF_VAR1"));

                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));
                        ll.add(new LinhaAssembly("!","SUB","IF_VAR2"));

                        String lb = String.format("NOT_%s",l.Variaveis.get(2).Token);
                        ll.add(new LinhaAssembly("!","JZ",lb));
                        ll.add(new LinhaAssembly("!","JP",l.Variaveis.get(2).Token));
                        ll.add(new LinhaAssembly(lb,"LV","\\000"));
                        break;
                }
                ll.get(0).Label = l.Label;
                return  ll;
            case LinhaCodInter.NEXT:

                if(l.Variaveis.get(1).Token.equals("-")) {
                    ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(1),"TO_VAR"));
                    ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                    ll.add(new LinhaAssembly("!","SUB","TO_VAR"));
                }
                else {
                    ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(1),"TO_VAR"));
                    ll.add(new LinhaAssembly("!","SUB",l.Variaveis.get(0).Token));
                }
                String breakLoop = String.format("B%s",returnLoop.peek());
                ll.add(new LinhaAssembly("!","JN",breakLoop));
                ll.add(new LinhaAssembly("!","JZ",breakLoop));

                ll.addAll(RPNtoMVN.TransformarExpressaoRPN(l.Expressoes.get(0),"STP_VAR"));
                ll.add(new LinhaAssembly("!","LD",l.Variaveis.get(0).Token));
                ll.add(new LinhaAssembly("!","ADD","STP_VAR"));
                ll.add(new LinhaAssembly("!","MM",l.Variaveis.get(0).Token));

                ll.add(new LinhaAssembly("!","JP",returnLoop.pop()));
                ll.add(new LinhaAssembly(breakLoop,"LV","\\000"));

                return ll;
            case LinhaCodInter.PRINT:
                for (Token tt:l.Variaveis) {

                    if(tt.Tipo == Token.STRING) {
                        ll.add(new LinhaAssembly("!","OS","\\001"));
                        for (int i = 0; i < tt.Token.length(); i++) {
                            char c = tt.Token.charAt(i);
                            String ascii = String.format("%03d",(int)c);
                            ll.add(new LinhaAssembly("!","PD",String.format("\\%s",ascii)));
                        }
                        ll.add(new LinhaAssembly("!","OS","\\000"));
                    }
                    else {
                        ll.add(new LinhaAssembly("!","PD",tt.Token));
                    }
                }
                ll.get(0).Label = l.Label;
                return ll;
            case LinhaCodInter.RETURN:
                ll.add(new LinhaAssembly("!","JP",returnSub.pop()));
                return ll;
//            case LinhaCodInter.READ:
//                for (Token t1:l.Variaveis) {
//                    ll.add(new LinhaAssembly("!","LD",String.format("DATA_%d",currentData)));
//                    ll.add(new LinhaAssembly("!","MM",t1.Token));
//                    currentData++;
//                    if(currentData > DataLen) {
//                        currentData = 0;
//                    }
//                }
//                ll.get(0).Label = l.Label;
//                return ll;
        }
        return ll;
    }

    public static List<LinhaAssembly> IsolarSubrotinas(List<LinhaAssembly> linhas) {

        for (int i = 0; i < linhas.size(); i++) {

            LinhaAssembly l = linhas.get(i);

            if(goSubLabels.contains(l.Label)) {
                linhas.add(i,new LinhaAssembly("!","JP","STOP"));
                i++;
            }
        }

        return linhas;
    }

    public static List<LinhaAssembly> ConfigurarMemoria(List<LinhaAssembly> linhas) {

        List<String> labelsRef = new ArrayList<>();

        for (LinhaAssembly l:linhas) {

            if(!labelsRef.contains(l.Operador) && !l.Operador.startsWith("\\")) {
                labelsRef.add(l.Operador);
            }
        }

        for (LinhaAssembly l:linhas) {
            labelsRef.remove(l.Label);
        }

        List<LinhaAssembly> memoria = new ArrayList<>();

        for (String s:labelsRef) {
            memoria.add(new LinhaAssembly(s,"JP","\\000"));
        }
        return memoria;

    }
}