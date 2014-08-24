package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.ArrayList;
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
        if (parent != null && this.isPlaceholder != b) {
            this.isPlaceholder = b;
            DescendantChangeEvent changeEvent = b ? new DescendantChangeEvent.ToPlaceholder(this) : new DescendantChangeEvent.ToNonPlaceholder(this);
            parent.descendantChanged(changeEvent);
        }
    }

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
    
    public List<SelectableNode> getPeerBranchSelectionListInclusive() {
        List<SelectableNode> branchSelectionList
                = new ArrayList<SelectableNode>();
        if (hasParent()) {
            InnerNode ancestor;
            ancestor = getParent();
            while (ancestor instanceof SlipperyNode) {
                ancestor = ancestor.getParent();
            }
            branchSelectionList = ancestor.getDescendantBranchSelectionList();
        } else {
            branchSelectionList.add(this);
        }
        return branchSelectionList;
    }

    public void descendantChanged(DescendantChangeEvent e) {
        if (getParent() != null) {
            getParent().descendantChanged(e);
        }
    }

    public void addToRight() {
        //do nothing
    }

    public void setTo() {
        //do nothing
    }
    
}
