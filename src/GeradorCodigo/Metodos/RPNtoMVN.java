package GeradorCodigo.Metodos;

import Classes.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class RPNtoMVN {

    public static String TransformarExpressaoRPN(List<Token> exp_rpn) {

        int stack = 0;
        List<Token> tl = new ArrayList<>(exp_rpn);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < tl.size()-1; i++) {

            Token t = tl.get(i);
            Token t2 = tl.get(i+1);

            if(t.Tipo == Token.IDENTIFICADOR) {

                if(t2.Tipo == Token.IDENTIFICADOR || t2.Tipo == Token.NUMERO) {
                    sb.append(String.format("LD  [%s]\nMM  [R%d]\n",t.Token,stack));
                    stack++;
                }
                else if(t2.Tipo == Token.OPERADOR){

                    stack--;

                    switch (t2.Token) {

                        case "+":
                            sb.append(String.format("ADD [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                        case "-":
                            sb.append(String.format("SUB [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                        case "*":
                            sb.append(String.format("MUL [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                        case "/":
                            sb.append(String.format("DIV [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                    }
                    stack++;
                }

            }
            else if(t.Tipo == Token.NUMERO) {

                if(t2.Tipo == Token.IDENTIFICADOR || t2.Tipo == Token.NUMERO) {
                    sb.append(String.format("LV %s\nMM  [R%d]\n",t.Token,stack));
                    stack++;
                }
                else if(t2.Tipo == Token.OPERADOR){

                    stack--;

                    switch (t2.Token) {

                        case "+":
                            sb.append(String.format("ADD [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                        case "-":
                            sb.append(String.format("SUB [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                        case "*":
                            sb.append(String.format("MUL [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                        case "/":
                            sb.append(String.format("DIV [%s]\nMM  [R%d]\n",t.Token,stack));
                            break;
                    }
                    stack++;
                }

            }

            else if(t.Tipo == Token.OPERADOR) {
                if(t2.Tipo == Token.OPERADOR) {

                    stack--;

                    int x = stack;
                    sb.append(String.format("LD  [R%d]\n",x - 1));

                    switch (t2.Token) {

                        case "+":
                            sb.append(String.format("ADD [R%d]\nMM  [R%d]\n",x,x-1));
                            break;
                        case "-":
                            sb.append(String.format("SUB [R%d]\nMM  [R%d]\n",x,x-1));
                            break;
                        case "*":
                            sb.append(String.format("MUL [R%d]\nMM  [R%d]\n",x,x-1));
                            break;
                        case "/":
                            sb.append(String.format("DIV [R%d]\nMM  [R%d]\n",x,x-1));
                            break;
                    }
                }
            }
        }


        return sb.toString();
    }

}
