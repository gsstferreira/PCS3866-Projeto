package GeradorCodigo.Metodos;

import Classes.Token;

import java.util.*;

public abstract class EXPtoRPN {

    public static List<Token> ExpToRPN(List<Token> tokens) {

        List<Token> lt = new ArrayList<>();
        Stack<Token> st = new Stack<>();

        Map<String,Integer> peso = new HashMap<>();
        peso.put("*",3);
        peso.put("/",3);
        peso.put("+",2);
        peso.put("-",2);
        peso.put("(",1);

        for (Token t:tokens) {
            switch (t.Tipo) {
                case Token.IDENTIFICADOR:
                    lt.add(t);
                    break;
                case Token.NUMERO:
                    lt.add(t);
                    break;
                case Token.OPERADOR_SINALIZADOR:
                    if(t.Token.equals("(")) {
                        st.push(t);
                    }
                    else if (t.Token.equals(")")) {
                        while (!st.peek().Token.equals("(")) {
                            lt.add(st.pop());
                        }
                        st.pop();
                    }
                    break;
                case Token.OPERADOR:
                    while (!st.empty() && peso.get(st.peek().Token) >= peso.get(t.Token)) {
                        lt.add(st.pop());
                    }
                    st.push(t);
                    break;
            }
        }

        while (!st.empty()) {
            lt.add(st.pop());
        }
        return lt;
    }
}
