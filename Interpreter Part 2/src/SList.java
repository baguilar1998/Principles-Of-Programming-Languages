import java.util.*;
public class SList {
	LinkedList<Statement> sList;
	
	SList(LinkedList<Statement> s){
		sList = s;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <s list>");
		String indent1 = indent + " ";
		for(Statement s: sList)
			s.printParseTree(indent1);
	}
	
	void M(HashMap<String,Val> state) {
		for(Statement s: sList) {
			s.M(state);
		}
	}
}
