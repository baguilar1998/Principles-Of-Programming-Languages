import java.util.*;
public class Expr{
	
	LinkedList<BoolTermItem> expr;
	
	Expr(LinkedList<BoolTermItem> ex){
		expr = ex;
	}
	
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <expr>");
		System.out.println(indent1 + indent1.length() + " <expr>");
		String indent2 = indent1 + " ";
		for(BoolTermItem bt: expr)
			bt.printParseTree(indent2);
	}
}
