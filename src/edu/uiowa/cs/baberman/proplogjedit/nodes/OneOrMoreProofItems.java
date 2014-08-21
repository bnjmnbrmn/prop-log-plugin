package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMoreProofItems extends OneOrMore<ProofItem> implements Indentable{
    private int indentationLevel;

    public OneOrMoreProofItems(boolean required, int indentationLevel) {
        super(required, new ProofItem(-1));
        this.indentationLevel = indentationLevel;
    }

    public OneOrMoreProofItems(int indentationLevel) {
        super(new ProofItem(-1));
        this.indentationLevel = indentationLevel;
        addSubnode(new OneOrMoreProofItems(false,indentationLevel));
        addSubnode(new ProofItem(true, indentationLevel));
        addSubnode(new OneOrMoreProofItems(false, indentationLevel));

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
            addSubnode(new OneOrMoreProofItems(false, indentationLevel));
            addSubnode(new ProofItem(true, indentationLevel));
            addSubnode(new OneOrMoreProofItems(false, indentationLevel));
            setMostRecentlySelectedSubnode((ProofItem) getSubnode(1));
            getProofModel().setSelectedNode((ProofItem) getSubnode(1));
        } else {
            ProofItem newProofItem = new ProofItem(true, indentationLevel);
            addSubnode(newProofItem);
            addSubnode(new OneOrMoreProofItems(false, indentationLevel));
            setMostRecentlySelectedSubnode(newProofItem);
            getProofModel().setSelectedNode(newProofItem);
        }
        getProofModel().getProofView().update();
    }

    @Override
    public int getIndentationLevel() {
        return indentationLevel;
    }

    @Override
    public void setIndentationLevel(int newIndentationLevel) {
        this.indentationLevel = indentationLevel;
    }

}
