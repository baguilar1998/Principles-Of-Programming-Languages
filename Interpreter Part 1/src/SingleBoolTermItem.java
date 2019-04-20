import java.util.*;
public class SingleBoolTermItem extends BoolTermItem {
	
	SingleBoolTermItem(BoolTerm t){
		term = t;
	}
	
	void printParseTree(String indent) {
		term.printParseTree(indent);
	}
	
	Val Eval(HashMap<String,Val> state, Val eVal) {
		eVal = term.Eval(state,eVal);
		return eVal;
	}
}
