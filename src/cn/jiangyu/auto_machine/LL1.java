package cn.jiangyu.auto_machine;

import java.util.List;

/**
 * Created by wangji on 2019/9/18.
 */


class ClassType {
    private String className = "";
    private boolean isArray = false;
    private ClassType subType = null;

    public ClassType(String className) {
        this.className = className;
    }

    public ClassType(String className, boolean isArray, ClassType subType) {

        this.className = className;
        this.isArray = isArray;
        this.subType = subType;
    }

    public String getClassName() {
        return className;
    }
}

public class LL1 {
    public static void main(String[] args) {
        new LL1(new Token().parse()).parse();
    }

    private List<TokenItem> tokenList;
    TokenItem ahead;
    int index = 0;


    public LL1(List<TokenItem> tokenList) {
        this.tokenList = tokenList;
        this.ahead = this.tokenList.get(0);
    }


    void parse() {
        this.match(TokenType.symbol, "#");
        this.match(TokenType.symbol, "/");
        this.match(TokenType.identify, "definition");
        this.match(TokenType.symbol, "/");

        ClassType type = this.findType();
        System.out.println(type);
    }

    ClassType findType() {
        String className = "";

        while (this.export(TokenType.identify)) {
            TokenItem token = this.getNextToken();
            className += token.getContent();

            if (this.ahead == null) {
                return new ClassType(className);
            }

            if (this.export(TokenType.symbol, ".")) {
                token = this.getNextToken();
                className += token.getContent();
            } else if (this.export(TokenType.symbol, "[")) {
                this.getNextToken();
                ClassType subClassType = this.findType();
                this.export(TokenType.symbol, "]");
                return new ClassType(className, true, subClassType);
            } else {
                return new ClassType(className);
            }
        }

        return null;
    }

    private boolean export(TokenType type, String content) {
        TokenItem token = this.ahead;
        if (token == null) {
            throw new RuntimeException("");
        }
        return token.getType() == type && token.getContent().equals(content);
    }

    private boolean export(TokenType type) {
        TokenItem token = this.ahead;
        return token.getType() == type;
    }

    private void match(TokenType type, String value) {
        TokenItem token = this.getNextToken();
        if (token.getType() != type || !token.getContent().equals(value)) {
            throw new RuntimeException("error in " + token.getContent());
        }
    }

    private TokenItem getNextToken() {
        TokenItem nextToken = this.ahead;
        if (this.tokenList.size() > this.index) {
            this.ahead = this.tokenList.get(++this.index);
        } else {
            this.ahead = null;
        }

        return nextToken;
    }
}
