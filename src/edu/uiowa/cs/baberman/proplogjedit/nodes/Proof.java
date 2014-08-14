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
        addSubnode(new OneOrMoreSpacePropVars());
        addSubnode(new Terminal(" : Prop.\n\n"));
        addSubnode(new OneOrMoreProofItems());
    }

    @Override
    public String getPlaceholderText() {
        return "PROOF";
    }

    @Override
    public ProofModel getProofModel() {
        return proofModel;
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
