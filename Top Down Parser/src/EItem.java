import java.util.*;
public class EItem{
	LinkedList<TermItem> termList;
	
	EItem(LinkedList<TermItem> term){
		termList = term;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <E>");
		String indent1 = indent + " ";
		for(TermItem t : termList)
			t.printParseTree(indent1);
	}
}
