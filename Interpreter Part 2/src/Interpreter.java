import java.util.*;

public abstract class Interpreter extends Parser
{	
	public static void main(String argv[])
	{
		// argv[0]: input file containing a string of <fun def list>
		// argv[1]: output file displaying lexical/syntax error messages
		
		setIO( argv[0], argv[1] );
		//setLex();

		getToken();
		FuncDefList funDefList = funcDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		else if ( ! errorFound )
		{
			FuncDef funDef = Parser.funcDefMap.get("main");  // get the FunDef object of main function
			if ( funDef != null )
			{
				Body body = funDef.body;  // get the body of main function
				HashMap<String,Val> newState = new HashMap<String,Val>();
				body.M(newState);
			}
			else {
				IO.displayln("Error: main function is undefined");
			}
		}

		closeIO();
		System.out.println("Success");
	}
}