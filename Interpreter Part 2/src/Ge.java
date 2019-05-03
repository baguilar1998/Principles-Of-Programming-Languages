
public class Ge extends CompOp{
	String op;
	
	Ge(){
		op = "<=";
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " " + op);
	}
}
