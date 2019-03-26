package sg.dex.oceanscript;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;

import sg.dex.oceanscript.ast.ANode;
import sg.dex.oceanscript.ast.OSRootNode;
import sg.dex.oceanscript.parser.Parser;

public class Language extends TruffleLanguage<Context> {

	public static final TruffleLanguage<?> INSTANCE = new Language();

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
	
	@SuppressWarnings("unchecked")
	public static <T> T execute(ANode<T> node) {
		return (T) execute(OSRootNode.create(node));
	}
	
	public static Object execute(OSRootNode node) {
		RootCallTarget target= Truffle.getRuntime().createCallTarget(node);
		return target.call();
	}

}
