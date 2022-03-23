import java.util.ArrayList;
import java.util.List;

public class ReadSimple {
    public static void addfound(List<found> fd)
    {
        List<String> tmp = new ArrayList<>();
        tmp.add("B");
        tmp.add("B");
        found f1 = new found();
        f1.start = "S";
        f1.end = tmp;
        fd.add(f1);

        tmp = new ArrayList<>();
        tmp.add("a");
        tmp.add("B");
        f1 = new found();
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        tmp = new ArrayList<>();
        tmp.add("b");
        f1 = new found();
        f1.start = "B";
        f1.end = tmp;
        fd.add(f1);

        tmp = new ArrayList<>();
        tmp.add("S");
        f1 = new found();
        f1.start = "S'";
        f1.end = tmp;
        fd.add(f1);

    }
}
