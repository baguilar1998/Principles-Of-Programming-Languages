
public class NeqPrimary extends Primary{
	Primary primary;
	
	NeqPrimary(Primary p){
		primary =p;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary> ");
		System.out.println(indent + indent.length() + " <primary> ");
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " ! ");
		System.out.println(indent1 + indent1.length() + " ! ");
		primary.printParseTree(indent1);
		
	}
}
