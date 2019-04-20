import java.util.*;
public abstract class RightSide {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <right side>");
	}
	
	abstract Val Eval(HashMap<String,Val> state);
}
