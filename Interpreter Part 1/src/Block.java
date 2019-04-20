import java.util.HashMap;

public class Block extends Statement{
	SList sList;
	
	Block(SList s){
		sList = s;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <block> ");
		String indent2 = indent1 + " ";
		sList.printParseTree(indent2);
	}

	@Override
	void M(HashMap<String, Val> state) {
		sList.M(state);
		
	}
}
