package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofItem extends InnerNode {

    public ProofItem(boolean required) {
        super(required);
    }
    
    ProofItem() {
        super();
    }

    @Override
    public String getPlaceholderText() {
        return "SECTION | PROOF_LINE";
    }

    @Override
    public ProofItem clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
