import java.util.*;
public class BoolPrimary {

	EItem e1;
	CompOp op;
	EItem e2;

	BoolPrimary(EItem term1, CompOp o, EItem term2){
		e1 = term1;
		op = o;
		e2 = term2;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <boolPrimary>");
		String indent1 = indent + " ";
		e1.printParseTree(indent1);
		if(op!=null) {
			op.printParseTree(indent1);
			e2.printParseTree(indent1);
		}
	}
	
	Val Eval(HashMap<String,Val> state, Val val) {
		Val eVal1 = val;
		// Case where there is only one <E>
		if(op==null) {
			eVal1 = e1.Eval(state,val);
			return eVal1;
		// Case where there is an operator between two <E>
		} else {
			eVal1 = e1.Eval(state, val);
			Val eVal2 = e2.Eval(state, val);
			BoolVal boolVal;
			Class operator = op.getClass();
			
			// Evaluate ==
			if(operator == Eq.class) {
				//Invalid Cases
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() != BoolVal.class) {
					IO.displayln("Error: == operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				if(eVal1.getClass() != BoolVal.class && eVal2.getClass() == BoolVal.class) {
					IO.displayln("Error: == operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				// Case where both eval values are boolean
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() == BoolVal.class) {
					boolVal = new BoolVal( ((BoolVal)eVal1).val == ((BoolVal)eVal2).val );
					return boolVal;
				} else {
					// Case where eval values are just numbers
					boolVal = new BoolVal((eVal1).floatVal() == (eVal2).floatVal());
					return boolVal;
				}
			}
			
			// Evaluate !=
			else if (operator == NotEq.class) {
				//Invalid Cases
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() != BoolVal.class) {
					IO.displayln("Error: != operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				if(eVal1.getClass() != BoolVal.class && eVal2.getClass() == BoolVal.class) {
					IO.displayln("Error: != operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				// Case where both eval values are boolean
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() == BoolVal.class) {
					boolVal = new BoolVal( ((BoolVal)eVal1).val != ((BoolVal)eVal2).val );
					return boolVal;
				} else {
					// Case where eval values are just numbers
					boolVal = new BoolVal((eVal1).floatVal() != (eVal2).floatVal());
					return boolVal;
				}

			}
			
			// Evaluate <
			else if(operator == Lt.class) {
				//Invalid Cases
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() != BoolVal.class) {
					IO.displayln("Error: < operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				if(eVal1.getClass() != BoolVal.class && eVal2.getClass() == BoolVal.class) {
					IO.displayln("Error: < operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				boolVal = new BoolVal((eVal1).floatVal() < (eVal2).floatVal());
				return boolVal;
			}
			
			// Evaluate <=
			else if(operator == Le.class) {
				//Invalid Cases
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() != BoolVal.class) {
					IO.displayln("Error: <= operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				if(eVal1.getClass() != BoolVal.class && eVal2.getClass() == BoolVal.class) {
					IO.displayln("Error: <= operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				boolVal = new BoolVal((eVal1).floatVal() <= (eVal2).floatVal());
				return boolVal;

			}
			
			//Evaluate >
			else if(operator == Gt.class) {
				//Invalid Cases
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() != BoolVal.class) {
					IO.displayln("Error: > operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				if(eVal1.getClass() != BoolVal.class && eVal2.getClass() == BoolVal.class) {
					IO.displayln("Error: > operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				boolVal = new BoolVal((eVal1).floatVal() > (eVal2).floatVal());
				return boolVal;

			}
			
			// Evaluate >=
			else {
				//Invalid Cases
				if(eVal1.getClass() == BoolVal.class && eVal2.getClass() != BoolVal.class) {
					IO.displayln("Error: >= operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				if(eVal1.getClass() != BoolVal.class && eVal2.getClass() == BoolVal.class) {
					IO.displayln("Error: >= operator cannot be applied to ["+eVal1+","+eVal2+"]");
					return null;
				}
				boolVal = new BoolVal((eVal1).floatVal() >= (eVal2).floatVal());
				return boolVal;
			}
			
		}
		
	}
}
