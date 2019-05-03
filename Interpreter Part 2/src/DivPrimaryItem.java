import java.util.*;

class DivPrimaryItem extends PrimaryItem

// Represents "/ <primary>"

{
	// Primary primary; inherited from PrimaryItem

	DivPrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " /");
		primary.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		Val termVal = primary.Eval(state, eVal);
		if ( termVal == null || eVal == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		Class termClass = termVal.getClass();
		Class    eClass =    eVal.getClass();

		if(termVal instanceof BoolVal) {
			IO.displayln("Error: / operator cannot be applied to " + termVal);
			return null;
		}
		
		if(eVal instanceof BoolVal) {
			IO.displayln("Error: / operator cannot be applied to " + eVal);
			return null;
		}
		
		
		if ( termClass == IntVal.class && eClass == IntVal.class )
		{
			if(((IntVal)termVal).isZero()) {
				IO.displayln("Error: divison by 0");
				return null;
			}
			((IntVal)termVal).val = ((IntVal)eVal).val / ((IntVal)termVal).val;
			return termVal;
		}
		else if ( termClass == IntVal.class ) // eClass == FloatVal.class
		{
			if(((FloatVal)termVal).isZero()) {
				IO.displayln("Error: divison by 0");
				return null;
			}
			FloatVal newVal = new FloatVal(((IntVal)termVal).val / ((FloatVal)eVal).val);
			return newVal;
		}
		else // termClass == FloatVal.class
		{
			if(((FloatVal)termVal).isZero()) {
				IO.displayln("Error: divison by 0");
				return null;
			}
			((FloatVal)termVal).val = eVal.floatVal() /termVal.floatVal();
			return termVal;
		}
	}

}