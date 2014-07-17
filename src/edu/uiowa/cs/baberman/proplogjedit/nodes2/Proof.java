
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
public class Proof extends LSLS<OneOrMore<SpacePropVar>, OneOrMore<ProofItem>>{

    public Proof() {
        super(new NonSelectableNode("Parameters"), 
                new OneOrMore<SpacePropVar>(new SpacePropVar()), 
                new NonSelectableNode(" : Prop.\n\n"), 
                new OneOrMore<ProofItem>(new ProofItem()));
        
    }

    @Override
    String getPlaceholderText() {
        return "PROOF";
    }
    
    

    
}
