
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

    InnerNode(InnerNode parent) {
        super(parent);
    }

    protected void setSubnodeParentsToThis() {
        for (Node subnode : getSubnodes()) {
            subnode.setParent(this);
        }
    }

    public boolean hasSelectedChild() {
        for (SelectableNode selectableNode : getSelectableSubnodes()) {
            if (selectableNode.isSelectedChild()) {
                return true;
            }
        }
        return false;
    }

    //    List<Node> subnodes = new ArrayList<Node>();
    @Override
    public String getText() {
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
    //    abstract List<Node> getSubnodes();
    List<Node> subnodes = new ArrayList<Node>();

    public List<Node> getSubnodes() {
        return subnodes;
    }

    boolean hasAllRequiredSubnodes() {
        for (Node subnode : getSubnodes()) {
            if (subnode instanceof RequiredInsertionPoint) {
                return false;
            }
        }
        return true;
    }

    public boolean isComplete() {
        if (!hasAllRequiredSubnodes()) {
            return false;
        }
        for (Node subnode : getSubnodes()) {
            if (subnode instanceof InnerNode) {
                InnerNode subInnerNode = (InnerNode) subnode;
                if (!subInnerNode.isComplete()) {
                    return false;
                }
            }
        }
        return true;
    }
    
//    public RequiredInsertionPoint getFirstRequiredInsertionPointDescendant() {        
//        for (Node subnode : getSubnodes()) {
//            if (subnode instanceof RequiredInsertionPoint) {
//                return (RequiredInsertionPoint) subnode;
//            } else if (subnode instanceof InnerNode) {
//                RequiredInsertionPoint rip = 
//                        ((InnerNode)subnode).getFirstRequiredInsertionPointDescendant();
//                if (rip != null)
//                    return rip;
//            }
//        }
//        return null;
//    }
    
    public List<SelectableNode> getSelectableLeaves() {
        
        List<SelectableNode> selectableLeaves = new ArrayList<SelectableNode>();
        
        for (Node subnode : getSubnodes()) {
            if (subnode instanceof SelectableNode 
                    && !(subnode instanceof InnerNode)) {
                selectableLeaves.add((SelectableNode)subnode);
            } else if (subnode instanceof InnerNode) {
                InnerNode subInnerNode = (InnerNode) subnode;
                for (SelectableNode selectableLeaf : 
                        subInnerNode.getSelectableLeaves()) {
                    selectableLeaves.add(selectableLeaf);
                }
            }
        }
        
        return selectableLeaves;
    }
    
}
