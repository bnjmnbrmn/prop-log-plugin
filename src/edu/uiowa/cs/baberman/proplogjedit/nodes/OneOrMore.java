package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class OneOrMore<N extends SelectableNode> extends InnerNode {

    N itemPrototype;

    public OneOrMore(boolean required, N itemPrototype) {
        super(required);
        this.itemPrototype
                = itemPrototype;
    }
    
    public OneOrMore(N itemPrototype) {
        super();
        this.itemPrototype = itemPrototype;
    }
    
    public boolean hasMultipleNonSublistPlaceholderSubnodes() {
        List<N> itemsThatCount = new ArrayList<N>();
        for (Node n : getSubnodes()) {
            if (itemPrototype.getClass().isAssignableFrom(n.getClass())) {
                itemsThatCount.add((N)n);
            }
        }
        return itemsThatCount.size() > 1;
    }
    
    public void removeOptionalSublistPlaceholders() {
        List<Node> nodesToRemove = new ArrayList<Node>();
        for (Node n : getSubnodes()) {
            if (!itemPrototype.getClass().isAssignableFrom(n.getClass())) {
                nodesToRemove.add(n);
            }
        }
        for (Node nodeToRemove : nodesToRemove)
            removeSubnode(nodeToRemove);
    }

    public int getIndexAmongNonPlaceholdersOf(N n) {
        if (n.isPlaceholder()) {
            return -1;
        }
        int index = -1;
        for (int i = 0; i < getSubnodes().size(); i++) {
            Node ithSubnode = getSubnode(i);
            if (ithSubnode instanceof SelectableNode
                    && !((SelectableNode)getSubnode(i)).isPlaceholder()) {
                index++;
            }
            if (getSubnode(i) == n) {
                return index;
            }
        }
        return -1;
    }
    
    @Override
    public String getPlaceholderText() {
        return "(" + itemPrototype.getPlaceholderText() + ")+";
    }

}
