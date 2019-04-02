
public class IdVar extends Var{
	Id id;
	
	IdVar(Id i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <id var> " + id.id);
	}
}
