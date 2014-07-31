
package edu.uiowa.cs.baberman.proplogjedit.nodes;


import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class Node {
    private InnerNode parent;

    public abstract String getText();

    /**
     * @return the parent
     */
    public InnerNode getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(InnerNode parent) {
        this.parent = parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public int getOffset() {
        int offset;
        if (!hasParent()) {
            return 0;
        }
        InnerNode parent = getParent();
        offset = parent.getOffset();
        List<Node> subnodes = parent.getSubnodes();
        for (int i = 0; i < subnodes.size(); i++) {
            Node subnode = subnodes.get(i);
            if (subnodes.get(i) == this) {
                return offset;
            } else {
                offset += subnode.getText().length();
            }
        }
        //this should never occur
        throw new RuntimeException("Programming error:  selected child not among its parent's children");
    }
    
}
