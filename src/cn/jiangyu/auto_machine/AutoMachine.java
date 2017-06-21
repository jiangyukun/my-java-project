package cn.jiangyu.auto_machine;

import java.util.List;

/**
 * 自动机进行词法分析
 * Created by jiangyukun on 2017/6/21.
 */
public class AutoMachine {
    private String string = "abc";
    private int current;

    public static void main(String[] args) {
        new AutoMachine();
    }

    private List<String> parse() {

        return null;
    }

    private String getLetter() {
        String type = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            char c = this.getNextChar();
            switch (c) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    sb.append(c);
                    type = Type.LETTER;
                    break;

                case ' ':

                    break;
                case '>':

                    break;
                case '<':

                    break;
                default:
                    break;
            }

        }


//        return null;
    }

    private char getNextChar() {
        return string.charAt(this.current++);
    }
}
