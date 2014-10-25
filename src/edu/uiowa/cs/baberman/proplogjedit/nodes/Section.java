package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class Section extends InnerNode implements Indentable {
    private int indentationLevel;
    private SectionId topSectionId;

    Section(boolean required, int indendationLevel) {
        super(required);
        this.indentationLevel = indendationLevel;
    }
    
    Section(int indentationLevel) {
        super();
        this.indentationLevel = indentationLevel;
        
        addSubnode(new Terminal("   ", indentationLevel));
        addSubnode(new Terminal("Section "));
        this.topSectionId = new SectionId(true);
        addSubnode(this.topSectionId);
        addSubnode(new Terminal("\n"));
        //to do
        
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
    
    public SectionId getTopSectionId() {
        return topSectionId;
    }

}
