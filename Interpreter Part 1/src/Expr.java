import java.util.*;
public class Expr{
	
	LinkedList<BoolTermItem> expr;
	
	Expr(LinkedList<BoolTermItem> ex){
		expr = ex;
	}
	
	void printParseTree(String indent) {
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <expr>");
		for(BoolTermItem bt: expr)
			bt.printParseTree(indent1);
	}
	
	Val Eval(HashMap<String,Val> state) {
		Val eVal = null;
		for (BoolTermItem t: expr)
			eVal = t.Eval(state,eVal);
		return eVal;
	}
}
