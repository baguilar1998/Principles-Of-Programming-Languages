import java.util.*;

public class ArrayConstructor extends RightSide{
	EList eList;
	
	ArrayConstructor(EList e) {
		eList = e;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		String indent2 = indent1 + " ";		
		eList.printParseTree(indent2);
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		HashMap<String,Val> arrayParam = new HashMap<String,Val>();
		for(String s: state.keySet()) arrayParam.put(s, state.get(s));
		eList.M(arrayParam);
		LinkedList<Integer> arraySize = new LinkedList<>();
		int counter = 1, size = 1;
		Val val ;
		do {
			val = arrayParam.get("e"+counter);
			if(val == null) {
				break;
			} else if (val.getClass() == FloatVal.class || val.getClass() == BoolVal.class) {
				IO.displayln("Error: array size expression evaluated to non-integer: " + val);
				return null;
				//break;
			}
			int index = ((IntVal)val).val;
			if(index < 0) {
				IO.displayln("Error: array size expression evaluated to non-positive integer: " + index);
				return null;
			}
			arraySize.add(index);
			size*=index;
			counter++;
		}while(val!=null);
		
		Val array[] = new Val[size];
		return new ArrayVal(arraySize,array);
	}
}
