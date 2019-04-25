import java.util.HashMap;

public class VarPrimary extends Primary{

	Var var;
	
	VarPrimary(Var v){
		var = v;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary>");
		String indent1 = indent + " ";
		var.printParseTree(indent1);
		
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		//Case: ReturnVal which gets no assignment
		if(var.getClass() == ReturnVal.class) {
			return null;
			
		}
		
		//Case: variable call, get the value of the variable
		else if(var.getClass() == IdVar.class) {
			Val value = state.get(((IdVar)var).id.id);
			
			// Case: when the variable does not exist
			if(value == null) {
				IO.displayln("variable " + ((IdVar)var).id.id + " does not exist");
				return null;
			}
			
			// Clone the value or else you pass the actual reference of the value in the function state
			return value.cloneVal();
		}
		
		return null;
	}
}
