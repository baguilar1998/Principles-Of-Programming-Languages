import java.util.*;
public class Term {

	LinkedList<Primary> primaryList;
	
	Term(LinkedList<Primary> p){
		primaryList = p;
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <Term>");
		System.out.println(indent + indent.length() + " <Term>");
		String indent1 = indent + " ";
		for(Primary p : primaryList)
			p.printParseTree(indent1);
	}
}
