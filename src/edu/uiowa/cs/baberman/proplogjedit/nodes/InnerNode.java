
package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.ArrayList;
import java.util.List;
import org.gjt.sp.jedit.Macros;
import org.gjt.sp.jedit.jEdit;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class InnerNode extends SelectableNode {
	
	InnerNode(boolean required) {
		super(required);
	}
	
	InnerNode() {
		super();
	}

    public boolean hasSelectedChild() {
        for (SelectableNode selectableNode : getSelectableSubnodes()) {
            if (selectableNode.isSelectedChild()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getText() {
		if (isARequiredPlaceholder()) {
			return getPlaceholderText();
		}
		
        String text = "";
        for (Node subnode : getSubnodes()) {
            text += subnode.getText();
        }
        return text;
    }

    public List<SelectableNode> getSelectableSubnodes() {
        List<SelectableNode> selectableSubnodes = new ArrayList<SelectableNode>();
        for (Node subnode : getSubnodes()) {
            if (subnode instanceof SelectableNode) {
                selectableSubnodes.add((SelectableNode) subnode);
            }
        }
        return selectableSubnodes;
    }
    
    private List<Node> subnodes = new ArrayList<Node>();

    List<Node> getSubnodes() {
        return subnodes;
    }
	
	protected void addSubnode(Node n) {
		subnodes.add(n);
		n.setParent(this);
	}
	
	public Node getSubnode(int index) {
		return subnodes.get(index);
	}
    
    public List<SelectableNode> getSelectableLeaves() {
        
        List<SelectableNode> selectableLeaves = new ArrayList<SelectableNode>();
		
		for (SelectableNode selectableSubnode : getSelectableSubnodes()) {
			if (selectableSubnode instanceof Identifier) {
				selectableLeaves.add(selectableSubnode);
			} else if (selectableSubnode instanceof InnerNode) {
				InnerNode innerSubnode = (InnerNode) selectableSubnode;
				if (innerSubnode.isARequiredPlaceholder() 
						|| innerSubnode.isAnOptionalPlaceholder()) {
					selectableLeaves.add(innerSubnode);
				} else {
					for (SelectableNode selectableLeaf : innerSubnode.getSelectableLeaves()) {
						selectableLeaves.add(selectableLeaf);
					}
				}
			}
		}
		
		

        return selectableLeaves;
    }
    
}
