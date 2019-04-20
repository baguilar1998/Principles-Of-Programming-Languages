import java.util.*;

class MulPrimaryItem extends PrimaryItem

// Represents "* <primary>"

{
	// Primary primary; inherited from PrimaryItem

	MulPrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " *");
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

		if ( termClass == IntVal.class && eClass == IntVal.class )
		{
			((IntVal)termVal).val = ((IntVal)eVal).val * ((IntVal)termVal).val;
			return termVal;
		}
		else if ( termClass == IntVal.class ) // eClass == FloatVal.class
		{

			((FloatVal)eVal).val = ((IntVal)eVal).val * ((FloatVal)termVal).val;
			return eVal;
		}
		else // termClass == FloatVal.class
		{

			((FloatVal)termVal).val = eVal.floatVal() * termVal.floatVal();
			return termVal;
		}
	}



}