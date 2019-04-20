class IntVal extends Val
{
	int val;

	IntVal(int i)
	{
		val = i;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new IntVal(val);
	}

	double floatVal()
	{
		return (double)val;
	}
	
	boolean isZero()
	{
		return val == 0;
	}

	@Override
	boolean isNumber() {
		// TODO Auto-generated method stub
		return false;
	}
}