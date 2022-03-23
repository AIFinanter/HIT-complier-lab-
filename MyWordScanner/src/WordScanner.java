import exception.IllegalIDException;
import exception.SBException;
import exception.WordException;

import token.Token;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//如何识别非法字符的问题：
//1.非法的字符比如以digit（1-9）开头，中途却出现了character的字符
//2.十六进制表示错误
//3.八进制表示错误
//4.指数表示错误
//5.还有另一种非法字符比如以character开头,但是中途却却出现了/+（非字符数字符号）
//6.指针变量声明语句
//
//7.我把科学计数法归到int类型



public class WordScanner {
    private String word;
    private List<Token> words = new ArrayList<>();
    public int line = 1;
    public int col = 1;
    public WordScanner(String path) throws IOException {
        word = this.read(path);
    }

    public String read(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            System.out.println("文件不存在,请检查文件目录");
        }
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte[] bytes = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        word = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("CAO:"+word);
        return word;
    }

    public boolean isDigit(char a){
        return a >= '0' && a <= '9';
    }

    public boolean isCharacter(char a){
        return (a>='a' && a<='z') || (a>='A' && a<='Z');
    }



    public void parse() throws SBException, IllegalIDException, WordException {
        int i = 0;

        boolean expr = false;//是不是指数
        boolean flag = false;
        //flag = true表示此时句子在某个双引号里面
        boolean isFloat = false;
        while(i<word.length() && word.charAt(i)!='#'){
            //这里是为了准确识别当前在哪一行，哪一列
            if(word.charAt(i) == '\n'){
                i++;
                this.line++;
                this.col = 1;
            }
            if(i == word.length())
            {
                return;
            }
            if(isDigit(word.charAt(i))){

                int mark = i;
                boolean _0xFloat = false;
                if(word.charAt(mark) == '0' && word.charAt(mark+1)=='x') {
                    i = i + 1;
                    this.col = this.col + 1;
                    while(isDigit(word.charAt(i+1)) || isCharacter(word.charAt(i+1)) || word.charAt(i+1) == '.')
                    {
                        i++;
                        this.col++;
//                    while (isDigit(word.charAt(i)) || isCharacter(word.charAt(i)) || word.charAt(i) == '.') {
//                        while (word.charAt(i) == '.' || (word.charAt(i) >= 'A' && word.charAt(i) <= 'F') || (word.charAt(i) >= 'a' && word.charAt(i) <= 'f') || isDigit(word.charAt(i))) {
//                            if (word.charAt(i) == '.') {
//                                _0xFloat = true;
//                            }
//                            i++;
//                        }
                        if(word.charAt(i) == '.')
                        {
                            if(isFloat == false && expr == false)
                            {
                                isFloat = true;
                            }
                            else
                            {
                                System.out.println("!");
                            }
                        }
                        //如果16进制字符串的读取是由于读到了ASCII值>='G'的字母,或者读取到了字母以外的其他字符的话,那么就读到了非法字符,就要把非法字符的行号和列号确定了再输出
                        if ((word.charAt(i) >= 'G' && word.charAt(i) <= 'Z') || (word.charAt(i) >= 'g' && word.charAt(i) <= 'z')) {
                            System.out.print("请注意！16进制字符串的每一位数字都应该是[0-9 A-F a-f]");
                            throw new WordException(this.line, this.col);
                        }

                    }

                    if (isFloat == true)
                    {
                        isFloat = false;
                        i++;
                        this.col++;
                        words.add(new Token(Token.idC.FLOAT, word.substring(mark,i),"常量->16进制浮点型",line));
                    } else {
                        i++;
                        this.col++;
                        words.add(new Token(Token.idC.INT, word.substring(mark, i),"常量->16进制整型",line));
                    };
                }

                else if(word.charAt(mark) == '0' && word.charAt(mark+1)!='x') {
                    i++;
                    this.col++;
                    while (word.charAt(i) == '.' || isDigit(word.charAt(i)) || isCharacter(word.charAt(i))) {

//                        if(word.charAt(i) == '.' && expr == false){
//                            isFloat = true;
//                            i++;
//                            col++;
//                        }
//
//                        if(word.charAt(i) == 'e' || word.charAt(i) == 'E'){
//                            isFloat = false;
//                            expr = true;
//                            i++;
//                            col++;
//                        }

                        //如果8进制字符串的读取是由于读到了ASCII值>='8'的字母，或者读取到了字母以外的其他字符的话，就要把非法字符的行号和列号确定了再输出
                        if(word.charAt(i) == '.'){
                            if(isFloat == false && expr == false){
                                isFloat = true;
                            }else {
                                System.out.println("No number has two dots!");
                                throw new WordException(this.line, this.col);
                            }

                        }

                        if ((word.charAt(i) >= '8' && word.charAt(i) <= 9) || word.charAt(i) == '\'' || word.charAt(i) == ':' || word.charAt(i) == '`' || word.charAt(i) == '~' || word.charAt(i) == '!' || word.charAt(i) == '|' || word.charAt(i) == '$' || word.charAt(i) == '@' || word.charAt(i) == '^') {
                            System.out.println("请注意！8进制数字每一个数位都应该是[0-7]");
                            throw new WordException(this.line, this.col);
                        }
                        i++;
                        this.col++;
                    }
                    words.add(new Token(Token.idC.INT, word.substring(mark, i),"常量->8进制整型",line));
                }

                else if(word.charAt(mark)>'0' && word.charAt(mark)<='9'){
                    while (isCharacter(word.charAt(i + 1)) || isDigit(word.charAt(i + 1))) {
                        i++;
                        this.col++;
                        if (word.charAt(i) == '#') {
                            return;
                        }
                        if (isCharacter(word.charAt(i))) {
                            System.out.println("You have put an illegal character in a number!Error occurs in line:"+this.line+" "+"col:"+this.col+word.charAt(i));
                            throw new IllegalIDException();
                        }

                        if (word.charAt(i) == '.' && expr == false) {
                            isFloat = true;
                        }

                        if(word.charAt(i) == 'e' || word.charAt(i) == 'E'){
                            expr = true;
                            isFloat = false;
                        }
                    }
                }
            }

            //要注意的是isCharacter这里的word.charAt(i)默认全是数字和字母，这样的话这里处理的是word是ID（标识符）或者以_开头的标识符的情况
            //以character开头说明这个很可能是标识符
            if(isCharacter(word.charAt(i))){
                int mark = i;

                while(isCharacter(word.charAt(i+1)))
                {
                    i++;
                    this.col++;
                }

//                if(!isCharacter(word.charAt(i+1))){
//                    words.add(new Token(Token.idC.ID,word.substring(mark,i+1)));
//                    i++;
//                    mark = i+1;
//                    i--;
//                }
//                while(isCharacter(word.charAt(i+1))){
//                    i++;
//
//                    while(isCharacter(word.charAt(i+1)) || isDigit(word.charAt(i+1))) { //规避类似于while1这样的东西出现
//                        i++;
//                    }

                switch (word.substring(mark, i + 1)) {
                    //关键字

                            case "System":
                                words.add(new Token(Token.idC.SYSTEM,"-","关键字",line));
                                break;
                            case "out":
                                words.add(new Token(Token.idC.OUT,"-","关键字",line));
                                break;
                            case "print":
                                words.add(new Token(Token.idC.PRINT,"-","关键字",line));
                            case "println":
                                words.add(new Token(Token.idC.PRINTLN,"-","关键字",line));
                            case "while":
                                words.add(new Token(Token.idC.WHILE,"-","关键字",line));
                                break;
                            case "if":
                                words.add(new Token(Token.idC.IF,"-","关键字",line));
                                break;
                            case "else":
                                words.add(new Token(Token.idC.ELSE,"-","关键字",line));
                                break;
                            case "return":
                                words.add(new Token(Token.idC.RETURN,"-","关键字",line));
                                break;
                            case "do":
                                words.add(new Token(Token.idC.DO,"-","关键字",line));
                                break;
                            case "switch":
                                words.add(new Token(Token.idC.SWITCH,"-","关键字",line));
                                break;
                            case "case":
                                words.add(new Token(Token.idC.CASE,"-","关键字",line));
                                break;
                            case "break":
                                words.add(new Token(Token.idC.BREAK,"-","关键字",line));
                                break;
                            case "enum":
                                words.add(new Token(Token.idC.ENUM,"-","关键字",line));
                                break;
                            case "public":
                                words.add(new Token(Token.idC.PUBLIC,"-","关键字",line));
                                break;
                            case "private":
                                words.add(new Token(Token.idC.PRIVATE,"-","关键字",line));
                                break;
                            case "byte":
                                words.add(new Token(Token.idC.BYTE,"-","关键字",line));
                                break;

                            //常量
                            //狭路相逢勇者胜
                            case "int":
                                words.add(new Token(Token.idC.INT,"-","常量->整型",line));
                                break;
                            case "float":
                                words.add(new Token(Token.idC.FLOAT,"-","常量->单精度浮点型",line));
                                break;
                            case "double":
                                words.add(new Token(Token.idC.DOUBLE,"-","常量->双精度浮点型",line));
                                break;
                            case "string":
                                words.add(new Token(Token.idC.STRING,"-","常量->字符串",line));
                                break;
                            case "char":
                                words.add(new Token(Token.idC.CHAR,"-","常量->字符型",line));
                                break;
                            case "bool":
                                words.add(new Token(Token.idC.BOOL,"-","常量->布尔型",line));
                                break;
                            case "class":
                                words.add(new Token(Token.idC.CLASS,"-","关键字",line));
                                break;
                            case "when":
                                words.add(new Token(Token.idC.WHEN,"-","关键字",line));
                                break;
                            case "boolean":
                                words.add(new Token(Token.idC.BOOLEAN,"-","布尔型",line));
                                break;
                            default:
                                words.add(new Token(Token.idC.ID,word.substring(mark,i+1),"标识符",line));
                                break;
                        }
                        i++;
                        this.col++;
            }

//                    if(word.substring(mark,i+1)=="while" && !isCharacter(word.charAt(i+1))){
//                        words.add(new Token(Token.idC.WHILE,"-"));
//                    }
//                    if(word.substring(mark,i+1))
//              }
//                if(isDigit(word.charAt(i+1))){
//                    i++;
//                    if(word.charAt(i)=='#'){
//                        return;
//                    }
//                    while(isDigit(word.charAt(i+1)) || isCharacter(word.charAt(i+1))){
//                        i++;
//                    }
//                    words.add(new Token(Token.idC.ID,word.substring(mark,i+1)));
//               }


            switch(word.charAt(i)){
                case ':':
                    if(word.charAt(i+1)==':')
                    {
                        i = i+2;
                        this.col = this.col+2;
                    }
                    else
                    {
                        i++;
                        this.col++;
                    }
                    break;
                case '&':
                    if(word.charAt(i+1)=='&'){
                        i = i+2;
                        this.col = this.col+2;
                        words.add(new Token(Token.idC.AND,"-","逻辑运算符",line));
                    }
                    else {
                        i++;
                        this.col++;
                        words.add(new Token(Token.idC.BITAND, "-","算术运算符",line));
                    }
                    break;
                case '"':
                    int mark = i;
                    while(word.charAt(i+1)!='"'){
                        i++;
                        this.col++;
                    }
                    words.add(new Token(Token.idC.LDQ,"\"","界限符:左引号",line));
                    words.add(new Token(Token.idC.ID,String.valueOf(word.substring(mark+1,i+1)),"标识符",line));
                    words.add(new Token(Token.idC.RDQ,"\"","界限符:右引号",line));
                    i = i + 2;
                    this.col = this.col + 2;
                    break;
                case '/':
                    if(word.charAt(i+1)=='/'){
                        while(word.charAt(i)!='\n') {
                            i++;
                            this.col++;
                        }
                    }

                    if(word.charAt(i+1)=='*'){
                        i+=2;
                        this.col+=2;
                        while(word.charAt(i)!='*' || word.charAt(i+1)!='/' || !flag){
                            i++;
                            this.col++;
                            if(word.charAt(i)=='"' && !flag){
                                flag = true;
                            }else if(word.charAt(i)=='"' && flag){
                                flag = false;
                            }
                            if(word.charAt(i)=='#'){
                               throw new SBException();
                            }
                        }
                        i+=2;
                        this.col+=2;
                    }
                    break;

                case '('://跳过
                    words.add(new Token(Token.idC.SLP,"(","界限符->左小括号",line));
                    i++;
                    this.col++;
                    break;

                case ')':
                    words.add(new Token(Token.idC.SRP,")","界限符->右小括号",line));
                    i++;
                    this.col++;
                    break;
                case '[':
                    words.add(new Token(Token.idC.MLP,"[","界限符->左中括号",line));
                    i++;
                    this.col++;
                    break;
                case ']':
                    words.add(new Token(Token.idC.MRP,"]","界限符->右中括号",line));
                    i++;
                    this.col++;
                    break;
                case '{':
                    words.add(new Token(Token.idC.LP,"{","界限符->左大括号",line));
                    i++;
                    this.col++;
                    break;
                case '}':
                    words.add(new Token(Token.idC.RP,"}","界限符->右大括号",line));
                    i++;
                    this.col++;
                    break;

                case '+':
                    if(word.charAt(i+1)=='+'){
                        Token t = new Token(Token.idC.INCREMENT,"++","运算符->算术运算符",line);
                        i = i+2;
                        this.col+=2;
                        words.add(t);
                    }else{
                        Token t = new Token(Token.idC.ADD,"+","运算符->算术运算符",line);
                        i = i+1;
                        this.col++;
                        words.add(t);
                    }
                    break;
                case '!':
                    if(word.charAt(i+1) == '='){
                        Token t = new Token(Token.idC.NE,"!=","运算符->关系运算符",line);
                        i+=2;
                        this.col+=2;
                        words.add(t);
                    }else
                    {
                        i++;
                        this.col++;
                    }

                    break;
                case '<':
                    if(word.charAt(i+1) == '='){
                        Token t = new Token(Token.idC.NMORE,"<=","运算符->关系运算符",line);
                        i+=2;
                        this.col+=2;
                        words.add(t);
                    }else if(word.charAt(i+1) == '<'){
                        words.add(new Token(Token.idC.INSERT,"<<","关键字",line));
                        i = i+2;
                        this.col+=2;
                    }else{
                        Token t = new Token(Token.idC.LESS,"<","运算符->关系运算符",line);
                        i+=1;
                        this.col++;
                        words.add(t);
                    }
                    break;
                case '>':
                    if(word.charAt(i+1) == '='){
                        Token t = new Token(Token.idC.NLESS,">=","运算符->关系运算符",line);
                        words.add(t);
                        i+=2;
                        this.col+=2;
                    }else{
                        Token t = new Token(Token.idC.MORE,">","运算符->关系运算符",line);
                        words.add(t);
                        i++;
                        this.col++;
                    }
                    break;
                case '-':
                    if(word.charAt(i+1)=='-'){
                        Token t = new Token(Token.idC.DCRE,"--","运算符->算术运算符",line);
                        i+=2;
                        this.col+=2;
                        words.add(t);
                    }else{
                        Token t = new Token(Token.idC.MINUS,"-","运算符->算术运算符",line);
                        words.add(t);
                        i++;
                        this.col++;
                    }
                    break;
                case ',':
                    Token t = new Token(Token.idC.COMMA,",","运算符->界限符",line);
                    words.add(t);
                    i++;
                    this.col++;
                    break;

                case '=':
                    if(word.charAt(i+1)=='='){
                        words.add(new Token(Token.idC.EQUALS,"==","运算符->关系运算符",line));
                        i+=2;
                        this.col+=2;
                    }else{
                        words.add(new Token(Token.idC.EVA,"=","运算符->算术运算符",line));
                        i++;
                        this.col++;
                    }
                    break;
                case ';':
                    words.add(new Token(Token.idC.SEMICOLON,";","界限符",line));
                    i++;
                    this.col++;
                    break;
                case '.':
                    words.add(new Token(Token.idC.DOT,".","标识符",line));
                    i++;
                    this.col++;
                    break;
                case '|':
                    if(word.charAt(i+1) == '|'){
                        i = i+2;
                        this.col = this.col+2;
                        words.add(new Token(Token.idC.OR,"||","运算符->逻辑运算符",line ));
                    }
                    else{
                        System.out.println("Syntax Error!");
                    }
                    break;
                default:
                    if(word.charAt(i)=='#'){
                        return;
                    }
                    i++;
                    this.col++;

                    break;
            }
        }
    }

    //
    private int computeStrValue(String str) {
        int len = str.length();
        int sum = 0;
        for(int i=len-1;i>=0;i--)
        {
            sum = sum + str.charAt(i);
        }
        return sum;
    }

    public List<Token> getWords() {
        return this.words;
    }
}
