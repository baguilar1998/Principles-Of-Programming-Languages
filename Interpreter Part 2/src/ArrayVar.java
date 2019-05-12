import java.util.*;

public class ArrayVar extends Var{
	ArrayName arrayName;
	EList eList;
	
	ArrayVar(ArrayName an, EList el){
		arrayName = an;
		eList = el;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <array var>");
		String indent2 = indent1 + " ";
		arrayName.printParseTree(indent2);
		eList.printParseTree(indent2);
	}
	
	void M(HashMap<String,Val> state) {
		eList.M(state);
	}
	
	Val Eval(HashMap<String,Val> state) {
		Val value = state.get(arrayName.id.id);
		if(value == null) {
			String name = arrayName.id.id;
			IO.displayln("array variable "+ name + " does not have a value");
			return null;
		}
		if(value.getClass() != ArrayVal.class) {
			String name = arrayName.id.id;
			IO.displayln("variable "+ name + " has a non array value: " + state.get(name));
			return null;
		}
		ArrayVal v = (ArrayVal)value;
		HashMap<String,Val> arrayState = new HashMap<String,Val>();
		for(String s: state.keySet()) {
			if(s.contains("e"))continue;
			arrayState.put(s, state.get(s));
		}
		eList.M(arrayState);
		int counter = 1, size = 1;
		Val val;
		ArrayList<Integer> indices = new ArrayList<>();
		do {
			val = arrayState.get("e"+counter);
			if(val == null) {
				break;
			} else if (val.getClass() == FloatVal.class || val.getClass() == BoolVal.class) {
				IO.displayln("Error: index expression of array test evaluated to non-integer: " + val);
				return null;
			}
			int index = ((IntVal)val).val;

			indices.add(index);
			counter++;
		}while(val!=null);
		
		if(indices.size() != v.sizeList.size())return null;

		for(int i=0;i<indices.size();i++) {
			int index1 = indices.get(i);
			int index2 = v.sizeList.get(i);
			if(index1 < 0 || (index1 > index2) ) {
				IO.displayln("Error: index value of array test out of range: " + index1);
				return null;
			}
		}
		
		int rank = rank(v,indices);
		Val arrayVal = v.a[rank];
		if(arrayVal == null) {
			IO.displayln("element of array test does not have a value");
		}
		return v.a[rank];
	}
	
	public int rank(ArrayVal val, ArrayList<Integer> indices) {
		int rank = (val.sizeList.get(0) -1) - indices.get(0);
		for(int k = 1; k < val.sizeList.size(); k++) {
			int a = 0;
			int b = val.sizeList.get(k) -1;
			rank = rank * (b-a+1) + indices.get(k);
		}
		return rank;
	}
}
