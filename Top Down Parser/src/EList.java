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
}
