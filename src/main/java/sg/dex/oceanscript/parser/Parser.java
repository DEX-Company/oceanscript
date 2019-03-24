package sg.dex.oceanscript.parser;

import java.io.IOException;

import org.parboiled.BaseParser;
import org.parboiled.Parboiled;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.buffers.InputBuffer;
import org.parboiled.errors.ParseError;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;

import sg.dex.oceanscript.ast.ANode;
import sg.dex.oceanscript.ast.ConstantNode;


@BuildParseTree
public class Parser extends BaseParser<ANode<?>> {

	private static Parser parser = Parboiled.createParser(Parser.class);
	
	// ====================================================================
	// Characters, digits etc.
	
    public Rule Digit() {
        return CharRange('0', '9');
    }

    public Rule WhiteSpace() {
        return ZeroOrMore(AnyOf(" \t\f"));
    }
    
	public Rule Digits() {
        return OneOrMore(Digit());
    }
	
   
	public Rule SignedInteger() {
        return Sequence(
        		 Optional(AnyOf("+-")),
				 Digits());
    }
	
	public Rule Long() {
        return Sequence(
        		SignedInteger(),
        		push(ConstantNode.create(Long.parseLong(matchOrDefault("0")))));
    }
 
    
	public Rule NumberLiteral() {
		return FirstOf(
				Long(),
				Long());
	}
	
	public Rule Constant() {
		return FirstOf(
				NumberLiteral(),
				NumberLiteral()
				);
	}
    
	public Rule Expression() {
		return FirstOf(
					Constant(),
					Constant()
				);
	}
	
	public Rule ExpressionInput() {
		return Sequence(
				Optional(WhiteSpace()),
				Expression(),
				Optional(WhiteSpace()),
				EOI
				);
	}
	
	// ==================================================================
	// Static utility functions
	
	private static <T> void checkErrors(ParsingResult<T> result) {
		if (result.hasErrors()) {
			java.util.List<ParseError> errors=result.parseErrors;
			StringBuilder sb=new StringBuilder();
			for (ParseError error: errors) {
				InputBuffer ib=error.getInputBuffer();
				int start=error.getStartIndex();
				int end=error.getEndIndex();
				sb.append("Parse error at "+ib.getPosition(error.getStartIndex())+": "+ib.extract(start, end)+" ERR: "+ error.getErrorMessage()+ "IB:"+ib.extractLine(1));
			}
			throw new Error(sb.toString());
		}
		
	}
	

	/**
	 * Parses an expression and returns a form
	 * @param string
	 * @return
	 */
	public static <T> ANode<T> read(String source) {
		ParsingResult<ANode<T>> result = new ReportingParseRunner<ANode<T>>(parser.ExpressionInput()).run(source);
		checkErrors(result);
		return result.resultValue;
	}
	
	public static <T> ANode<T> read(java.io.Reader source) throws IOException {
	    char[] arr = new char[8 * 1024];
	    StringBuilder buffer = new StringBuilder();
	    int numCharsRead;
	    while ((numCharsRead = source.read(arr, 0, arr.length)) != -1) {
	        buffer.append(arr, 0, numCharsRead);
	    }
	    return read(buffer.toString());
	}
}
