import java.util.*;

public abstract class Parser extends LexArith{
	static boolean errorFound = false;

	/**
	 * Top Down Parser for <func def list>
	 */
	public static FuncDefList funcDefList() {
		FuncDef f = funcDef();
		LinkedList<FuncDef> list = new LinkedList<FuncDef>();
		list.add(f);
		while( state == State.Id ) {
			f = funcDef();
			list.add(f);
		}
		return new FuncDefList(list);
	}
		
	/**
	 * Top Down Parser for <func def>
	 */
	public static FuncDef funcDef(){
		Header header = header();
		Body body = body();
		return new FuncDef(header,body);
	}
	
	/**
	 * Top Down Parser for <header>
	 */
	public static Header header() {
		FuncName funcName = funcName();
		ParameterList pList = parameterList();
		if( state == State.LParen) {
			getToken();
			pList = parameterList();
			if(state == State.RParen) {
				getToken();
				return new Header(funcName, pList);
			} else {
				errorMsg(5);
			}
		} else {
			errorMsg(5);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <func name>
	 */
	public static FuncName funcName() {
		if (state == State.Id) {
			String id = t;
			getToken();
			return new FuncName(id);
			
		} else {
			errorMsg(5);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <parameter list>
	 */
	public static ParameterList parameterList () {
		Parameter p = parameter();
		LinkedList<Parameter> parameterList = new LinkedList<Parameter>();
		parameterList.add(p);
		while( state == State.Comma) {
			getToken();
			p = parameter();
			parameterList.add(p);
		}
		return new ParameterList(parameterList);
	}
	
	/**
	 * Top Down Parser for <parameter>
	 */
	public static Parameter parameter() {
	if (state == State.Id) {
			String id = t;
			getToken();
			return new Parameter(id);
			
		} else {
			errorMsg(5);
		}
		return null;
	}
	
	public static Body body() {
		if(state == State.LBrace) {
			getToken();
			SList sList = sList();
			if(state == State.RBrace) {
				getToken();
				return new Body(sList);
			}
		}
		return null;
	}
	
	public static SList sList() {
		Statement s;
		if (state == State.Id) {
			s = assignment();
		}
		return null;
	}
	
	public static Assignment assignment() {
		return null;
	}
	
	/*public static AssignmentList assignmentList()
	
	// <assignment list> --> { <assignment> }+
	
	{
		LinkedList<Assignment> assignmentList = new LinkedList<Assignment>();

		Assignment assignment = assignment();
		assignmentList.add(assignment);
		while ( state == State.Id )
		{
			assignment = assignment();
			assignmentList.add(assignment); // append "assignment" to the end of "assignmentList"
		}
		return new AssignmentList(assignmentList);
	}

	public static Assignment assignment()
	
	// <assignment> --> <id> = <E> ";"
	
	{
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			if ( state == State.Assign )
			{
				getToken();
				E e = E();
				if ( state == State.Semicolon )
				{
					getToken();
					return new Assignment(id, e);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}

	public static E E()

	// <E> --> <term> { (+|-) <term> }

	{
		LinkedList<TermItem> termItemList = new LinkedList<TermItem>();

		Term term = term();
		termItemList.add(new SingleTermItem(term));
		while ( state == State.Plus | state == State.Minus )
		{
			State op = state;
			getToken();
			term = term();
			if ( op == State.Plus )
				termItemList.add(new AddTermItem(term));
			else // op == State.Minus
				termItemList.add(new SubTermItem(term));
		}
		return new E(termItemList);
	}

	public static Term term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		LinkedList<PrimaryItem> primaryItemList = new LinkedList<PrimaryItem>();

		Primary primary = primary();
		primaryItemList.add(new SinglePrimaryItem(primary));
		while ( state == State.Times | state == State.Div )
		{
			State op = state;
			getToken();
			primary = primary();
			if ( op == State.Times )
				primaryItemList.add(new MulPrimaryItem(primary));
			else // op == State.Div
				primaryItemList.add(new DivPrimaryItem(primary));
		}
		return new Term(primaryItemList);
	}

	public static Primary primary()

	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"

	{
		switch ( state )
		{
			case Id:
										
				Id id = new Id(t);
				getToken();
				return id;
				
			case Int:

				Int intElem = new Int(Integer.parseInt(t));
				getToken();
				return intElem;

			case Float: case FloatE:

				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;

			case LParen:
				
				getToken();
				E e = E();
				if ( state == State.RParen )
				{
					getToken();
					Parenthesized paren = new Parenthesized(e);
					return paren;
				}
				else
				{
					errorMsg(1);
					return null;
				}

			default:

				errorMsg(2);
				return null;
		}
	}*/
	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, or ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id expected"); return;		
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		//setLex();

		getToken();

		// AssignmentList assignmentList = assignmentList(); // build a parse tree
		FuncDefList functionDefinitionList = funcDefList();
		functionDefinitionList.printParseTree("");
		if ( ! t.isEmpty() )
			errorMsg(5);
		else if ( ! errorFound )
			functionDefinitionList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}