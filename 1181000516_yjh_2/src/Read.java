import java.util.ArrayList;
import java.util.List;

public class Read {


    public static void addfound(List<found> fd)
    {


        List<String> tmp = new ArrayList<>();
        tmp.add("D");
        found f1 = new found();
        f1.start = "P";
        f1.end = tmp;
        fd.add(f1);



        f1 = new found();
        tmp = new ArrayList<>();
        tmp.add("P");
        f1.start = "P'";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.add("S");
        f1.start = "P";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("S");
        tmp.add("S");
        f1.start = "P";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        f1.start = "D";
        tmp.add("D");
        tmp.add("D");
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        f1.start = "S";
        tmp.add("S");
        tmp.add("S");
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("proc");
        tmp.add("id");
        tmp.add(";");
        tmp.add("D");
        tmp.add("S");
        f1.start = "D";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("proc");
        tmp.add("id");
        tmp.add(":");
        tmp.add("D");
        tmp.add("S");
        f1.start = "D";
        f1.end = tmp;
        fd.add(f1);

//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("1");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("2");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("3");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("4");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("5");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("6");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("7");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("8");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("9");
//        tmp.add("digit");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("0");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("0");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("1");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("2");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("3");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("4");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("5");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("6");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("7");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("8");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);
//
//        f1 = new found();
//        tmp = new ArrayList<>();
//        tmp.clear();
//        tmp.add("9");
//        f1.start = "digit";
//        f1.end = tmp;
//        fd.add(f1);






        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("T");
        tmp.add("id");
        tmp.add(";");
        f1.start = "D";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("X");
        tmp.add("C");
        f1.start = "T";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("record");
        tmp.add("D");
        f1.start = "T";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("integer");
        f1.start = "X";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("real");
        f1.start = "X";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("[");
        tmp.add("digit");
        tmp.add("]");
        tmp.add("C");
        f1.start = "C";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("epsilon");
        f1.start = "C";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("id");
        tmp.add("=");
        tmp.add("E");
        tmp.add(";");
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("L");
        tmp.add("=");
        tmp.add("E");
        tmp.add(";");
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);


        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("E");
        tmp.add("+");
        tmp.add("E");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("E");
        tmp.add("*");
        tmp.add("E");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("E");
        tmp.add("-");
        tmp.add("E");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("-");
        tmp.add("E");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("(");
        tmp.add("E");
        tmp.add(")");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("id");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("digit");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("L");
        f1.start = "E";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("struct");
        f1.start = "X";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("⬆");
        tmp.add("C");
        f1.start = "C";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("id");
        tmp.add("[");
        tmp.add("E");
        tmp.add("]");
        f1.start = "L";
        f1.end = tmp;
        fd.add(f1);


        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("L");
        tmp.add("[");
        tmp.add("E");
        tmp.add("]");
        f1.start = "L";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("if");
        tmp.add("B");
        tmp.add("then");
        tmp.add("S");
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("if");
        tmp.add("B");
        tmp.add("then");
        tmp.add("S");
        tmp.add("else");
        tmp.add("S");
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("while");
        tmp.add("B");
        tmp.add("do");
        tmp.add("S");
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("B");
        tmp.add("or");
        tmp.add("B");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("B");
        tmp.add("and");
        tmp.add("B");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("L");
        tmp.add("=");
        tmp.add("E");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("not");
        tmp.add("B");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("(");
        tmp.add("B");
        tmp.add(")");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("E");
        tmp.add("relop");
        tmp.add("E");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("true");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("false");
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("<");
        f1.start = "relop";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("<=");
        f1.start = "relop";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("==");
        f1.start = "relop";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("!=");
        f1.start = "relop";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add(">");
        f1.start = "relop";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add(">=");
        f1.start = "relop";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("call");
        tmp.add("id");
        tmp.add("(");
        tmp.add("Elist");
        tmp.add(")");
        tmp.add(";");
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("Elist");
        tmp.add(",");
        tmp.add("E");
        f1.start = "Elist";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("Elist");
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);

        f1 = new found();
        tmp = new ArrayList<>();
        tmp.clear();
        tmp.add("E");
        f1.start = "Elist";
        f1.end = tmp;
        fd.add(f1);
    }
}