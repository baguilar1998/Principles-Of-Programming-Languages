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
	
	Val Eval(HashMap<String,Val> state, Val eVal) {
		
		// Create a new state for the function call
		HashMap<String,Val> functionState = new HashMap<String,Val>();
		
		// Get the function that we are trying to call
		FuncDef funcDef = Parser.funcDefMap.get(funcName.id.id);
		
		// Case: if the function that the user is trying to call does not exist
		if (funcDef == null) {
			IO.displayln("Error: "+ funcName.id.id +"function is undefined");
			return null;
		}
		
		// If there are parameters inside the function
		if(exprList!=null) {
			// Store all current variables that we have into function state for parameter evaluation
			for(String p: state.keySet()) functionState.put(p,state.get(p));
			
			// Evaluate each expression
			exprList.M(functionState);
			
			// Map each parameter now to it's actual parameter name
			LinkedList<Parameter> list = funcDef.head.parameterList.parameterList;
			int counter =1;
			for(Parameter p: list) {
				String tempParam = "e"+counter;
				Val temp = functionState.get(tempParam);
				if(temp == null)return null;
				functionState.remove(tempParam);
				String actualParam = p.id.id;
				functionState.put(actualParam, temp);
				++counter;
			}
		}

		// Add the returnVal to the function state
		functionState.put("returnVal", null);
		
		// Evaluate the function
		funcDef.body.M(functionState);
		
		// Get the return value of the function call
		Val returnVal = functionState.get("returnVal");
		
		// Return the value
		return returnVal;
	}
}
