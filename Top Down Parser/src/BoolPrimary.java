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
}
