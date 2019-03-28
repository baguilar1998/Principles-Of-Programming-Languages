
public class Block extends Statement{
	SList sList;
	
	Block(SList s){
		sList = s;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		sList.printParseTree(indent1);
	}
}
