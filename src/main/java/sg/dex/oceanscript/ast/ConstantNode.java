package sg.dex.oceanscript.ast;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * AST node representing a constant
 * 
 * @author Mike
 *
 * @param <T>
 */
public class ConstantNode<T> extends ANode<T> {

	private T value;

	public ConstantNode(T value) {
		this.value=value;
	}

	public static <T> ANode<T> create(T value) {
		return new ConstantNode<T>(value);
	}

	@Override
	public T execute(VirtualFrame virtualFrame) {
		return value;
	}

}
