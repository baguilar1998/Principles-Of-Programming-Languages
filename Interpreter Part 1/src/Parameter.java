
public class Parameter {
	Id id;
	
	Parameter(Id i) {
		id = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <parameter> " +id.id);
	}
	
}
