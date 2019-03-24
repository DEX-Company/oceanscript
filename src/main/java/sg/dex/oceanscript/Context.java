package sg.dex.oceanscript;

import com.oracle.truffle.api.TruffleLanguage.Env;

public class Context {

	public static Context create(Env env) {
		// TODO: probably specialise based on env?
		return new Context();
	}

}
