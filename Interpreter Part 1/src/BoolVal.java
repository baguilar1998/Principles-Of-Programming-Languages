
public class BoolVal extends Val{

	boolean val;
	BoolVal(boolean i){
		val=i;
	}
	
	@Override
	Val cloneVal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	double floatVal() {
		// TODO Auto-generated method stub
		return 0;
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
		return val+" ";
	}

}
