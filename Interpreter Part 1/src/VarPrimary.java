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
		// TODO Auto-generated method stub
		if(var.getClass() == ReturnVal.class) {
			return null;
			
		}else if(var.getClass() == IdVar.class) {
			return state.get(((IdVar)var).id.id);
		}
		return null;
	}
}
