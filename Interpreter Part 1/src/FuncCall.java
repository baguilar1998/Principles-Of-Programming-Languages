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
		HashMap<String,Val> functionState = new HashMap<String,Val>();
		FuncDef funcDef = Parser.funcDefMap.get(funcName.id.id);
		if(exprList!=null) {
			for(String p: state.keySet()) functionState.put(p,state.get(p));
			exprList.M(functionState);
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

		functionState.put("returnVal", null);
		funcDef.body.M(functionState);
		Val returnVal = functionState.get("returnVal");
		return returnVal;
	}
}
