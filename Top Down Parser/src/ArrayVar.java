
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
		//IO.displayln(indent1 + indent1.length() + " <array name>" + );
		//System.out.println(indent1 + indent1.length() + " <id var>" + id);
	}
}
