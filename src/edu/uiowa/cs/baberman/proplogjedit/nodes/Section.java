package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class Section extends InnerNode implements Indentable {
    private int indentationLevel;

    Section(boolean required, int indendationLevel) {
        super(required);
        this.indentationLevel = indendationLevel;
    }
    
    Section(int indentationLevel) {
        super();
        this.indentationLevel = indentationLevel;
    }

    @Override
    public String getPlaceholderText() {
        return "SECTION";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToRight() {
        //to do
    }

    @Override
    public int getIndentationLevel() {
        return indentationLevel;
    }

    @Override
    public void setIndentationLevel(int newIndentationLevel) {
        indentationLevel = newIndentationLevel;
    }

}
