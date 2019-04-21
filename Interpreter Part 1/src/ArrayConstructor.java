import java.util.HashMap;

public class ArrayConstructor extends RightSide{
	EList eList;
	
	ArrayConstructor(EList e) {
		eList = e;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		String indent2 = indent1 + " ";		
		eList.printParseTree(indent2);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}
