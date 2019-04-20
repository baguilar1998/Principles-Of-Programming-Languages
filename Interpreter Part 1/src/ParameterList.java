import java.util.*;
public class ParameterList {
	LinkedList<Parameter> parameterList;
	
	ParameterList(LinkedList<Parameter> pList) {
		parameterList = pList;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <parameter list>");
		String indent1 = indent + " ";
		for(Parameter p: parameterList) 
			p.printParseTree(indent1);
	}
}
