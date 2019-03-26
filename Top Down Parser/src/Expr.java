import java.util.*;
public class Expr extends Primary{
	
	LinkedList<BoolTermItem> expr;
	
	Expr(LinkedList<BoolTermItem> ex){
		expr = ex;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <expr>");
		System.out.println(indent + indent.length() + " <expr>");
		String indent1 = indent + " ";
		for(BoolTermItem bt: expr)
			bt.printParseTree(indent1);
	}
}
