import java.util.ArrayList;
import java.util.HashMap;

public class Assignment extends Statement {

	Var var;
	RightSide rightSide;
	
	Assignment(Var v, RightSide rs) {
		var = v;
		rightSide = rs;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <assignment>");
		String indent2 = indent1 + " ";
		var.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " =");
		rightSide.printParseTree(indent2);
	}

	@Override
	void M(HashMap<String, Val> state) {
		Val eVal = rightSide.Eval(state);
		if(eVal == null) {
			return;
		}
		

		Class varType = var.getClass();
		if(varType == ReturnVal.class) {
			state.replace("returnVal", eVal);
		} else if(varType == IdVar.class) {
			state.put(((IdVar)var).id.id, eVal);
		} else if( varType == ArrayVar.class){
			String name = ((ArrayVar) var).arrayName.id.id;
			Val value = state.get(name);
			if(value == null) {
				IO.displayln("array variable " + name + " does not have a value");
				//IO.displayln("variable " + name + " has a non-array value: " + eVal);
				return;
			}
			if(value.getClass() != ArrayVal.class) {
				IO.displayln("variable " + name + " has a non-array value: " + eVal);
				return;	
			}
			ArrayVal arrayVal = (ArrayVal) value;

			HashMap<String,Val> arrayState = new HashMap<String,Val>();
			for(String s: state.keySet()) arrayState.put(s, state.get(s));
			
			((ArrayVar)var).M(arrayState);
			int counter = 1, size = 1;
			Val val;
			ArrayList<Integer> indices = new ArrayList<>();
			do {
				val = arrayState.get("e"+counter);
				if(val == null) {
					break;
				} else if (val.getClass() == FloatVal.class || val.getClass() == BoolVal.class) {
					IO.displayln("Error: index expression of array test evaluated to non-integer: " + val);
					return;
				}
				int index = ((IntVal)val).val;
				indices.add(index);
				counter++;
			}while(val!=null);
			
			for(int i=0;i<indices.size();i++) {
				int index1 = indices.get(i);
				int index2 = arrayVal.sizeList.get(i) -1;
				if(index1 < 0 || (index1 > index2) ) {
					IO.displayln("Error: index value of array test out of range: " + index1);
					return;
				}
			}
			int rank = ((ArrayVar) var).rank(arrayVal, indices);
			arrayVal.a[rank] = eVal;
			return;
		}
		
	}
}
