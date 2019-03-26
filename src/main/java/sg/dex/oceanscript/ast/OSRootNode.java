package sg.dex.oceanscript.ast;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

import sg.dex.oceanscript.Language;

public class OSRootNode extends RootNode {

	@Child private final ANode<?> body;
	
	protected OSRootNode(TruffleLanguage<?> language, ANode<?> body) {
		super(language);
		this.body=body;
	}
	
	public static <T> OSRootNode create(ANode<T> node) {
		return new OSRootNode(Language.INSTANCE,node);
	}
	
	@Override
    public Object execute(VirtualFrame virtualFrame) {
        return body.execute(virtualFrame);
    }



}
