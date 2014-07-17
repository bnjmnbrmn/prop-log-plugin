
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
class ProofItem extends OneOrTheOther<Section,ProofLine> {

    public ProofItem() {
        super(new Section(),
                new ProofLine());
    }
    
}
