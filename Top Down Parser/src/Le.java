
public class Le extends CompOp{
	
	String op;
	
	Le(){
		op = "<=";
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " "+op);
	}
}
