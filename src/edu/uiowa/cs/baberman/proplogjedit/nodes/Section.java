package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class Section extends InnerNode {

    Section(boolean required) {
        super(required);
    }
    
    Section() {
        super();
    }

    @Override
    public String getPlaceholderText() {
        return "SECTION";
    }

    @Override
    public SelectableNode clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
