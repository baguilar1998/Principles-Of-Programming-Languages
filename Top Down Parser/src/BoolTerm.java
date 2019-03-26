import java.util.*;
public class BoolTerm {
	
	LinkedList<BoolPrimary> boolPrimary;
	
	BoolTerm(LinkedList<BoolPrimary> bp){
		boolPrimary = bp;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolTerm>");
		System.out.println(indent + indent.length() + " <boolTerm>");
		String indent1 = indent + " ";
		for(BoolPrimary bp : boolPrimary)
			bp.printParseTree(indent1);
	}
}
