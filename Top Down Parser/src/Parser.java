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
			} else {
				errorMsg(5);
			}
		} else {
			errorMsg(5);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <S List>
	 */
	public static SList sList() {
		LinkedList<Statement> sList = new LinkedList<Statement>();
		Statement s;
		while(state == State.Id || state == State.Keyword_if || state == State.Keyword_while
				|| state == State.LBrace || state == State.Keyword_print) {
			s = statement();
			sList.add(s);
		}
		return new SList(sList);
	}
	
	public static Statement statement() {
		if (state == State.Id) {
			return assignment();
		} else if (state == State.Keyword_if) {
			
		} else if (state == State.Keyword_while) {
			
		} else if (state == State.LBrace) {
		
		} else if (state == State.Keyword_print) {
			
		} else {
			errorMsg(5);
		}
		return null;
	}
	
	public static Assignment assignment() {
		Var v = var();
		if (state == State.Assign) {
			getToken();
			RightSide rs = rightSide();
			if(state == State.Semicolon) {
				getToken();
				return new Assignment(v,rs);
			} else {
				//EXPECTED ;
				errorMsg(5);
			}
		}else {
			// EXPECTED =
			errorMsg(5);
		}
		return null;
	}
	
	/***
	 * Top Down Parser for <var>
	 */
	public static Var var() {
		String id = t;
		getToken();
		if(state == State.LBracket) {
			return arrayVar();
			
		} else if (state == State.Keyword_returnVal) {
			getToken();
			return new ReturnVal();
		}
		
		return new IdVar(id);
	}
	
	public static ArrayVar arrayVar() {
		getToken();
		return null;
	}
	
	public static RightSide rightSide() {
		if(state == State.Keyword_new) {
			
		} else {
			return exprRightSide();
		}
		return null;
	}
	
	public static ExprRightSide exprRightSide() {
		Expr ex = expr();
		return new ExprRightSide(ex);
	}
	
	public static Expr expr() {
		LinkedList<BoolTermItem> boolTermList = new LinkedList<BoolTermItem>();
		BoolTerm term = boolTerm();
		boolTermList.add(new SingleBoolTermItem(term));
		while (state == State.Or) {
			getToken();
			term = boolTerm();
			boolTermList.add(new OrBoolTermItem(term));
		}
		return new Expr(boolTermList);
		
	}
	
	public static BoolTerm boolTerm() {
		LinkedList<BoolPrimaryItem> boolPrimaryList = new LinkedList<BoolPrimaryItem>();
		BoolPrimary primary = boolPrimary();
		boolPrimaryList.add(new SingleBoolPrimaryItem(primary));
		while (state == State.Ampersand) {
			getToken();
			primary =  boolPrimary();
			boolPrimaryList.add(new AndBoolPrimaryItem(primary));
		}
		return new BoolTerm(boolPrimaryList);
	}
	
	public static BoolPrimary boolPrimary() {
		LinkedList<ETermItem> eList = new LinkedList<ETermItem>();
		EItem e = E();
		eList.add(new SingleEItem(e));
		while (state == State.Ge || state == State.Gt || state == State.Le ||
				state == State.Lt || state == State.Eq || state == State.Neq) {
			State op = state;
			CompOp operation;
			getToken();
			e = E();
			if(op == State.Ge) {
				operation = new Ge();
				eList.add(new CompOpEItem(e,operation));
			} else if(op == State.Gt) {
				operation = new Gt();
				eList.add(new CompOpEItem(e,operation));
			} else if(op == State.Le) {
				operation = new Le();
				eList.add(new CompOpEItem(e,operation));
			} else if(op == State.Lt) {
				operation = new Lt();
				eList.add(new CompOpEItem(e,operation));
			} else if (op == State.Eq) {
				operation = new Eq();
				eList.add(new CompOpEItem(e,operation));
			} else {
				operation = new NotEq();
				eList.add(new CompOpEItem(e,operation));
			}
		}
		return new BoolPrimary(eList);
	}
	
	public static EItem E() {
		LinkedList<TermItem> termItemList = new LinkedList<TermItem>();

		Term term = term();
		termItemList.add(new SingleTermItem(term));
		while ( state == State.Add | state == State.Sub )
		{
			State op = state;
			getToken();
			term = term();
			if ( op == State.Add )
				termItemList.add(new AddTermItem(term));
			else // op == State.Minus
				termItemList.add(new SubTermItem(term));
		}
		return new EItem(termItemList);
	}
	
	public static Term term() {
		LinkedList<PrimaryItem> primaryItemList = new LinkedList<PrimaryItem>();

		Primary primary = primary();
		primaryItemList.add(new SinglePrimaryItem(primary));
		while ( state == State.Mul | state == State.Div )
		{
			State op = state;
			getToken();
			primary = primary();
			if ( op == State.Mul )
				primaryItemList.add(new MulPrimaryItem(primary));
			else // op == State.Div
				primaryItemList.add(new DivPrimaryItem(primary));
		}
		return new Term(primaryItemList);	
	}
	
	public static Primary primary() {
		if (state == State.Id) {
			Var v = var();
			return new VarPrimary(v);
		} else if (state == State.Int) {
			Int i = new Int(Integer.parseInt(t));
			getToken();
			return i;
		} else if (state == State.Float) {
			FloatP f = new FloatP(Float.parseFloat(t));
			getToken();
			return f;
		} else if (state == State.FloatE) {
			FloatP f = new FloatP(Float.parseFloat(t));
			getToken();
			return f;
		} else if (state == State.LParen) {
			getToken();
			Expr expr = expr();
			if (state == State.RParen) {
				getToken();
				return expr;
			} else {
				errorMsg(5);
				return null;
			}
		} else if (state == State.Sub) {
			getToken();
			Primary p = primary();
			return new NegPrimary(p);
		} else if (state == State.Neq) {
			
		} else {
			errorMsg(5);
		}
		return null;
	}

	/*

	

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
		//if ( ! t.isEmpty() )
			//errorMsg(5);
		//else if ( ! errorFound )
			//functionDefinitionList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}