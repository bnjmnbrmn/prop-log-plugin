package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMore<N extends SelectableNode> extends InnerNode {

    public OneOrMore(InnerNode parent) {
        super(parent);
    }

    public void addInitialSubnode(N initialSubnode) {
        getSubnodes().add(
                new OptionalInsertionPoint(this, initialSubnode.getClass()));
        getSubnodes().add(initialSubnode);
        getSubnodes().add(
                new OptionalInsertionPoint(this, initialSubnode.getClass()));
    }
    
    public void addInitialRequiredInsertionPoint(Class<N> clazz) {
        getSubnodes().add(new RequiredInsertionPoint(this, clazz));
    }

}
