import java.util.HashMap;

public class FuncCallStatement extends Statement {
	FuncCall funcCall;
	
	FuncCallStatement(FuncCall fc){
		funcCall = fc;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <func call statement> ");
		String indent2 = indent1+ " ";
		funcCall.printParseTree(indent2);
	}

	void M(HashMap<String, Val> state) {
		return;
		
	}
}
