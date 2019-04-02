
public class ArrayName {
	Id id;
	
	ArrayName(Id i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <array name> " + id.id);
	}
}
