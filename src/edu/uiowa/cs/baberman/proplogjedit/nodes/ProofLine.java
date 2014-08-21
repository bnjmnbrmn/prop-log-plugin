package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class ProofLine extends InnerNode implements Indentable {
    private int indentationLevel;

    public ProofLine(boolean required, int indendationLevel) {
        super(required);
        this.indentationLevel = indendationLevel;
    }
    
    public ProofLine(int indentationLevel) {
        super();
        this.indentationLevel = indentationLevel;
        
        addSubnode(new Terminal("   ", indentationLevel));
        addSubnode(new Terminal("Fact "));
        addSubnode(new LineId(true));
        addSubnode(new Terminal(" : "));
        addSubnode(new Formula(true));
    }

    @Override
    public String getPlaceholderText() {
        return "PROOF_LINE";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToRight() {
        //do nothing
    }

    @Override
    public int getIndentationLevel() {
        return indentationLevel;
    }

    @Override
    public void setIndentationLevel(int newIndentationLevel) {
        this.indentationLevel = newIndentationLevel;
    }
    
    public Formula getFormula() { 
        return (Formula) getSubnode(4);
    }

}
