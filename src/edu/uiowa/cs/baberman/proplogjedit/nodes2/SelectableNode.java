
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
abstract class SelectableNode extends Node{
    private boolean isPlaceholder;
    
    public boolean isPlaceholder() {
        return isPlaceholder;
    }
    
    public void setIsPlaceholder(boolean isPlaceholder) {
        this.isPlaceholder = isPlaceholder;
    }
    
    protected SelectableNode() {
        this.isPlaceholder = true;
    }

    @Override
    public String getText() {
        if (isPlaceholder()) {
            return "(*" + getPlaceholderText() + "*)";
        } else {
            return getNonPlaceholderText();
        }
    }
    
    
    
    abstract String getNonPlaceholderText();
    abstract String getPlaceholderText();
    
    
    
}
