
public class FuncCallStatement extends Statement {
	FuncCall funcCall;
	
	FuncCallStatement(FuncCall fc){
		funcCall = fc;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		funcCall.printParseTree(indent1);
	}
}
