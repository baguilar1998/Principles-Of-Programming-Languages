import java.util.*;
public class BoolPrimary {

	LinkedList<ETermItem> eList;
	
	BoolPrimary(LinkedList<ETermItem> e){
		eList = e;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolPrimary>");
		System.out.println(indent + indent.length() + " <boolPrimary>");
		String indent1 = indent + " ";
		for(ETermItem e : eList)
			e.printParseTree(indent1);
	}
}
