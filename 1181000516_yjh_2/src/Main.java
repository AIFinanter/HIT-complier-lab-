import java.io.*;

import java.util.*;




import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

class found
{
    public String start;
    public List<String> end = new ArrayList<>();
}

class item
{
    public String start;
    public List<String> end = new ArrayList<>();
    public String exp;
    public int point;
}

class items
{
    public List<item> is = new ArrayList<>();
}

class ACTION
{
    public int  state = 0;
    public String ch = "";
    public String s1 = "error";
}

class GOTO
{
    public int state = 0;
    public String next = "";
    public String s1 = "error";
}

public class Main {
    public static List<found> fd = new ArrayList<>();

    public static String[] gen = {"P'","P","D","S","T","X","C","E","L","B","Elist","relop"};

    //public static String[] gen = {"S","B"};
    //public static String[] notgen = {"a","b","$"};


    public static String[] chars = {"S1","S2","P","D","S","T","X","C","E","L","B","Elist","relop","-","+","*","<","<=","==","!=",">",">=","if","then","else","while","do","and","not","or","(",")","[","]","=",";",",","true","false","proc","epsilon","call"};

    public static String[] notgen = {"⬆","struct","-","+","*","<","<=","==","!=",">",">=","if","then","else","while","do","id","and","or","not","(",")","[","]","=",";",",","true","false","proc","epsilon","call","$","digit",":","integer","real"};

    public Stack<items> its = new Stack<>();
    public Stack<items> irs = new Stack<>();

    public static boolean isgen(String s)
    {
        boolean ans = false;
        for(String g:gen)
        {
            if(s.equals(g))
            {
                ans = true;
            }
        }
        return ans;
    }




    /**
     * 计算First集
     * @param next
     * @param exp
     * @return
     */
    public static List<String> getFirst(String next, String exp)
    {
        List<String> first = new ArrayList<>();
        boolean isgen = false;
        boolean rec = false;
        boolean infirst = false;
        //stay hungry stay foolish
        //加油吧,jun

            isgen = false;
            for(String g:gen)
            {
                if(g.equals(next))
                {
                    isgen = true;
                }
            }
            if(!isgen)
            {
                first.add(next);
            }
            else
            {
                //stk是用于存放产生式项目集的
                Stack<String> stk = new Stack<>();
                //这里的next是产生式项目
                stk.push(next);
                //record记录已经使用过的产生式在这里可以使用
                List<String> record = new ArrayList<>();

                while(true)
                {
                    String cur = stk.peek();
                    stk.pop();
                    record.add(cur);
                    for(found f:fd)
                    {
                        if(f.start.equals(cur))
                        {
                            if(isgen(f.end.get(0)))
                            {
                                rec = false;
                                for(String s:record)
                                {
                                    if(s.equals(f.end.get(0)))
                                    {
                                        rec = true;
                                        break;
                                    }
                                }
                                if(rec == true)
                                {
                                    continue;
                                }
                                else {
                                    //System.out.println("新加入的非终结符"+f.end.get(0));
                                    stk.push(f.end.get(0));
                                }
                            }
                            else {
                                infirst = false;
                                for(String s:first)
                                {
                                    if(f.end.get(0).equals(s))
                                    {
                                        infirst = true;
                                    }
                                }
                                if(!infirst)
                                {
                                    first.add(f.end.get(0));
                                }
                            }
                        }
                    }
                    if(stk.size()==0)
                    {
                        break;
                    }
                }
            }

        return first;
    }

    public static items closure(items I)
    {
        boolean inexp = false;
        item tmp = new item();

        Queue<item> qi = new LinkedList<item>();
        List<item> li = new ArrayList<item>();



        if(I.is.size()==0)
        {
            return null;
        }

        //System.out.println("I.is"+I.is.size());
        for(int i=0;i<I.is.size();i++) {
            li.add(I.is.get(i));
            qi.add(I.is.get(i));
        }

        while(true)
        {

            if(qi.isEmpty())
            {
                break;
            }

            item curr = qi.peek();
            qi.remove();
//            for(item i:tp)
//            {
//                System.out.println(i.start+"->"+i.end+","+i.exp);
//            }

            for(found f:fd)
            {
                    //System.out.println("a.end:"+a.end+"a.point:"+a.point);
                if(curr.point>curr.end.size())
                {
                    break;
                }
                if(!curr.end.get(curr.point-1).equals(f.start))
                {
                    continue;
                }


//                    else if(a.point == a.end.size() || a.end.get(a.point).equals("C")) {
//                        tmp.exp = "$";
//                        inexp = false;
//                        for (item s : li) {
//
//                            if (s.start.equals(tmp.start) && s.end.equals(tmp.end)&& s.exp.equals(tmp.exp)) {
//                                inexp = true;
//                            }
//                        }
//                        if (!inexp)
//                            li.add(tmp);
//
//
                //这种情况下first集只可能是a.exp
                if(curr.point == curr.end.size())
                {
                    String s = curr.exp;
                    tmp = new item();
                    tmp.start = f.start;
                    tmp.end = f.end;
                    tmp.point = 1;
                    tmp.exp = s;
                    inexp = false;
                    for(item i:li)
                    {
                        if(i.start.equals(tmp.start) && i.end.equals(tmp.end) && i.exp.equals(tmp.exp) && i.point == tmp.point)
                        {
                            inexp = true;
                            break;
                        }
                    }
                    if(!inexp) {
//                               System.out.println("新加入元组\n" + tmp.start + "->" + tmp.end + "[,]" + tmp.exp);
                        li.add(tmp);
                        qi.add(tmp);
                    }
                }
                    //first是First(a.end(a.point)+a.exp)
                else if(curr.point<curr.end.size())
                {
                    //System.out.println("first"+a.end.get(a.point)+"->"+getFirst(a.end.get(a.point),"$"));
                    for(String s:getFirst(curr.end.get(curr.point),curr.exp))
                    {
                        tmp = new item();
                        tmp.start = f.start;
                        tmp.end = f.end;
                        tmp.point = 1;
                        tmp.exp = s;
                        inexp = false;
                        // System.out.println("已有元组");
//                          for(item i:li)
//                          {
//
//                               System.out.println(i.start+"->"+i.end+"-,-"+i.exp);
//
//                          }
                        for(item i:li)
                        {
                            if(i.start.equals(tmp.start) && i.end.equals(tmp.end) && i.exp.equals(tmp.exp) && i.point==tmp.point)
                            {
                                inexp = true;
                                break;
                            }
                        }
                        if(!inexp) {
//                               System.out.println("新加入元组\n" + tmp.start + "->" + tmp.end + "[,]" + tmp.exp);
                                li.add(tmp);
                                qi.add(tmp);
                            }
                        }
                    }
            }
            //System.out.println("li");
//            for(item i:li)
//            {
//
//                System.out.println(i.start+"->"+i.end+","+i.exp);
//            }
            //System.out.println("size1"+size1+","+"size2"+size2);


        }


        items its = new items();
        its.is = li;
        return its;
    }

    public static items Goto(items I,String ch)
    {
        items J = new items();
        for(item i:I.is)
        {
            if(i.point<=i.end.size() && i.end.get(i.point-1).equals(ch))
            {
                item tmp = new item();
                tmp.point = i.point+1;
                tmp.start = i.start;
                for(String e:i.end)
                {
                    tmp.end.add(e);
                }
                tmp.exp = i.exp;
                J.is.add(tmp);
            }
        }
        return closure(J);
    }

    public static ArrayList<items> C = new ArrayList<>();

    public static boolean Mycontain(ArrayList<items> ai,items i)
    {

        boolean isin = false;
        for(items a:ai)
        {
            isin = false;
            //System.out.println("what is a"+a.is.get(0).start+"what is i"+i.is.get(0));
            if(a.is.size() == i.is.size())
            {
                isin = true;
                for(int j=0;j<a.is.size();j++)
                {
                    if(!a.is.get(j).start.equals(i.is.get(j).start))
                    {
                        isin = false;
                        break;
                    }

                    if(a.is.get(j).end.size()!=i.is.get(j).end.size())
                    {
                        isin = false;
                        break;
                    }
                    for(int k=0;k<a.is.get(j).end.size();k++)
                    {
                        if(!a.is.get(j).end.get(k).equals(i.is.get(j).end.get(k)))
                        {
                            isin = false;
                            break;
                        }
                    }
                    if(!a.is.get(j).exp.equals(i.is.get(j).exp))
                    {
                        isin = false;
                        break;
                    }
                    if(a.is.get(j).point!=i.is.get(j).point)
                    {
                        isin = false;
                        break;
                    }

                    if(!isin)
                    {
                        break;
                    }
                }
            }
            if(isin)
            {
                return true;
            }
        }
        return isin;
    }

    public  static void Tree()
    {
        item t1 = new item();
        t1.start = "P'";
        t1.end.add("P");
        t1.exp = "$";
        t1.point = 1;

        items t11;
        t11 = new items();
        t11.is.add(t1);

        t11 = closure(t11);
        //System.out.println("t11->"+t11.is.size());

        C.add(t11);

        //采取广度优先搜索的方式将所有的状态计算出来
        Queue<items> q = new LinkedList<items>();
        q.add(t11);

        while(true) {
            if(q.size()==0)
                break;
            items curit = q.peek();
            q.poll();
            for(String ch:gen)
            {
                if(Goto(curit,ch)!=null && !Mycontain(C,Goto(curit,ch)))
                {
                    C.add(Goto(curit,ch));
                    q.add(Goto(curit,ch));
                }
            }
            for(String ch:notgen)
            {
                if(Goto(curit,ch)!=null && !Mycontain(C,Goto(curit,ch)))
                {
                    C.add(Goto(curit,ch));
                    q.add(Goto(curit,ch));
                }
            }

            /**
             * 这个算法好像不大行
             */
            //
//            //System.out.println("size1+"+size1);
//            ArrayList<items> C1 = (ArrayList<items>) C.clone();
//            for (items I : C1) {
//                for (String ch : chars) {
//                    if (Goto(I, ch) != null && !Mycontain(C,Goto(I, ch))) {
//                        C.add(Goto(I, ch));
//                    }
//                }
//            }
//            int size2 = C.size();
//
//            //System.out.println("C.size1"+size1+",C.size2"+size2);
//            if(size1==size2)
//            {
//                break;
//            }
        }
    }

    public static Stack<Integer> state = new Stack<>();
    public static Stack<Token> chr = new Stack<>();
    public static Stack<found> gens = new Stack<>();

    public static int index = 0;

    public static boolean isid(String s)
    {
        boolean ans = false;
        for(int i=0;i<notgen.length;i++)
        {
            //CMU students
            if(s.equals(notgen[i]))
            {
                return false;
            }
        }

        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)<'0' || s.charAt(i)>'9')
            {
                ans = true;
            }
        }
        return ans;
    }

    public static boolean isdigit(String s)
    {
        boolean isd = true;
        if(s.length()>1 && s.charAt(0)=='0')
        {
            return false;
        }
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)<'0' || s.charAt(i)>'9')
            {
                isd = false;
                break;
            }
        }
        return isd;
    }

    public static List<Token> readFile() throws IOException {
        String path = "./src/file/input.txt";
        InputStreamReader is = new InputStreamReader(new FileInputStream(new File(path)));
        BufferedReader br = new BufferedReader(is);
        List<Token> ls = new ArrayList<>();
        String line = "";
        int count = 0;
        while((line = br.readLine())!=null)
        {
            count++;
            line = line.strip();
            String str[] = line.split("\\s");
            for(String s:str)
            {
                if(!s.equals("\b"))
                {
                    if(isid(s) && !s.equals("id"))
                        ls.add(new Token("id",count,s));
                    else if(isdigit(s) && !s.equals("digit"))
                        ls.add(new Token("digit",count,s));
                    else
                        ls.add(new Token(s,count));
                }
            }
        }



        List<Token> ans = new ArrayList<>();

        for(int i=0;i<ls.size();i++)
        {
            if(ls.get(i).str.equals("id") && (i-1<0 || !ls.get(i-1).str.equals("call")))
            {
                if(i+1<ls.size() && ls.get(i+1).str.equals("("))
                {
                    System.out.println("Syntax error at line"+ls.get(i).line);
                    System.out.println("输入符号错误");
                    throw new EOFException();
                }
            }

            if(i>0 && (ls.get(i-1).str.equals("⬆") || ls.get(i-1).str.equals("struct") || ls.get(i-1).str.equals("integer") || ls.get(i-1).str.equals("real") || ls.get(i-1).str.equals("]"))  && (isid(ls.get(i).str) || ls.get(i).str.equals("id")))
            {
                ans.add(new Token("epsilon",ls.get(i-1).line));
            }
            ans.add(ls.get(i));
        }
        return ans;
    }

    public static ACTION all[][] = new ACTION[C.size()][notgen.length];
    public static GOTO allg[][] = new GOTO[C.size()][gen.length];

    public static void writeFile()
    {
        String path = "./src/file" +
                "/output.txt";
        File f = new File(path);
        try {
            f.createNewFile();
            BufferedWriter out =  new BufferedWriter(new FileWriter(f));
            for(int i=0;i<all[0].length;i++)
            {
                out.write(String.format("%12s",notgen[i]));
            }
            for(int i=0;i<allg[0].length;i++)
            {
                out.write(String.format("%12s",gen[i]));
            }
            out.write("\n");
            for(int i=0;i<all.length;i++)
            {
                for(int j=0;j<all[0].length;j++)
                {
                    out.write(String.format("%12s",all[i][j].s1));
                }
                for(int k=0;k<allg[0].length;k++)
                {
                    out.write(String.format("%12s",allg[i][k].s1));
                }
                out.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void build()
    {
        String path = "./src/1.xls";
        //        FileOutputStream os = new FileOutputStream(new File(path));
        //        WritableWorkbook book = Workbook.createWorkbook(os);
        //        WritableSheet sheet = book.createSheet("test",0);
        //        读入文件
        Read.addfound(fd);
        for(found f:fd)
        {
            System.out.println(f.start+"->"+f.end);
        }
        List<String> slist = getFirst("epsilon","id");
        items it = new items();

        System.out.println("检验First集是否有问题"+slist);

        item s = new item();
        s.start = "P";

        s.end.add("D");

        s.exp = "$";
        s.point = 1;



        List<item> litem = new ArrayList<>();
        litem.add(s);
        it.is = litem;

        it = closure(it);



        for(int i=0;i<slist.size();i++)
        {
            System.out.println(":"+slist.get(i));
        }

        for(item ite:it.is)
        {
            System.out.println(ite.start+"->"+ite.end+","+ite.exp);
        }

        Tree();
        System.out.println(C.size());
        for(int i=0;i<C.size();i++)
        {
            System.out.println("闭包状态"+(i));
            for(int j=0;j<C.get(i).is.size();j++)
            {
                System.out.println(C.get(i).is.get(j).start+"->"+C.get(i).is.get(j).end+" , "+C.get(i).is.get(j).exp+"   |POINT POS|  "+C.get(i).is.get(j).point);
            }
        }

        System.out.println("闭包状态65");
        for(int j=0;j<C.get(65).is.size();j++)
        {
            System.out.println(C.get(65).is.get(j).start+"->"+C.get(65).is.get(j).end+" , "+C.get(65).is.get(j).exp+"   |POINT POS|  "+C.get(65).is.get(j).point);
        }

        all = new ACTION[C.size()][notgen.length];
        allg = new GOTO[C.size()][gen.length];

        System.out.println("all:"+all[0].length+"allg:"+allg[0].length);

        for(int i=0;i<all.length;i++)
        {
            for(int j=0;j<all[0].length;j++)
            {
                all[i][j] = new ACTION();
                all[i][j].state = i;
            }
        }

        for(int i=0;i<allg.length;i++)
        {
            for(int j=0;j<allg[0].length;j++)
            {
                allg[i][j] = new GOTO();
                allg[i][j].state = i;
            }
        }

        //System.out.println("all state 示范"+all[0][0].state);
        for(int i=0;i<C.size();i++)
        {
            for(int c=0;c<notgen.length;c++)
            {
                all[i][c].state = i;
                for(int j=0;j<C.size();j++)
                {
                    ArrayList<items> Cj = new ArrayList<items>();
                    items ims = new items();
                    ims = C.get(j);
                    Cj.add(ims);
                    boolean out1for = false;
                    for(item ite:C.get(i).is)
                    {
                        if(ite.point>ite.end.size() && ite.exp.equals(notgen[c]))
                        {
                            for(int fdi=0;fdi<fd.size();fdi++)
                            {
                                if(fd.get(fdi).start.equals(ite.start) && fd.get(fdi).end.equals(ite.end))
                                {
                                    all[i][c].s1 = "r"+String.valueOf(fdi);
                                }
                            }
                        }
                    }
                    if (Goto(C.get(i),notgen[c])!=null && Mycontain((ArrayList<items>) Cj, Goto(C.get(i), notgen[c])))
                    {
                        for(item ite:C.get(i).is)
                        {
                            if(ite.point<=ite.end.size() && ite.end.get(ite.point-1).equals(notgen[c]))
                            {
                                all[i][c].s1 = "s" + String.valueOf(j);
                            }
                        }
                    }
                }
            }

            for(int d=0;d<gen.length;d++)
            {
                allg[i][d].state = i;
                for(int j=0;j<C.size();j++)
                {
                    ArrayList<items> Cj = new ArrayList<>();
                    Cj.add(C.get(j));
                    boolean out1for = false;


                    if( Goto(C.get(i),gen[d])!=null && Mycontain((ArrayList<items>) Cj,Goto(C.get(i),gen[d]))) {
                        for (item ite : C.get(i).is) {

                                if (ite.point <= ite.end.size() && ite.end.get(ite.point - 1).equals(gen[d])) {
                                    allg[i][d].s1 = String.valueOf(j);
                                }
                        }
                    }




                }

            }

//            boolean isine = false;
//            for(int e=0;e<notgen.length;e++)
//            {
//                for(item ie:C.get(i).is)
//                {
//                    if(ie.exp.equals(notgen[e]) && ie.start!="P")
//                    {
//                        all[i][e].ch = ie.exp;
//                        for(int ifd=0;ifd<fd.size();ifd++)
//                        {
//                            if(fd.get(ifd).start.equals(ie.start) && fd.get(ifd).end.equals(ie.end))
//                            {
//                                all[i][e].s1 = "r"+String.valueOf(ifd);
//                            }
//                        }
//                    }
//                }
//
//            }

            //System.out.println("C:"+C.size());

            for(item ite:C.get(i).is)
            {
                if(ite.start.equals("S'") && ite.end.equals("P") && ite.exp.equals("$") && ite.point == 2)
                {
                    all[i][notgen.length-1].ch = "$";
                    all[i][notgen.length-1].s1 = "acc";
                }
            }

            //System.out.println("C.size::::::"+C.size());
        }

        for(int x=0;x<all.length;x++)
        {
            for(int y=0;y<all[0].length;y++)
            {
                System.out.print(all[x][y].s1+" , ");
            }
            for(int y1=0;y1<allg[0].length;y1++)
            {
                System.out.print(allg[x][y1].s1+" , ");
            }
            System.out.println("");
        }


        //输出文件
//        FileOutputStream os = new FileOutputStream(path);
//        wb.write(os);
//        os.flush();

    }

    public static int findgen(String s)
    {
        for(int i=0;i<gen.length;i++)
        {
            if(s.equals(gen[i]))
            {
                return i;
            }
        }
        return -1;
    }

    public static int findnotgen(String s)
    {
        for(int i=0;i<notgen.length;i++)
        {
            if(s.equals(notgen[i]))
            {
                return i;
            }

        }
        return -1;
    }

    public static Tree root;


    public static void outputTree(Tree r)
    {
        Stack<Tree> q = new Stack<>();
        Stack<Integer> numspace = new Stack<>();
        q.push(r);
        numspace.push(0);
        while(true)
        {
            if(q.empty())
                return;
            Tree cur = q.peek();
            q.pop();
            int curnum = numspace.peek();
            numspace.pop();

            if(cur.val.str.equals("epsilon"))
                continue;

            for(int i=0;i<curnum+1;i++)
                System.out.print("  ");



            if(cur.val.line == -1)
                System.out.println(cur.val.str);
            else
            {
                if(cur.val.attr.equals(""))
                {
                    System.out.println(cur.val.str+" ("+cur.val.line+")");
                }
                else
                {
                    System.out.println(cur.val.str+" ("+cur.val.line+")"+" :"+cur.val.attr);
                }
            }

            for(int i=cur.sons.size()-1;i>=0;i--)
            {
                q.push(cur.sons.get(i));
                numspace.push(curnum+1);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        List<Token> lst =  readFile();

        Token t1 = new Token("$",-1);
        Stack<Tree> s1 = new Stack<>();
        state.push(0);
        chr.push(t1);
        System.out.println("ACTION表建好了吗");
        for(int i=0;i<all.length;i++)
        {
            for(int j=0;j<all[0].length;j++)
            {
                System.out.print("A"+all[i][j].s1);
            }
            System.out.println("");
        }



        lst.add(t1);



        build();



        /**
         * 开始真正的主程序逻辑
         */
        index = 0;

        writeFile();

        System.out.println("让我们来看一看这个Token是怎么回事");
        for(int i=0;i<lst.size();i++)
        {
            System.out.print(lst.get(i).str+"->");
        }

        Stack<Tree> stree = new Stack<>();



        while(true)
        {
            Token now = lst.get(index);

            if(state.empty())
                return;
            int curs = state.peek();
            System.out.println("当前状态:"+curs+"当前展望符"+now.str);



            //System.out.println("state.peek()"+state.peek());
            //nowind是现有的now的索引
            int nowind = findnotgen(now.str);
            if(nowind==-1)
            {
                System.out.println("程序中存在非法字符!");
                exit(0);
            }

            if(all[curs][nowind].s1.charAt(0) == 's' )
            {
                chr.push(now);
                state.push(Integer.valueOf(all[curs][nowind].s1.substring(1)));
                if(index+1<lst.size())
                    index++;
                System.out.println("移入状态"+String.valueOf(nowind)+Integer.valueOf(all[curs][nowind].s1.substring(1)));
            }

            else if(all[curs][nowind].s1.charAt(0) == 'r')
            {

                int ind = Integer.valueOf(all[curs][nowind].s1.substring(1));
                //这里的curi指的是第几个产生式
                found curi = fd.get(ind);

                ArrayList<Token> rlist = new ArrayList<>();

                for(int j=0;j<curi.end.size();j++)
                {
                    rlist.add(chr.peek());
                    state.pop();
                    chr.pop();
                }

                Token t = new Token(curi.start,rlist.get(rlist.size()-1).line);

                //当前规约的树的根节点
                Tree curt = new Tree(t);

                Stack<Tree> tmp_stack = new Stack<>();

                //把栈中的输入标识符转存
                //rlist本来就是输入标识符里面反着来的

                for(int i=0;i<rlist.size();i++)
                {
                    if(isgen(rlist.get(i).str))
                    {
                        tmp_stack.push(stree.peek());
                        stree.pop();
                    }
                    else
                    {
                        //stay hungry stay foolish
                        tmp_stack.push(new Tree(rlist.get(i)));
                    }
                }

                // 生成一棵新的树
                for(int i=0;i<rlist.size();i++)
                {
                    //stay hungry stay foolish
                    curt.sons.add(tmp_stack.peek());
                    tmp_stack.pop();
                }

                curt.val.line = curt.sons.get(0).val.line;
                stree.push(curt);

                if(curt.val.str.equals("P'"))
                {
                    root = curt;
                }

                if(curi.start.equals("P'") && curi.end.get(0).equals("P"))
                {
                    System.out.println("ACCEPTED!");
                    break;
                }

                System.out.println("规约的前列项"+curi.start+" 当前的栈顶 "+state.peek());
                chr.push(new Token(curi.start,-1));
                int s_t = findgen(curi.start);
                curs = state.peek();
                state.push(Integer.valueOf(allg[curs][s_t].s1));
                curs = state.peek();
            }
            else if(all[curs][nowind].s1.equals("acc")) {
                System.out.println("accepted!");
                outputTree(root);
                break;
            }
            else if(all[curs][nowind].s1.equals("error"))
            {
                System.out.println("ERROR!");
                System.out.println("Syntax error at line "+now.line);
                System.out.println("函数规约错误");
                String s;
                List<String> s12 = getFirst("S","$");
                System.out.println("S的First集:"+s12);
                exit(0);
            }
        }
        outputTree(root);



        //System.out.println("the list of input string"+readFile());

    }
}