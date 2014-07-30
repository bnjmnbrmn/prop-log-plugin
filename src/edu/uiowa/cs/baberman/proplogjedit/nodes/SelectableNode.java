
package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.List;


/**
 *
 * @author bnjmnbrmn
 */
public abstract class SelectableNode extends Node {

	static String placeholderText = " ";
	
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

    /**
     * 
     * @return the List of SelectableNode siblings of the calling Selectable Node,
     * including the calling node
     */
    public List<SelectableNode> getSelectableSiblingsInclusive() {
        if (hasParent()) {
            return getParent().getSelectableSubnodes();
        } else {
            return null;
        }
    }
    
    public boolean isValid() {
        if (this instanceof RequiredInsertionPoint) {
            return false;
        } else if (this instanceof InnerNode) {
            for (SelectableNode selectableNode : ((InnerNode) this).getSelectableSubnodes()) {
                if (!selectableNode.isValid())
                    return false;
            }
        }
        return true;
    }

	public void respondToLetterPress(String letter) {
		//do nothing (in general)
	}

    
}
