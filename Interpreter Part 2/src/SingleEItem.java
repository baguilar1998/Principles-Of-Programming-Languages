import java.util.HashMap;

public class SingleEItem extends ETermItem{

	SingleEItem(EItem e) {
		item = e;
	}
	
	void printParseTree(String indent) {
		item.printParseTree(indent);
	}
	
	Val Eval(HashMap<String, Val> state, Val eVal) {
		return null;
	}
	
}
