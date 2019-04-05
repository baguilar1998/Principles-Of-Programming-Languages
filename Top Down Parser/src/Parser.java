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
		FuncName funcName;
		if(state == State.Id) {
			String id = t;
			getToken();
			funcName = funcName(id);
			ParameterList pList;
			if( state == State.LParen) {
				getToken();
				if(state == State.Id) {
					pList = parameterList();
				} else {
					pList = null;
				}
				if(state == State.RParen) {
					getToken();
					return new Header(funcName, pList);
				} else {
					// Expected )
					errorMsg(6);
				}
			} else {
				//Expected {
				errorMsg(7);
			}
		} else {
			//Expected Id
			errorMsg(5);
		}

		return null;
	}
	
	/**
	 * Top Down Parser for <func name>
	 */
	public static FuncName funcName(String id) {
		Id funId = new Id(id);
		return new FuncName(funId);
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
			Id pId = new Id(id);
			getToken();
			return new Parameter(pId);
			
		} else {
			// Expected Id
			errorMsg(5);
		}
		return null;
	}
	
	/**
	 * 
	 * Top Down Parser for <body>
	 */
	public static Body body() {
		if(state == State.LBrace) {
			getToken();
			SList sList = sList();
			if(state == State.RBrace) {
				getToken();
				return new Body(sList);
			} else {
				// Expected }
				errorMsg(9);
			}
		} else {
			// Expected {
			errorMsg(8);
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
				|| state == State.LBrace || state == State.Keyword_print || state == State.Keyword_returnVal) {
			s = statement();
			sList.add(s);
		}
		return new SList(sList);
	}
	

	/**
	 * Top Down Parser for <statement>
	 */
	public static Statement statement() {
		if (state == State.Id) {
			String id = t;
			getToken();
			if(state == State.LParen) {
				return funcCallStatement(id);
			} else {
				return assignment(id);
			}
		} else if (state == State.Keyword_if) {
			return cond();
		} else if (state == State.Keyword_while) {
			return While();
		} else if (state == State.LBrace) {
			return block();
		} else if (state == State.Keyword_print) {
			return print();
		} else if (state == State.Keyword_returnVal) {
			String id = t;
			getToken();
			return assignment(id);
		} else {
			// Expected id, (, if, while, print, returnVal
			errorMsg(12);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <block>
	 */
	public static Block block() {
		getToken();
		SList s = sList();
		if(state == State.RBrace) {
			getToken();
			return new Block(s);
		} else {
			//EXPECTED }
			errorMsg(9);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <func call statement>
	 */
	public static FuncCallStatement funcCallStatement(String id) {
		FuncCall funcCall = funcCall(id);
		if(state == State.Semicolon) {
			getToken();
			return new FuncCallStatement(funcCall);
		} else {
			// Expected ;
			errorMsg(4);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <func call>
	 */
	public static FuncCall funcCall(String id) {
		FuncName funcName = funcName(id);
		if(state == State.LParen) {
			getToken();
			if(state != State.RParen) {
				ExprList exprList = exprList();
				if(state == State.RParen) {
					getToken();
					return new FuncCall(funcName,exprList);
				} else {
					//Expected )
					errorMsg(6);
				}
			} else {
				getToken();
				return new FuncCall(funcName, null);
			}
		} else {
			//Expected (
			errorMsg(7);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <expr list>
	 */
	public static ExprList exprList() {
		LinkedList<Expr> exprList = new LinkedList<>();
		Expr expr = expr();
		exprList.add(expr);
		while(state == State.Comma) {
			getToken();
			expr = expr();
			exprList.add(expr);
		}
		return new ExprList(exprList);
	}
	
	/**
	 * Top Down Parser for <assignment>
	 */
	public static Assignment assignment(String id) {
		Var v = var(id);
		if (state == State.Assign) {
			getToken();
			RightSide rs = rightSide();
			if(state == State.Semicolon) {
				getToken();
				return new Assignment(v,rs);
			} else {
				//EXPECTED ;
				errorMsg(4);
			}
		}else {
			// EXPECTED =
			errorMsg(3);
		}
		return null;
	}
	
	/***
	 * Top Down Parser for <var>
	 */
	public static Var var(String id) {
		if(state == State.LBracket) {
			return arrayVar(id);
		} else if (id.equals("returnVal")) {
			return new ReturnVal();
		}
		
		return idVar(id);
	}
	
	/**
	 * Top Down Parser for <id var>
	 */
	public static IdVar idVar(String id) {
		Id varId = new Id(id);
		return new IdVar(varId);
	}
	
	/**
	 * Top Down Parser for <array name>
	 */
	public static ArrayName arrayName(String id) {
		Id varId = new Id(id);
		return new ArrayName(varId);
	}

	/**
	 * Top Down Parser for <array var>
	 */
	public static ArrayVar arrayVar(String id) {
		getToken();
		ArrayName arrayName = arrayName(id);
		EList elist = eList();
		if(state == State.RBracket) {
			getToken();
			return new ArrayVar(arrayName,elist);
		} else {
			//EXPECTED ]
			errorMsg(11);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <EList>
	 */
	public static EList eList() {
		LinkedList<EItem> eList = new LinkedList<EItem>();
		EItem eItem = E();
		eList.add(eItem);
		while(state == State.Comma) {
			getToken();
			eItem = E();
			eList.add(eItem);
		}
		return new EList(eList);
		
	}
	
	/**
	 * Top Down Parser for <print>
	 */
	public static Print print() {
		getToken();
		Expr expr = expr();
		if(state == State.Semicolon) {
			getToken();
			return new Print(expr);
		} else {
			// Expected ;
			errorMsg(4);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <while>
	 */
	public static While While() {
		getToken();
		if(state == State.LParen) {
			getToken();
			Expr expr = expr();
			if( state == State.RParen) {
				getToken();
				Statement s = statement();
				return new While(expr,s);
			} else {
				//EXPECTED RPAREN
				errorMsg(6);
			}
		} else {
			// EXPECTED LPAREN
			errorMsg(5);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <cond>
	 */
	public static Cond cond() {
		getToken();
		if(state == State.LParen) {
			getToken();
			Expr expr = expr();
			if(state == State.RParen) {
				getToken();
				Statement s1 = statement();
				if(state == State.Keyword_else) {
					getToken();
					Statement s2 = statement();
					return new Cond(expr,s1,s2);
				} else {
					return new Cond(expr,s1);
				}
			} else {
				//EXPECTED RPAREN
				errorMsg(6);
			}
		} else {
			//EXPECTED LPAREN
			errorMsg(5);
		}
		return null;
	}

	/**
	 * Top Down Parser for <right side>
	 */
	public static RightSide rightSide() {
		if(state == State.Keyword_new) {
			return arrayConstructor();
		} else {
			return exprRightSide();
		}
	}
	

	/**
	 * Top Down Parser for <expr right side>
	 */
	public static ExprRightSide exprRightSide() {
		Expr ex = expr();
		return new ExprRightSide(ex);
	}
	
	/**
	 * Top Down Parser for <array constrcutor>
	 */
	public static ArrayConstructor arrayConstructor() {
		getToken();
		if(state == State.LBracket) {
			getToken();
			EList elist = eList();
			if(state == State.RBracket) {
				getToken();
				return new ArrayConstructor(elist);
			} else {
				//EXPECTED RBRACKET
				errorMsg(10);
			}
		} else {
			//EXPECTED LBRACKET
			errorMsg(11);
		}
		return null;
	}
	
	/**
	 * Top Down Parser for <expr>
	 */
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
	

	/**
	 * Top Down Parser for <boolTerm>
	 */
	public static BoolTerm boolTerm() {
		LinkedList<BoolPrimaryItem> boolPrimaryList = new LinkedList<BoolPrimaryItem>();
		BoolPrimary primary = boolPrimary();
		boolPrimaryList.add(new SingleBoolPrimaryItem(primary));
		while (state == State.And) {
			getToken();
			primary =  boolPrimary();
			boolPrimaryList.add(new AndBoolPrimaryItem(primary));	
		
		}
		return new BoolTerm(boolPrimaryList);
	}
	

	/**
	 * Top Down Parser for <boolPrimary>
	 */
	public static BoolPrimary boolPrimary() {
		LinkedList<ETermItem> eList = new LinkedList<ETermItem>();
		EItem e1 = E();
		CompOp operation = null;
		EItem e2 = null;
		if (state == State.Ge || state == State.Gt || state == State.Le ||
				state == State.Lt || state == State.Eq || state == State.Neq) {
			State op = state;
			getToken();
			if(op == State.Ge) {
				operation = new Ge();
				e2 = E();
			} else if(op == State.Gt) {
				operation = new Gt();
				e2 = E();
			} else if(op == State.Le) {
				operation = new Le();
				e2 = E();
			} else if(op == State.Lt) {
				operation = new Lt();
				e2 = E();
			} else if (op == State.Eq) {
				operation = new Eq();
				e2 = E();
			} else {
				operation = new NotEq();
				e2 = E();
			}
		}
		return new BoolPrimary(e1,operation,e2);
	}
	

	/**
	 * Top Down Parser for <E>
	 */
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
	

	/**
	 * Top Down Parser for <term>
	 */
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
	

	/**
	 * Top Down Parser for <primary>
	 */
	public static Primary primary() {
		if (state == State.Id || state == State.Keyword_returnVal) {
			String id = t;
			getToken();
			if(state == State.LParen) {
				return new FuncCallPrimary(funcCall(id));
			}
			Var v = var(id);
			return new VarPrimary(v);
		} else if (state == State.Int) {
			Int i = new Int(Integer.parseInt(t));
			getToken();
			return i;
		} else if (state == State.Float) {
			FloatP f = new FloatP(Double.parseDouble(t));
			getToken();
			return f;
		} else if (state == State.FloatE) {
			FloatP f = new FloatP(Double.parseDouble(t));
			getToken();
			return f;
		} else if (state == State.LParen) {
			
			getToken();
			Expr expr = expr();
			if (state == State.RParen) {
				getToken();
				return new ExprPrimary(expr);
			} else {
				errorMsg(6);
				return null;
			}
		} else if (state == State.Sub) {
			getToken();
			Primary p = primary();
			return new NegPrimary(p);
		} else if (state == State.Inv) {
			getToken();
			Primary p = primary();
			return new NeqPrimary(p);
		} else { 
			errorMsg(2);
		}
		return null;
	}

	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, returnVal, int, float, floatE, expr, -, !, ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id expected"); return;	
		case 6:	displayln(" ) expected"); return;
		case 7:	displayln(" ( expected"); return;	
		case 8:	displayln(" { expected"); return;	
		case 9:	displayln(" } expected"); return;	
		case 10: displayln(" [ expected"); return;
		case 11: displayln(" ] expected"); return;	
		case 12: displayln(" id, (, if, while, {, print, returnVal expected"); return;	
		
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );

		getToken();

		FuncDefList functionDefinitionList = funcDefList();
		
		if ( ! t.isEmpty() )
			errorMsg(-1);
		else if ( ! errorFound )
			functionDefinitionList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

		closeIO();
	}
}