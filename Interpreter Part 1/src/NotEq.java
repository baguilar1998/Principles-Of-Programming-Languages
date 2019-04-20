
public class NotEq extends CompOp{
	String op;
	
	NotEq(){
		op="!=";
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " "+op);
	}
}
