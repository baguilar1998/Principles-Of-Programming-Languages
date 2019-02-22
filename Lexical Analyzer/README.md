# Project 1: Lexical Analyzer
The object of this project was to create lexical analyzer (DFA) 
for the following BNF Grammar below that accepts all of the 26 token
categories. New token categories must be implemented as well to
accept the following keywords (if,else,while,print,returnVal,new). 
As input, the program takes in a file that contains a program of a 
mock programming lanaguage for the class and produces as output all 
the extracted tokens displayed line by line.

# BNF Grammar
⟨letter⟩ → a | b | ... | z | A | B | ... | Z 
⟨digit⟩ → 0 | 1 | ... | 9 
⟨id⟩ → ⟨letter⟩ {⟨letter⟩ | ⟨digit⟩} 
⟨int⟩ → {⟨digit⟩}+ 
⟨float⟩ → {⟨digit⟩}+ "." {⟨digit⟩}+ 
⟨floatE⟩ → ⟨float⟩ (e|E) [+|−] {⟨digit⟩}+ 
⟨add⟩ → + 
⟨sub⟩ → − 
⟨mul⟩ → * 
⟨div⟩ → / 
⟨or⟩ → "||" 
⟨and⟩ → "&&" 
⟨inv⟩ → ! 
⟨lt⟩ → "<" 
⟨le⟩ → "<=" 
⟨gt⟩ → ">" 
⟨ge⟩ → ">=" 
⟨eq⟩ → "==" 
⟨neq⟩ → "!=" 
⟨assign⟩ → = 
⟨LParen⟩ → "(" 
⟨RParen⟩ → ")" 
⟨LBrace⟩ → "{" 
⟨RBrace⟩ → "}" 
⟨LBracket⟩ → "[" 
⟨RBracket⟩ → "]" 
⟨semicolon⟩ → ";" 
⟨comma⟩ → "," 

⟨letter⟩ and ⟨digit⟩ are not token categories by themselves; rather, they are auxiliary categories to assist the definitions of the tokens ⟨id⟩, ⟨int⟩, ⟨float⟩, ⟨floatE⟩. 
