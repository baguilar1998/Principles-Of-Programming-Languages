import java.util.HashMap;

public class ExprRightSide extends RightSide {
	Expr expr;
	
	ExprRightSide(Expr ex) {
		expr = ex;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <expr right side>");
		String indent2 = indent1 + " ";
		expr.printParseTree(indent2);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		return expr.Eval(state);
	}
}
