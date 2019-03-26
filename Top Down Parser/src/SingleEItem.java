
public class SingleEItem extends ETermItem{

	SingleEItem(EItem e) {
		item = e;
	}
	
	void printParseTree(String indent) {
		item.printParseTree(indent);
	}
	
}
