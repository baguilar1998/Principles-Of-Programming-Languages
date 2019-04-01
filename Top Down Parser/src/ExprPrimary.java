public class ExprPrimary extends Primary{
	
	Expr expr;
	
	ExprPrimary(Expr ex){
		expr = ex;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary>");
		System.out.println(indent + indent.length() + " <primary>");
		expr.printParseTree(indent);
	}
}
