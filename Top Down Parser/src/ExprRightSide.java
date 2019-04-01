
public class ExprRightSide extends RightSide {
	Expr expr;
	
	ExprRightSide(Expr ex) {
		expr = ex;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <expr right side>");
		System.out.println(indent1 + indent1.length() + " <expr right side>");
		String indent2 = indent1 + " ";
		expr.printParseTree(indent2);
	}
}
