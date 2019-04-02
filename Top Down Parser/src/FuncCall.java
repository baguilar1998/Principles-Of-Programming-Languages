
public class FuncCall {

	FuncName funcName;
	ExprList exprList;
	
	FuncCall(FuncName fn, ExprList el){
		funcName = fn;
		exprList = el;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <func call>");
		String indent1 = indent + " ";
		funcName.printParseTree(indent1);
		if(exprList!=null)exprList.printParseTree(indent1);
	}
}
