import java.util.*;
public class Expr{
	
	LinkedList<BoolTermItem> expr;
	
	Expr(LinkedList<BoolTermItem> ex){
		expr = ex;
	}
	
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <expr>");
		System.out.println(indent + indent.length() + " <expr>");
		String indent2 = indent1 + " ";
		for(BoolTermItem bt: expr)
			bt.printParseTree(indent1);
	}
}
