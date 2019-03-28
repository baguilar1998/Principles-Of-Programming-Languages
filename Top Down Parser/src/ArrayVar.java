
public class ArrayVar extends Var{
	ArrayName arrayName;
	EList eList;
	
	ArrayVar(ArrayName an, EList el){
		arrayName = an;
		eList = el;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <array var>");
		System.out.println(indent1 + indent1.length() + " <array var>");
		String indent2 = indent1 + " ";
		arrayName.printParseTree(indent2);
		eList.printParseTree(indent2);
	}
}
