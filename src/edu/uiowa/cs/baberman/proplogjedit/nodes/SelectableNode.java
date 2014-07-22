
package edu.uiowa.cs.baberman.proplogjedit.nodes;


/**
 *
 * @author bnjmnbrmn
 */
public abstract class SelectableNode extends Node {

    public SelectableNode(InnerNode parent) {
        super(parent);
    }
    private boolean selected = false;

    public void setAsSelectedChild(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelectedChild() {
        return selected;
    }

    public boolean isSelectedChildSibling() {
        if (getParent() == null) {
            return false;
        }
        if (isSelectedChild()) {
            return false;
        }
        for (SelectableNode selectableNode : getParent().getSelectableSubnodes()) {
            if (selectableNode.isSelectedChild()) {
                return true;
            }
        }
        return false;
    }
    
}
