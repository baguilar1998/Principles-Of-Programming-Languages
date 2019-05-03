
public class FuncDef {
	Header head;
	Body body;
	
	FuncDef(Header h, Body b) {
		head = h;
		body = b;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <fun def>");
		String indent1 = indent + " ";
		head.printParseTree(indent1);
		body.printParseTree(indent1);
	}
}
