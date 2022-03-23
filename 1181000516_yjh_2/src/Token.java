public class Token {
    public String str;
    public int line;
    public String attr = "";
    public Token(String s,int l)
    {
        this.str = s;
        this.line = l;
    }
    public Token(String s,int l,String a)
    {
        this.str = s;
        this.line = l;
        this.attr = a;
    }
}
