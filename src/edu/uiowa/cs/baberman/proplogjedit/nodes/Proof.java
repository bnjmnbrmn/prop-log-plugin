
package edu.uiowa.cs.baberman.proplogjedit.nodes;



/**
 *
 * @author bnjmnbrmn
 */
public final class Proof extends InnerNode {
    static String placeholderText = "PROOF";

    public Proof() {
        super(null);
        getSubnodes().add(new Terminal(this, "Parameters"));
        
        OneOrMore<SpacePropVar> spacePropVars = new OneOrMore<SpacePropVar>(this);
//        spacePropVars.addInitialSubnode(new SpacePropVar(spacePropVars));
        getSubnodes().add(spacePropVars);
        
//        getSubnodes().add(new SpacePropVar(this));
//        getSubnodes().add(new RequiredInsertionPoint(this, SpacePropVar.class));
        getSubnodes().add(new Terminal(this, " : Prop.\n\n"));
//        getSubnodes().add(new RequiredInsertionPoint(this, ProofItem.class));
        
        OneOrMore<ProofItem> proofItems = new OneOrMore<ProofItem>(this);
//        proofItems.addInitialRequiredInsertionPoint(ProofItem.class);
        getSubnodes().add(proofItems);
    }
    
}
