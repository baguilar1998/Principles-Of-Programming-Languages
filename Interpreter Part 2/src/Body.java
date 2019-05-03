import java.util.HashMap;

public class Body {

	SList sList;
	
	Body(SList s) {
		sList = s;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <body>");
		String indent1 = indent + " ";
		sList.printParseTree(indent1);
	}
	
	void M(HashMap<String,Val> state) 
	{
		sList.M(state);
	}
}
