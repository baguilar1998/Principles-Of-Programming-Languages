import java.util.HashMap;

public abstract class Primary {
	abstract void printParseTree(String indent);
	abstract Val Eval(HashMap<String,Val> state,Val eVal);
}
