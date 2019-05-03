import java.util.*;
public class Term {

	LinkedList<PrimaryItem> primaryList;
	
	Term(LinkedList<PrimaryItem> p){
		primaryList = p;
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <term>");
		String indent1 = indent + " ";
		for(PrimaryItem p : primaryList)
			p.printParseTree(indent1);
	}
	
	Val Eval(HashMap<String, Val> state, Val eVal) {
		Val val = null;
		for (PrimaryItem p: primaryList)
			eVal = p.Eval(state,eVal);
		return eVal;
	}
}
