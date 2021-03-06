package edu.uiowa.cs.baberman.proplogjedit.nodes;

import edu.uiowa.cs.baberman.proplogjedit.ProofModel;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class Node {

    protected InnerNode parent;
    protected boolean isPlaceholder;

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
    
    public InnerNode getParent() {
        return parent;
    }
    
    public void setParent(InnerNode parent) {
        this.parent = parent;
    }

    public ProofModel getProofModel() {
        return getParent().getProofModel();
    }
    
    public boolean hasParent() {
        return parent != null;
    }

    public abstract String getText();
    public abstract SelectableNode deepCopy();

}
