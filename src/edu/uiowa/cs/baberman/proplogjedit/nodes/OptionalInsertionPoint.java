
package edu.uiowa.cs.baberman.proplogjedit.nodes;


/**
 *
 * @author bnjmnbrmn
 */
public class OptionalInsertionPoint extends InsertionPoint {

    public OptionalInsertionPoint(InnerNode parent, Class<? extends SelectableNode> clazz) {
        super(parent, clazz);
    }

    @Override
    public String getText() {
        if (isSelectedChild()) {
            return super.getText();
        } else {
            return "";
        }
    }
    
}
