import java.util.HashMap;

public class NeqPrimary extends Primary{
	Primary primary;
	
	NeqPrimary(Primary p){
		primary =p;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> ");
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " ! ");
		primary.printParseTree(indent1);
		
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		Val not = primary.Eval(state, eVal);
		if(not.getClass() == BoolVal.class) {
			((BoolVal)not).val =  !((BoolVal)not).val;
			return not;
		}
		return null;
	}
}
