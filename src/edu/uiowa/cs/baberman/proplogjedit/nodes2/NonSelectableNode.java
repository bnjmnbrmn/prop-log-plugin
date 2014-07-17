
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
class NonSelectableNode extends Node {

    private String text;
    
    NonSelectableNode(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
    
}
