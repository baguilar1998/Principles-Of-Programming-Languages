
public class AndBoolPrimaryItem extends BoolPrimaryItem{

	AndBoolPrimaryItem(BoolPrimary p){
		item = p;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length()+ " &&");
		System.out.println(indent + indent.length()+ " &&");
		item.printParseTree(indent);
	}
}
