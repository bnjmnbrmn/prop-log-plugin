package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class SelectableNode extends Node {
	
	public abstract String getPlaceholderText();
	
	SelectableNode(boolean required) {
		if (required) {
			placeholderStatus = PlaceholderStatus.REQUIRED_PLACEHOLDER;
		} else {
			placeholderStatus = PlaceholderStatus.OPTIONAL_PLACEHOLDER;
		}
	}
	
	SelectableNode() {
		placeholderStatus = PlaceholderStatus.NONPLACEHOLDER;
	}

    public boolean isCompletedSubtreeRoot() {
        if (isARequiredPlaceholder()) {
            return false;
        } else if (isAnOptionalPlaceholder()) {
            return true;
        } else {
            if (this instanceof InnerNode) {
                for (SelectableNode subnode : ((InnerNode) this).getSelectableSubnodes()) {
                    if (!subnode.isCompletedSubtreeRoot())
                        return false;
                }
            }
            return true;
        }
    }

    public void respondToLetterPress(String letter) {
		//for most types of selectable nodes we do nothing
	}
	

    public enum PlaceholderStatus {
        REQUIRED_PLACEHOLDER, OPTIONAL_PLACEHOLDER, NONPLACEHOLDER;
    }
	
	public void setPlaceholderStatus(PlaceholderStatus status) {
		placeholderStatus = status;
	}

    private PlaceholderStatus placeholderStatus;

    public boolean isARequiredPlaceholder() {
        return placeholderStatus == PlaceholderStatus.REQUIRED_PLACEHOLDER;
    }

    public boolean isAnOptionalPlaceholder() {
        return placeholderStatus == PlaceholderStatus.OPTIONAL_PLACEHOLDER;
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

}
