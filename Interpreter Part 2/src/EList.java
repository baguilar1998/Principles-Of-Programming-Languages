import java.util.*;
public class EList {
	LinkedList<EItem> eList;
	
	public EList(LinkedList<EItem> e) {
		eList = e;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <EList>");
		String indent1 = indent + " ";
		for(EItem e: eList)
			e.printParseTree(indent1);
	}
	
	void M(HashMap<String,Val> state) {
		int counter = 1;
		for(EItem e : eList) {
			Val val = e.Eval(state, null);
			String tempParam = "e" + counter;
			state.put(tempParam,val);
			counter++;
		}
	}
}
