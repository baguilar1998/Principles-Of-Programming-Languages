
public class SingleBoolTermItem extends BoolTermItem {
	
	SingleBoolTermItem(BoolTerm t){
		term = t;
	}
	
	void printParseTree(String indent) {
		term.printParseTree(indent);
	}
}
