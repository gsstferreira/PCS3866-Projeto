package GeradorCodigo.Metodos;

import Classes.GeradorCodigo.LinhaAssembly;
import Classes.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class RPNtoMVN {

    public static List<LinhaAssembly> TransformarExpressaoRPN(List<Token> exp_rpn, String destino) {

        int stack = 0;
        List<LinhaAssembly> gerado = new ArrayList<>();
        List<Token> tl = new ArrayList<>(exp_rpn);

        if(exp_rpn.size() == 1) {
            Token t = exp_rpn.get(0);

            if(t.Tipo == Token.IDENTIFICADOR) {
                gerado.add(new LinhaAssembly("!","LD",t.Token));
                gerado.add(new LinhaAssembly("!","MM",destino));
            }
            else {
                gerado.add(new LinhaAssembly("!","LV",String.format("\\%s",t.Token)));
                gerado.add(new LinhaAssembly("!","MM",destino));
            }
            return gerado;
        }

        for(int i = 0; i < tl.size()-1; i++) {

            Token t = tl.get(i);
            Token t2 = tl.get(i+1);

            if(t.Tipo == Token.IDENTIFICADOR) {

                if(t2.Tipo == Token.IDENTIFICADOR || t2.Tipo == Token.NUMERO) {

                    gerado.add(new LinhaAssembly("!","LD",t.Token));
                    gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));

                    stack++;
                }
                else if(t2.Tipo == Token.OPERADOR){

                    stack--;

                    switch (t2.Token) {

                        case "+":
                            gerado.add(new LinhaAssembly("!","ADD",String.format("%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                        case "-":
                            gerado.add(new LinhaAssembly("!","SUB",String.format("%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                        case "*":
                            gerado.add(new LinhaAssembly("!","MUL",String.format("%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                        case "/":
                            gerado.add(new LinhaAssembly("!","DIV",String.format("%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                    }
                    stack++;
                }

            }
            else if(t.Tipo == Token.NUMERO) {

                if(t2.Tipo == Token.IDENTIFICADOR || t2.Tipo == Token.NUMERO) {

                    gerado.add(new LinhaAssembly("!","LV",String.format("\\%s",t.Token)));
                    gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));

                    stack++;
                }
                else if(t2.Tipo == Token.OPERADOR){

                    stack--;

                    switch (t2.Token) {

                        case "+":
                            gerado.add(new LinhaAssembly("!","ADD",String.format("\\%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                        case "-":
                            gerado.add(new LinhaAssembly("!","SUB",String.format("\\%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                        case "*":
                            gerado.add(new LinhaAssembly("!","MUL",String.format("\\%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                        case "/":
                            gerado.add(new LinhaAssembly("!","DIV",String.format("\\%s",t.Token)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack)));
                            break;
                    }
                    stack++;
                }

            }

            else if(t.Tipo == Token.OPERADOR) {
                if(t2.Tipo == Token.OPERADOR) {

                    stack--;

                    int x = stack;
                    gerado.add(new LinhaAssembly("!","LD",String.format("REG%d",stack-1)));

                    switch (t2.Token) {

                        case "+":
                            gerado.add(new LinhaAssembly("!","ADD",String.format("REG%d",stack)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack-1)));
                            break;
                        case "-":
                            gerado.add(new LinhaAssembly("!","SUB",String.format("REG%d",stack)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack-1)));
                            break;
                        case "*":
                            gerado.add(new LinhaAssembly("!","MUL",String.format("REG%d",stack)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack-1)));
                            break;
                        case "/":
                            gerado.add(new LinhaAssembly("!","DIV",String.format("REG%d",stack)));
                            gerado.add(new LinhaAssembly("!","MM",String.format("REG%d",stack-1)));
                            break;
                    }
                }
            }
        }

        gerado.add(new LinhaAssembly("!","MM",destino));
        return gerado;
    }

}
