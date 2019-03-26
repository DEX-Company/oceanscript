package sg.dex.oceanscript.ast;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Base class for OceanScript AST nodes
 * 
 * @author Mike
 * @param T The type of values produced by this Node
 */
public abstract class ANode<T> {

    public abstract T execute(VirtualFrame virtualFrame);

}
