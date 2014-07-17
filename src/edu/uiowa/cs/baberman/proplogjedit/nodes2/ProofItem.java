
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofItem extends OneOrTheOther<Section,ProofLine> {

    public ProofItem() {
        super(new Section(),
                new ProofLine());
    }

    @Override
    String getPlaceholderText() {
        return "PROOF_ITEM";
    }
    
}
