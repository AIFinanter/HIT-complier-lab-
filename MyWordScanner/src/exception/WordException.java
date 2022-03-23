package exception;

public class WordException extends Throwable{
    int line;
    int col;
    public WordException(int line,int col){
        this.line = line;
        this.col = col;
        System.out.println("position:"+"<"+this.line+","+this.col+">");
    }
}
