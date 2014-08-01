
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofLine extends InnerNode {
    
	public ProofLine(boolean required) {
		super(required);
	}
	
	@Override 
	public String getPlaceholderText() {
        return "PROOF_LINE";
    }

    @Override
    public ProofLine clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
