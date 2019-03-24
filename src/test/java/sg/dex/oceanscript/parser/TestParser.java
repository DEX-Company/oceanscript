package sg.dex.oceanscript.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sg.dex.oceanscript.Language;
import sg.dex.oceanscript.ast.ANode;
import sg.dex.oceanscript.ast.ConstantNode;

public class TestParser {

	@Test public void testConstant() {
		ANode<Object> node=Parser.read("13");
		assertTrue(node instanceof ConstantNode);
	}
	
	public static void main(String... args) {
		System.out.println(Language.execute("13"));
	}
}
