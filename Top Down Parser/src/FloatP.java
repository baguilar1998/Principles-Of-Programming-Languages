
public class FloatP extends Primary{

	float number;
	
	FloatP(float f) {
		number = f;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> " + number);
		System.out.println(indent + indent.length() + " <primary> " + number);
	}
}
