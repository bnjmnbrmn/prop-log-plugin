
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class LSLS <N0 extends SelectableNode, 
        N1 extends SelectableNode> extends InnerNode {
    
    protected LSLS(NonSelectableNode l1, N0 n1, NonSelectableNode l2, N1 n2) {
        getSubnodes().add(l1);
        getSubnodes().add(n1);
        getSubnodes().add(l2);
        getSubnodes().add(n2);
    }
    
    

}
