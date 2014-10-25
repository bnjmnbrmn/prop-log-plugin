
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class SectionId extends Identifier {
    private SectionId matchingPair;

    public SectionId(boolean required) {
        super(required);
    }

    @Override
    public String getPlaceholderText() {
        return "SECTION_ID";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setMatchingPair(SectionId matchingPair) {
        this.matchingPair = matchingPair;
        if (matchingPair.getMatchingPair() != this)
            matchingPair.setMatchingPair(this);
    }

    private SectionId getMatchingPair() {
        return this.matchingPair;
    }

    @Override
    public void appendToIdentifierString(String toAppend) {
        super.appendToIdentifierString(toAppend);
        if (!getMatchingPair().getIdentifierString().equals(this.getIdentifierString()))
            getMatchingPair().setIdentifierString(this.getIdentifierString());
    }

    @Override
    public void setIdentifierString(String text) {
        super.setIdentifierString(text);
        if (!getMatchingPair().getIdentifierString().equals(this.getIdentifierString()))
            getMatchingPair().setIdentifierString(this.getIdentifierString());
    }
    
    
    
    
}
