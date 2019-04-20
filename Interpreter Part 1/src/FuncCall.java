import java.util.*;
public class FuncCall {

	FuncName funcName;
	ExprList exprList;
	
	FuncCall(FuncName fn, ExprList el){
		funcName = fn;
		exprList = el;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <func call>");
		String indent1 = indent + " ";
		funcName.printParseTree(indent1);
		if(exprList!=null)exprList.printParseTree(indent1);
	}
	
	/**
	 * WORK ON THIS FUNCTION
	 * @param state current program state
	 */
	void M(HashMap<String,Val> state) {
		HashMap<String,Val> functionState = new HashMap<String,Val>();
		//
		// EVAL the expr list
		// if there is no program state for expr that means the function has no parameters
		// else add the function parameters into the new hashmap
		
		FuncDef funcDef = Parser.funcDefMap.get(funcName.id.id);
		funcDef.body.M(functionState);
		// Get the return val of the functionState hashmap and add it to your current program state
		state.put("returnVal", functionState.get("returnVal"));
	}
}
