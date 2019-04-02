
public class ParenPrimary extends Primary{
	Expr expr;
	
	ParenPrimary(Expr e){
		expr = e;
	}
	void printParseTree(String indent) {
		IO.display(indent + indent.length() + "(");
		expr.printParseTree(indent);
		IO.display(indent + indent.length() + ")");
	}
}
