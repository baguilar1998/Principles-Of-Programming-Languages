
public class Gt extends CompOp{
	
	String op;
	
	Gt(){
		op =">";
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " " + op);
	}
}
