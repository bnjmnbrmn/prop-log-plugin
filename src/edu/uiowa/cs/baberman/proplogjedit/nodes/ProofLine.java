
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofLine extends InnerNode {
    static String placeholderText = "PROOF_LINE";

    static String getPlaceholderText() {
        return "PROOF_LINE";
    }

	public ProofLine(boolean required) {
		super(required);
	}
}
