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

    public List<Node> getClonedSubnodes() {
        List<Node> clonedSubnodes = new ArrayList<Node>();
        for (Node subnode : getSubnodes()) {
            clonedSubnodes.add(subnode.clone());
        }
        return clonedSubnodes;
    }

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
        if (isARequiredPlaceholder()
                || (isAnOptionalPlaceholder() && isSelectedChild())) {
            
            return "(*" + getPlaceholderText() + "*)";
            
        } else if (isAnOptionalPlaceholder() && !isSelectedChild()) {
            
            return "";
            
        } else {
            
            String text = "";
            for (Node subnode : getSubnodes()) {
                text += subnode.getText();
            }
            return text;
            
        }
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
    
    protected void addSubnode(int index, Node n) {
        subnodes.add(index, n);
        n.setParent(this);
    }
    
    
    protected void removeSubnode(int i) {
        Node removedNode = subnodes.remove(i);
        removedNode.setParent(null);
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

    @Override
    public void appendString(String str) {
        //do nothing
    }

    
    
}
