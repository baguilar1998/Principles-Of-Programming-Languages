import java.util.HashMap;

public class Cond extends Statement{
	Expr expr;
	Statement statement1, statement2;
	
	Cond(Expr e, Statement s1){
		expr = e;
		statement1 = s1;
	}
	
	Cond(Expr e, Statement s1, Statement s2){
		expr = e;
		statement1 = s1;
		statement2 = s2;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <cond>");
		String indent2 = indent1+ " ";
		IO.displayln(indent2 + indent2.length() + " if");
		expr.printParseTree(indent2);
		statement1.printParseTree(indent2);
		if(statement2 !=null) {
			IO.displayln(indent2 + indent2.length() + " else");
			statement2.printParseTree(indent2);
		}
	}

	@Override
	void M(HashMap<String, Val> state) {
		if(statement2 == null) {
			Val boolExpr = expr.Eval(state);
			if(((BoolVal)boolExpr).val) {
				statement1.M(state);
			}
		} else {
			Val boolExpr = expr.Eval(state);
			if(((BoolVal)boolExpr).val) {
				statement1.M(state);
			} else {
				statement2.M(state);
			}
		}
		
	}
}
