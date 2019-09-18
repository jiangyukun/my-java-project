package cn.jiangyu.auto_machine;

import java.util.ArrayList;
import java.util.List;

public class Token {
    private String string = "#/definition/a.b.c[x.y[z]]";
    private int current;
    private TokenType type;

    public static void main(String[] args) {
        Token machine = new Token();
        List result = machine.parse();
        System.out.println(result);
    }

    public List<TokenItem> parse() {
        List<TokenItem> result = new ArrayList<>();
        char c = getNextChar();

        while (c != 13) {
            if (c == ' ') {
                c = getNextChar();
            } else {
                back();
                String tokenContent = getLetter();
                result.add(new TokenItem(this.type, tokenContent));
                c = getNextChar();
            }
        }
        return result;
    }

    private String getLetter() {
        StringBuilder sb = new StringBuilder();

        int state = 0;

        char c = this.getNextChar();
        while (true) {
            if (state == 0) {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    this.type = TokenType.identify;
                    state = 1;
                } else if (c >= '0' && c <= '9') {
                    this.type = TokenType.number;
                    state = 3;
                } else {
                    this.type = TokenType.symbol;
                    state = 4;
                }
            } else if (state == 1) {
                while ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    sb.append(c);
                    c = this.getNextChar();
                }
                state = 2;
            } else if (state == 2) {
                back();
                break;
            } else if (state == 3) {
                while (c >= '0' && c <= '9') {
                    sb.append(c);
                    c = this.getNextChar();
                }
                state = 2;
            } else {
                sb.append(c);
                break;
            }

        }
        return sb.toString();
    }

    private char getCurrent() {
        return string.charAt(this.current++);
    }

    private char getNextChar() {
        if (string.length() > this.current) {

            return string.charAt(this.current++);
        }
        this.current++;
        return 13;
    }

    private void back() {
        this.current--;
    }
}
