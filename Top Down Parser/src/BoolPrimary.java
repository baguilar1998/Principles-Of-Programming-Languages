import java.util.*;
public class BoolPrimary {

	LinkedList<EItem> eList;
	
	BoolPrimary(LinkedList<EItem> e){
		eList = e;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolPrimary>");
		System.out.println(indent + indent.length() + " <boolPrimary>");
		String indent1 = indent + " ";
		for(EItem e : eList)
			e.printParseTree(indent1);
	}
}
