package token;
public class Token{
    public enum idC {
        LDQ,//左引号"
        RDQ,//右引号"
        SLP,//左小括号(
        SRP,//右小括号)
        MLP,//左中括号[
        MRP,//右中括号]
        LP,//左大括号{
        RP,//右大括号}
        WHILE,//while关键字
        CLASS,//class关键字
        WHEN,//when关键字
        ENUM,
        INT,
        FLOAT,
        DOUBLE,
        STRING,
        CHAR,
        BOOLEAN,
        SWITCH,
        CASE,
        BREAK,
        BYTE,
        IF,
        ELSE,
        RETURN,
        PUBLIC,
        PRIVATE,
        COMMA,
        MORE,
        LESS,
        NMORE,
        NLESS,
        NE,
        INCREMENT,
        ID,
        DCRE,
        MINUS,
        ADD,
        DO,
        BOOL,
        EQUALS,
        SEMICOLON,
        EVA,
        DOT,
        INSERT,
        AND, BITAND, OR, SYSTEM,OUT,PRINT,PRINTLN;
    }

    private idC id = idC.ID;
    private String val = "";
    private String kind = "";
    private int line = 0;

    public Token(idC id, String val ,String kind,int line){
        this.id = id;
        this.val = val;
        this.kind = kind;
        this.line = line;
    }
    public String getVal() {
        return this.val;
    }
    public idC getId() {
        return this.id;
    }
    public int getLine() {return this.line;}
    public String getKind() {return this.kind;}
}