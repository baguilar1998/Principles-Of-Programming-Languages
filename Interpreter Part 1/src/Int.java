import java.util.HashMap;

public class Int extends Primary{
	int number;
	
	Int(int i){
		number = i;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> " + number);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		return new IntVal(number);
	}
	
}
