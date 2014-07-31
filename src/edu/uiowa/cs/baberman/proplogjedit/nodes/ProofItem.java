
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofItem extends InnerNode {
    
	public ProofItem(boolean required) {
		super(required);
	}
	
	@Override 
	public String getPlaceholderText() {
        return "SECTION | PROOF_LINE";
    }
    
}
