
public class CompOpEItem extends ETermItem{
	CompOp op;
	
	CompOpEItem(EItem e, CompOp o){
		item = e;
		op =o;
	}
	
	void printParseTree(String indent) {
		op.printParseTree(indent);
		item.printParseTree(indent);
	}
}
