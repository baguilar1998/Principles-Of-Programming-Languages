import java.util.*;
public class EItem{
	LinkedList<Term> termList;
	
	EItem(LinkedList<Term> term){
		termList = term;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <E>");
		System.out.println(indent + indent.length() + " <E>");
		String indent1 = indent + " ";
		for(Term t : termList)
			t.printParseTree(indent1);
	}
}
