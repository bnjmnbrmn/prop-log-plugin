package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMore<N extends SelectableNode> extends InnerNode {
	
	N typePlaceholder;
	
//	public OneOrMore(N typePlaceholder) {
//		super();
//		this.typePlaceholder
//				= typePlaceholder;
//	}
	
	public OneOrMore(boolean required, N typePlaceholder) {
		super(required);
		this.typePlaceholder
				= typePlaceholder;
	}

	@Override
	public String getPlaceholderText() {
		return "(" + typePlaceholder.getPlaceholderText() + ")+";
	}
}
