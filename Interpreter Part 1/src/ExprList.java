import java.util.*;
public class ExprList {

	LinkedList<Expr> exprList;
	
	ExprList(LinkedList<Expr> el){
		exprList = el;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <expr list>");
		String indent1 = indent + " ";
		for(Expr e: exprList)
			e.printParseTree(indent1);
	}
	
	void M(HashMap<String,Val> state) {
		if(exprList.size()== 0)return;
		Val expr;
		int counter = 1;
		for(Expr e: exprList) {
			expr = e.Eval(state);
			if(expr == null) {
				return;
			}
			state.put("e" +counter, expr);
			++counter;
		}
	}
}
