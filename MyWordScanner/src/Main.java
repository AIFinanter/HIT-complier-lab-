import exception.IllegalIDException;
import exception.WordException;
import token.Token;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception, IllegalIDException, WordException {
        File file = new File("./src/files/input.txt");
        File out = new File("./src/files/output.txt");
        if(!file.exists()){
            System.out.println("文件不存在，请检查指定路径");
        }
        WordScanner ws = new WordScanner("./src/files/input.txt");
        ws.parse();
        List<Token> wordstr = ws.getWords();
        for(Token t:wordstr){
            System.out.println("Line:"+t.getLine()+"["+t.getKind()+","+t.getId()+","+t.getVal()+"]");
        }
        for(Token t:wordstr){
            System.out.print(t.getVal());
        }
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(out);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        inputStream.read(bytes);
        for(Token t:wordstr){
            outputStream.write("Line:".getBytes());
            outputStream.write(String.valueOf(t.getLine()).getBytes());
            outputStream.write('\t');
            outputStream.write('[');
            outputStream.write(t.getKind().toString().getBytes());
            outputStream.write(',');
            outputStream.write(t.getId().toString().getBytes());
            outputStream.write(',');

            outputStream.write(t.getVal().getBytes());
            outputStream.write(']');
            outputStream.write('\n');
        }
        inputStream.close();
        outputStream.close();
        String str =new String(bytes, StandardCharsets.UTF_8);
        System.out.println("CAO:"+str);
    }
}
