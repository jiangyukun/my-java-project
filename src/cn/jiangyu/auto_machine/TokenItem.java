package cn.jiangyu.auto_machine;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动机进行词法分析
 * Created by jiangyukun on 2017/6/21.
 */

public class TokenItem {
    private TokenType type;
    private String content;

    TokenItem(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content + "(" + this.type + ")";
    }
}

 enum TokenType {
    identify, number, string, bool, symbol
}