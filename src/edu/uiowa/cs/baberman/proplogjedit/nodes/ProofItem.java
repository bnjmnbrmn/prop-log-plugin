package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofItem extends SlipperyNode implements Indentable {
    private int indentationLevel;
    
    public ProofItem(boolean required, int indentationLevel) {
        super(required);
        this.indentationLevel = indentationLevel;
    }
    
    public ProofItem(int indentationLevel) {
        super();
        this.indentationLevel = indentationLevel;
    }

    @Override
    public String getPlaceholderText() {
        return "SECTION | PROOF_LINE";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SelectableNode getSelectableChild() {
        return (SelectableNode) getSubnode(0);
    }

    @Override
    public void addToRight() {
        if (hasParent() 
                && getParent().hasParent() 
                && getParent().getParent() instanceof OneOrMoreProofItems) {
            getParent().getParent().addToRight();
        } else if (hasParent()
                && getParent() instanceof OneOrMoreProofItems) {
            getParent().addToRight();
        }
        
    }

    public void setToProofLine() {
        removeAllSubnodes();
        setIsPlaceholder(false);
        
        ProofLine pl = new ProofLine(indentationLevel);
        addSubnode(pl);
        getProofModel().setSelectedNode(pl.getLineId());
        getProofModel().getProofView().update();
    }
    
    public void setToSection() {
        removeAllSubnodes();
        setIsPlaceholder(false);
        
        Section s = new Section(indentationLevel);
        addSubnode(s);
        getProofModel().setSelectedNode(s.getTopSectionId());
        getProofModel().getProofView().update();
    }

    @Override
    public int getIndentationLevel() {
       return indentationLevel;
    }

    @Override
    public void setIndentationLevel(int newIndentationLevel) {
        this.indentationLevel = newIndentationLevel;
    }


}
