
public abstract class Var extends Primary {
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <var>");
		System.out.println(indent + indent.length() + " <var>");
	}
}
