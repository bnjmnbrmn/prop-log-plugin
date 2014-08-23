
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
class Justification extends SlipperyNode {

    public Justification(boolean required) {
        super(required);
    }

    @Override
    public String getPlaceholderText() {
        return "JUSTIFICATION";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SelectableNode getSelectableChild() {
        return (SelectableNode) getSubnode(0);
    }
    
}
