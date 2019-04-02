
public class OrBoolTermItem  extends BoolTermItem{
	
	OrBoolTermItem(BoolTerm t){
		term = t;
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " ||");
		term.printParseTree(indent);
	}
}
