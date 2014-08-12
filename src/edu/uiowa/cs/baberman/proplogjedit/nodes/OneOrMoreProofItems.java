
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMoreProofItems extends OneOrMore<ProofItem>{

    public OneOrMoreProofItems(boolean required) {
        super(required, new ProofItem());
    }
    
    public OneOrMoreProofItems() {
        super(new ProofItem());
//        addSubnode(new OneOrMoreProofItems(false));
        addSubnode(new ProofItem(true));
//        addSubnode(new OneOrMoreProofItems(false));
    }
}
