package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class Proof extends InnerNode {

    public Proof() {
        super();

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

}
