
public class Header {
	FuncName funcName;
	ParameterList parameterList;
	
	Header(FuncName f, ParameterList p){
		funcName = f;
		parameterList = p;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <header>");
		System.out.println(indent + indent.length() + " <header>");
		String indent1 = indent + " ";
		funcName.printParseTree(indent1);
		if(parameterList != null) parameterList.printParseTree(indent1);
	}
}
