import java.util.HashMap;

public class ExprPrimary extends Primary{
	
	Expr expr;
	
	ExprPrimary(Expr ex){
		expr = ex;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary>");
		String indent1 = indent + " ";
		expr.printParseTree(indent1);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		Val val = expr.Eval(state);
		return val;

	}
}
