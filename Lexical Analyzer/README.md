# Project 1: Lexical Analyzer
The object of this project was to create lexical analyzer (DFA) 
for the following BNF Grammar below that accepts all of the 26 token
categories. New token categories must be implemented as well to
accept the following keywords (if,else,while,print,returnVal,new). 
As input, the program takes in a file that contains a program of a 
mock programming lanaguage for the class and produces as output all 
the extracted tokens displayed line by line.

# BNF Grammar
⟨letter⟩ → a | b | ... | z | A | B | ... | Z  <br />
⟨digit⟩ → 0 | 1 | ... | 9 <br />
⟨id⟩ → ⟨letter⟩ {⟨letter⟩ | ⟨digit⟩} <br /> 
⟨int⟩ → {⟨digit⟩}+  <br />
⟨float⟩ → {⟨digit⟩}+ "." {⟨digit⟩}+ <br /> 
⟨floatE⟩ → ⟨float⟩ (e|E) [+|−] {⟨digit⟩}+ <br />
⟨add⟩ → + <br />
⟨sub⟩ → − <br />
⟨mul⟩ → * <br />
⟨div⟩ → / <br />
⟨or⟩ → "||" <br />
⟨and⟩ → "&&" <br />
⟨inv⟩ → ! <br />
⟨lt⟩ → "<" <br />
⟨le⟩ → "<=" <br />
⟨gt⟩ → ">" <br />
⟨ge⟩ → ">=" <br />
⟨eq⟩ → "==" <br />
⟨neq⟩ → "!=" <br />
⟨assign⟩ → = <br />
⟨LParen⟩ → "(" <br />
⟨RParen⟩ → ")" <br />
⟨LBrace⟩ → "{" <br />
⟨RBrace⟩ → "}" <br />
⟨LBracket⟩ → "[" <br />
⟨RBracket⟩ → "]" <br />
⟨semicolon⟩ → ";" <br />
⟨comma⟩ → "," 

⟨letter⟩ and ⟨digit⟩ are not token categories by themselves; rather, they are auxiliary categories to assist the definitions of the tokens ⟨id⟩, ⟨int⟩, ⟨float⟩, ⟨floatE⟩. 
