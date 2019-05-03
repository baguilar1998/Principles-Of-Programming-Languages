
public class Lt extends CompOp{
	
	String op;
	
	Lt(){
		op = "<";
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " "+op);
	}
}
