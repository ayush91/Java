package Parse;
import ErrorMsg.ErrorMsg;

public class Yylex implements Yylex{
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_State = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferredReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private boolean yy_at_bol;
	private int yy_lexical_state;
	
	private void newline() 
	{ errorMsg.newline(yychar);}
	private void err(int pos,String s) 
	{ errorMsg.error(pos,s);}
	private void err(Strinf s)
	{ err(yychar,s);}
	private java_cup.runtime.Symbol tok(int kind,Object value)
	{ return new java_cup.runtime.Symbol(kind,yychar,yychar+yylength(), value);}
	private ErrorMsg errorMsg;
	Yylex(java.io.InputStream s, ErrorMsg e){
		this(s);
		errorMsg = e;
	}
	
