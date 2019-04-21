
public class BoolVal extends Val{

	boolean val;
	BoolVal(boolean i){
		val=i;
	}
	
	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return new BoolVal(val);
		
	}

	@Override
	double floatVal() {
		// TODO Auto-generated method stub
		if(val)return 1.0;
		return 0.0;
	}

	@Override
	boolean isNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return val+"";
	}

}
