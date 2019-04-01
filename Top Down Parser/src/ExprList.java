import java.util.*;
public class ExprList {

	LinkedList<Expr> exprList;
	
	ExprList(LinkedList<Expr> el){
		exprList = el;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <expr list>");
		System.out.println(indent + indent.length() + " <expr list>");
		String indent1 = indent + " ";
		for(Expr e: exprList)
			e.printParseTree(indent);
	}
}
