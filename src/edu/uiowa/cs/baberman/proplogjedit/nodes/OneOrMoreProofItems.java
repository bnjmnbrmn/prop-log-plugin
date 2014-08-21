package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMoreProofItems extends OneOrMore<ProofItem> {

    public OneOrMoreProofItems(boolean required) {
        super(required, new ProofItem());
    }

    public OneOrMoreProofItems() {
        super(new ProofItem());
        addSubnode(new OneOrMoreProofItems(false));
        addSubnode(new ProofItem(true));
        addSubnode(new OneOrMoreProofItems(false));

        setMostRecentlySelectedSubnode((ProofItem) getSubnode(1));
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToRight() {
        if (hasParent() && getParent() instanceof OneOrMoreProofItems) {
            getParent().addToRight();
        } else if (getSubnodes().isEmpty()) {
            addSubnode(new OneOrMoreProofItems(false));
            addSubnode(new ProofItem(true));
            addSubnode(new OneOrMoreProofItems(false));
            setMostRecentlySelectedSubnode((ProofItem) getSubnode(1));
            getProofModel().setSelectedNode((ProofItem) getSubnode(1));
        } else {
            ProofItem newProofItem = new ProofItem(true);
            addSubnode(newProofItem);
            addSubnode(new OneOrMoreProofItems(false));
            setMostRecentlySelectedSubnode(newProofItem);
            getProofModel().setSelectedNode(newProofItem);
        }
        getProofModel().getProofView().update();
    }
}
