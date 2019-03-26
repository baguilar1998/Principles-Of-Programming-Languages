
public class SingleBoolPrimaryItem extends BoolPrimaryItem{

	SingleBoolPrimaryItem(BoolPrimary t){
		item = t;
	}
	
	void printParseTree(String indent) {
		item.printParseTree(indent);
	}
}
