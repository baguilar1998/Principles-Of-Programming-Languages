import java.util.*;

public class FuncDefList {
	LinkedList<FuncDef> functionDefinitionList;
	
	FuncDefList(LinkedList<FuncDef> funcDefList) {
		functionDefinitionList = funcDefList;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <func def list>");
		System.out.println(indent + indent.length() + " <func def list>");
		String indent1 = indent + " ";
		for (FuncDef f: functionDefinitionList)
				f.printParseTree(indent1);
	}
}

