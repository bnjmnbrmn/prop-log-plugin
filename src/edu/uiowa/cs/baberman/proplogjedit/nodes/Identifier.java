
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class Identifier extends SelectableNode {
    String text = "";

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Identifier(InnerNode parent) {
        super(parent);
    }
    
}
