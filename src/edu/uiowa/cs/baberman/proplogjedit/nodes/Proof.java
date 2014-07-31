
package edu.uiowa.cs.baberman.proplogjedit.nodes;



/**
 *
 * @author bnjmnbrmn
 */
public final class Proof extends InnerNode {
    public Proof() {
		super();
		
        addSubnode(new Terminal("Parameters"));
        
        OneOrMore<SpacePropVar> spacePropVars 
				= new OneOrMore<SpacePropVar>(true, new SpacePropVar(false));
	    addSubnode(spacePropVars);
        
        addSubnode(new Terminal(" : Prop.\n\n"));
        
        OneOrMore<ProofItem> proofItems = new OneOrMore<ProofItem>(true, new ProofItem(false));
	    addSubnode(proofItems);
    }

	@Override
	public String getPlaceholderText() {
		return "PROOF";
	}
    
}
