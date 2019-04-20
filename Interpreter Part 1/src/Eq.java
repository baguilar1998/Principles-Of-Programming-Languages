
public class Eq extends CompOp{
	
	String op;
	
	Eq(){
		op = "==";
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " "+op);
	}
}
