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
	Val Eval(HashMap<String,Val> state, Val eVal) {
		HashMap<String,Val> functionState = new HashMap<String,Val>();
		FuncDef funcDef = Parser.funcDefMap.get(funcName.id.id);
		//System.out.println(eVal + " CURRENT EVAL");
		if(exprList!=null) {
			for(String p: state.keySet()) functionState.put(p,state.get(p));
			//System.out.println(functionState + " BEFORE FUNC EVAL");
			exprList.M(functionState);
			//System.out.println(functionState + "AFTER FUNC EVAL");
			LinkedList<Parameter> list = funcDef.head.parameterList.parameterList;
			int counter =1;
			for(Parameter p: list) {
				String tempParam = "e"+counter;
				Val temp = functionState.get(tempParam);
				functionState.remove(tempParam);
				String actualParam = p.id.id;
				functionState.put(actualParam, temp);
				++counter;
			}

		}

		functionState.put("returnVal", null);
		//System.out.println(functionState);
		funcDef.body.M(functionState);
		// Get the return val of the functionState hashmap and add it to your current program state
		//System.out.println(functionState + " WITH RETURN VAL");
		return functionState.get("returnVal");
	}
}
