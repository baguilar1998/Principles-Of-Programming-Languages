class FloatVal extends Val
{
	double val;

	FloatVal(double f)
	{
		val = f;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new FloatVal(val);
	}

	double floatVal()
	{
		return val;
	}
	
	boolean isZero()
	{
		return val == 0.0f;
	}

	@Override
	boolean isNumber() {
		// TODO Auto-generated method stub
		return false;
	}
}