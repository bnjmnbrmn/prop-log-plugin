package edu.uiowa.cs.baberman.proplogjedit.nodes;

import edu.uiowa.cs.baberman.kcm.KeyboardCard;
import edu.uiowa.cs.baberman.proplogjedit.ProofModel;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class SelectableNode extends Node {

    private boolean isPlaceholder;
    private boolean isOptional;
    
    SelectableNode(boolean required) {
        isOptional = !required;
        isPlaceholder = true;
    }

    SelectableNode() {
        isOptional = false;
        isPlaceholder = false;
    }

    public abstract String getPlaceholderText();

    public boolean isCompletedSubtreeRoot() {
        if (isARequiredPlaceholder()) {
            return false;
        } else if (isAnOptionalPlaceholder()) {
            return true;
        } else {
            if (this instanceof InnerNode) {
                for (SelectableNode subnode : ((InnerNode) this).getSelectableSubnodes()) {
                    if (!subnode.isCompletedSubtreeRoot()) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    

    public boolean isARequiredPlaceholder() {
        return isPlaceholder && !isOptional;
    }

    public boolean isAnOptionalPlaceholder() {
        return isPlaceholder && isOptional;
    }

    public boolean isPlaceholder() {
        return isPlaceholder;
    }

    public void setIsPlaceholder(boolean b) {
        this.isPlaceholder = b;
        if (parent != null) {
            DescendantChangeEvent changeEvent = b ? new DescendantChangeEvent.ToPlaceholder(this) : new DescendantChangeEvent.ToNonPlaceholder(this);
            parent.descendantChanged(changeEvent);
        }
    }

//    private boolean selected = false;
//    public void setAsSelectedChild(boolean selected) {
//        this.selected = selected;
//    }
    public boolean isSelectedChild() {
        return getProofModel().getSelectedNode() == this;
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
     * @return the List of SelectableNode siblings of the calling Selectable
     * Node, including the calling node
     */
    public List<SelectableNode> getSelectableSiblingsInclusive() {
        if (hasParent()) {
            return getParent().getSelectableSubnodes();
        } else {
            return null;
        }
    }

    public void descendantChanged(DescendantChangeEvent e) {
        if (getParent() != null) {
            getParent().descendantChanged(e);
        }
    }
    
}
