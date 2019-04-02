
public class Int extends Primary{
	int number;
	
	Int(int i){
		number = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> " + number);
	}
	
}
