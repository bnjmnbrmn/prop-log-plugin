package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMore<N extends SelectableNode> extends InnerNode {
	
	public OneOrMore() {
		super();
	}
	
	public OneOrMore(boolean required) {
		super(required);
	}

	public void addSubnode(N subnode) {
		super.addSubnode(subnode);
	}
}
