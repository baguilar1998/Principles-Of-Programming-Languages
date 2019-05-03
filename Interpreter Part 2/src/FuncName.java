
public class FuncName {
	Id id;
	
	FuncName(Id i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <func name> " + id.id);
	}
}
