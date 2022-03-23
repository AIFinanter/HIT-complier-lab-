import java.util.ArrayList;
import java.util.List;

public class Tree {
    public Token val;
    public List<Tree> sons = new ArrayList<>();
    public Tree(Token t)
    {
        this.val = t;
    }
}
