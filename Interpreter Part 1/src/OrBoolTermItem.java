import java.util.*;
public class OrBoolTermItem  extends BoolTermItem{
	
	OrBoolTermItem(BoolTerm t){
		term = t;
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " ||");
		term.printParseTree(indent);
	}
	
	Val Eval(HashMap<String,Val> state, Val eVal) {
		Val termVal = term.Eval(state,eVal);
		if(eVal == null || termVal == null) return null;
		
		Class termClass = termVal.getClass();
		Class eClass = eVal.getClass();
		
		if(termClass == IntVal.class && eClass == IntVal.class) {
			((BoolVal)termVal).val = ((BoolVal)termVal).val || ((BoolVal)eVal).val;
			return termVal;
		}
		
		return null;
	}
}
