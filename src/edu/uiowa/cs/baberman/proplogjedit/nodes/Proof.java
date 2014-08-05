package edu.uiowa.cs.baberman.proplogjedit.nodes;

import edu.uiowa.cs.baberman.proplogjedit.ProofModel;

/**
 *
 * @author bnjmnbrmn
 */
public final class Proof extends InnerNode {
    private final ProofModel proofModel;

    public Proof(ProofModel proofModel) {
        super();
        
        this.proofModel = proofModel;

        addSubnode(new Terminal("Parameters"));

        OneOrMoreSpacePropVars spacePropVars
                = new OneOrMoreSpacePropVars();
        addSubnode(spacePropVars);

        addSubnode(new Terminal(" : Prop.\n\n"));

        OneOrMoreProofItems proofItems = new OneOrMoreProofItems();
        addSubnode(proofItems);
    }

    @Override
    public String getPlaceholderText() {
        return "PROOF";
    }

    @Override
    public Proof clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProofModel getProofModel() {
        return proofModel;
    }

}
