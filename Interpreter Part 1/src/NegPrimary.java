import java.util.HashMap;

public class NegPrimary extends Primary{
	Primary primary;
	
	NegPrimary(Primary p){
		primary =p;
	}
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> ");
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " - ");
		primary.printParseTree(indent1);
		
	}
	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		Val neg = primary.Eval(state, eVal);
		
		Class negClass = neg.getClass();
		
		if(negClass == IntVal.class) {
			((IntVal)neg).val = -((IntVal)neg).val;
		} else {
			((FloatVal)neg).val = -((FloatVal)neg).val;
		}
		return neg;
	}
}
