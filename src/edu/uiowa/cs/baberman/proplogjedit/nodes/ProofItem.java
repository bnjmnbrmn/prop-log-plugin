package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofItem extends SlipperyNode {
    
    public ProofItem(boolean required) {
        super(required);
    }
    
    public ProofItem() {
        super();
    }

    @Override
    public String getPlaceholderText() {
        return "SECTION | PROOF_LINE";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    SelectableNode getSelectableChild() {
        return (SelectableNode) getSubnode(0);
    }

}
