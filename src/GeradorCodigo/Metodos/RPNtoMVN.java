package GeradorCodigo.Metodos;

import Classes.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class RPNtoMVN {

    public static String TransformarExpressaoRPN(List<Token> exp_rpn) {

        Stack<String> stack = new Stack<>();
        List<Token> tl = new ArrayList<>(exp_rpn);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < tl.size()-1; i++) {

            Token t = tl.get(i);
            Token t2 = tl.get(i+1);

            if(t.Tipo == Token.IDENTIFICADOR || t2.Tipo == Token.NUMERO) {

                if(t2.Tipo == Token.IDENTIFICADOR || t2.Tipo == Token.NUMERO) {
                    sb.append(String.format("LD [%s]\nMM [P%d]\n",t.Token,stack.size()));
                    stack.push("");
                }
                else if(t2.Tipo == Token.OPERADOR){

                    stack.pop();

                    switch (t2.Token) {

                        case "+":
                            sb.append(String.format("ADD [%s]\nMM [P%d]\n",t.Token,stack.size()));
                            break;
                        case "-":
                            sb.append(String.format("SUB [%s]\nMM [P%d]\n",t.Token,stack.size()));
                            break;
                        case "*":
                            sb.append(String.format("MUL [%s]\nMM [P%d]\n",t.Token,stack.size()));
                            break;
                        case "/":
                            sb.append(String.format("DIV [%s]\nMM [P%d]\n",t.Token,stack.size()));
                            break;
                    }
                    stack.push("");
                }

            }
            else if(t.Tipo == Token.OPERADOR) {
                if(t2.Tipo == Token.OPERADOR) {

                    stack.pop();

                    int x = stack.size();
                    sb.append(String.format("LD [P%d]\n",x - 1));

                    switch (t2.Token) {

                        case "+":
                            sb.append(String.format("ADD [P%d]\nMM [P%d]\n",x,x-1));
                            break;
                        case "-":
                            sb.append(String.format("SUB [P%d]\nMM [P%d]\n",x,x-1));
                            break;
                        case "*":
                            sb.append(String.format("MUL [P%d]\nMM [P%d]\n",x,x-1));
                            break;
                        case "/":
                            sb.append(String.format("DIV [P%d]\nMM [P%d]\n",x,x-1));
                            break;
                    }
                }
            }
        }


        return sb.toString();
    }

}
