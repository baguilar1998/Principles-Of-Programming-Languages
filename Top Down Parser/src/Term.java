import java.util.*;
public class Term {

	LinkedList<PrimaryItem> primaryList;
	
	Term(LinkedList<PrimaryItem> p){
		primaryList = p;
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <Term>");
		System.out.println(indent + indent.length() + " <Term>");
		String indent1 = indent + " ";
		for(PrimaryItem p : primaryList)
			p.printParseTree(indent1);
	}
}
