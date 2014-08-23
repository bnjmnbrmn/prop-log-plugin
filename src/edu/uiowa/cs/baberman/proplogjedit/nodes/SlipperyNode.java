
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 * Marker class for InnerNodes with a max of only one child.
 * 
 * This has significance for tree traversal, since generally there is no point 
 * in navigating to one of these nodes.
 * 
 * 
 * @author bnjmnbrmn
 */
public abstract class SlipperyNode extends InnerNode {
    public SlipperyNode(boolean required) {
        super(required);
    }
    
    public SlipperyNode() {
        super();
    }

    public abstract SelectableNode getSelectableChild();
}
