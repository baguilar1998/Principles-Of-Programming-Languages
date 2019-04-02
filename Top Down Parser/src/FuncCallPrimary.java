
public class FuncCallPrimary extends Primary{
	
	FuncCall funcCall;
	
	FuncCallPrimary(FuncCall fc){
		funcCall = fc;
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> ");
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <func call primary> ");
		String indent2 = indent1 + " ";
		funcCall.printParseTree(indent2);
		
	}

}
