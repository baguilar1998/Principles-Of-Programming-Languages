import java.util.*;
public class BoolTerm {
	
	LinkedList<BoolPrimaryItem> boolPrimary;
	
	BoolTerm(LinkedList<BoolPrimaryItem> bp){
		boolPrimary = bp;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolTerm>");
		System.out.println(indent + indent.length() + " <boolTerm>");
		String indent1 = indent + " ";
		for(BoolPrimaryItem bp : boolPrimary)
			bp.printParseTree(indent1);
	}
}
