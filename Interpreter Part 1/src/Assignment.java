import java.util.HashMap;

public class Assignment extends Statement {

	Var var;
	RightSide rightSide;
	
	Assignment(Var v, RightSide rs) {
		var = v;
		rightSide = rs;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <assignment>");
		String indent2 = indent1 + " ";
		var.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " =");
		rightSide.printParseTree(indent2);
	}

	@Override
	void M(HashMap<String, Val> state) {
		Val eVal = rightSide.Eval(state);
		if(eVal == null) {
			return;
		}
		
		Class varType = var.getClass();
		if(varType == ReturnVal.class) {
			state.replace("returnVal", eVal);
		} else if(varType == IdVar.class) {
			state.put(((IdVar)var).id.id, eVal);
		} else {
			// ARRAY VAR FOR PROJECT 4
			return;
		}
		
	}
}
