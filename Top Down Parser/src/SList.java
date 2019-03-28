import java.util.*;
public class SList {
	LinkedList<Statement> sList;
	
	SList(LinkedList<Statement> s){
		sList = s;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <s list>");
		System.out.println(indent + indent.length() + " <s list>");
		String indent1 = indent + " ";
		System.out.println(sList);
		for(Statement s: sList)
			s.printParseTree(indent1);
	}
}
