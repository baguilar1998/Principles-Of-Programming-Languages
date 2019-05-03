import java.util.HashMap;

public class FloatP extends Primary{

	double number;
	
	FloatP(double f) {
		number = f;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> " + number);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		return new FloatVal(number);
	}
}
