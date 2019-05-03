import java.util.*;

class SinglePrimaryItem extends PrimaryItem {

	SinglePrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		primary.printParseTree(indent);
	}
	
	Val Eval(HashMap<String,Val> state,Val eVal)
	{
		return primary.Eval(state, eVal);
	}

}