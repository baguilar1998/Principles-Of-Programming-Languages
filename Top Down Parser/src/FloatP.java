
public class FloatP extends Primary{

	double number;
	
	FloatP(double f) {
		number = f;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> " + number);
	}
}
