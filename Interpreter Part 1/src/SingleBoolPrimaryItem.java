import java.util.HashMap;

public class SingleBoolPrimaryItem extends BoolPrimaryItem{

	SingleBoolPrimaryItem(BoolPrimary t){
		item = t;
	}
	
	void printParseTree(String indent) {
		item.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		Val val = item.Eval(state, eVal);
		return val;
	}
}
