
public class While extends Statement{
	Expr expr;
	Statement statement;
	
	While(Expr e, Statement s){
		expr = e;
		statement = s;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		super.printParseTree(indent);
		String indent1 = indent + " ";
		String indent2 = indent1+ " ";
		IO.displayln(indent1 + indent1.length() + " <while>");
		System.out.println(indent1 + indent1.length() + " <while>");
		IO.displayln(indent1 + indent1.length() + " while");
		System.out.println(indent1 + indent1.length() + " while");
		expr.printParseTree(indent2);
		statement.printParseTree(indent2);
	}
}
