package sg.dex.oceanscript;

import sg.dex.oceanscript.Context;
import sg.dex.oceanscript.ast.ANode;
import sg.dex.oceanscript.parser.Parser;
import sg.dex.starfish.exception.TODOException;

import com.oracle.truffle.api.TruffleLanguage;

public class Language extends TruffleLanguage<Context> {

	@Override
	protected Context createContext(Env env) {
		return Context.create(env);
	}

	@Override
	protected boolean isObjectOfLanguage(Object object) {
		// TODO:restrict here?
		return true;
	}

	public static Object execute(String src) {
		ANode<Object> node=Parser.read(src);
		return execute(node);
	}
	
	public static <T> T execute(ANode<T> node) {
		throw new TODOException();
	}

}
