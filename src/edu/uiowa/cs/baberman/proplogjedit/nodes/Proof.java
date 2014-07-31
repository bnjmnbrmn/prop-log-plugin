
package edu.uiowa.cs.baberman.proplogjedit.nodes;



/**
 *
 * @author bnjmnbrmn
 */
public final class Proof extends InnerNode {
    static String placeholderText = "PROOF";

    public Proof() {
        addSubnode(new Terminal("Parameters"));
        
        OneOrMore<SpacePropVar> spacePropVars = new OneOrMore<SpacePropVar>();
		spacePropVars.addSubnode(new SpacePropVar(false));
        addSubnode(spacePropVars);
        
        addSubnode(new Terminal(" : Prop.\n\n"));
        
        OneOrMore<ProofItem> proofItems = new OneOrMore<ProofItem>();
		proofItems.addSubnode(new ProofItem(true));
        addSubnode(proofItems);
    }
    
}
