
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofItem extends InnerNode {
    static String placeholderText = "SECTION | PROOF_LINE";

    static String getPlaceholderText() {
        return "SECTION | PROOF_LINE";
    }

	public ProofItem(boolean required) {
		super(required);
	}
    
}
