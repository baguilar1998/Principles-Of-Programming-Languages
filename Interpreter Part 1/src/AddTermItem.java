import java.util.*;

class AddTermItem extends TermItem

// Represents "+ <term>"

{
	// Term term; inherited from TermItem

	AddTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " +");
		term.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state, Val eVal) {
		Val termVal = term.Eval(state, eVal);
		if ( termVal == null || eVal == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		Class termClass = termVal.getClass();
		Class    eClass =    eVal.getClass();

		if ( termClass == IntVal.class && eClass == IntVal.class )
		{
			((IntVal)termVal).val = ((IntVal)termVal).val + ((IntVal)eVal).val;
			return termVal;
		}
		else if ( termClass == IntVal.class ) // eClass == FloatVal.class
		{
			((FloatVal)termVal).val = ((IntVal)termVal).val + ((FloatVal)eVal).val;
			return termVal;
		}
		else // termClass == FloatVal.class
		{
			((FloatVal)termVal).val = termVal.floatVal() + eVal.floatVal();
			return termVal;
		}
	}

}