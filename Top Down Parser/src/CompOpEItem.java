
public class CompOpEItem extends ETermItem{
	CompOp op;
	
	CompOpEItem(EItem e){
		item = e;
	}
	
	void printParseTree(String indent) {
		op.printParseTree(indent);
		item.printParseTree(indent);
	}
}
