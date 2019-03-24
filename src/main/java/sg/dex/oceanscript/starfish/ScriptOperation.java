package sg.dex.oceanscript.starfish;

import java.util.Map;

import sg.dex.starfish.Asset;
import sg.dex.starfish.exception.TODOException;
import sg.dex.starfish.impl.memory.AMemoryOperation;

public class ScriptOperation extends AMemoryOperation {

	protected ScriptOperation(String meta) {
		super(meta);
	}

	@Override
	public Asset compute(Map<String, Asset> params) {
		throw new TODOException();
	}

}
