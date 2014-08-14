
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class DescendantChangeEvent {
    
    private final Node changedNode;
    
    private DescendantChangeEvent(Node changedNode) {
        this.changedNode = changedNode;
    }

    /**
     * @return the changedNode
     */
    public Node getChangedNode() {
        return changedNode;
    }
    
    public static class ToPlaceholder extends DescendantChangeEvent {

        public ToPlaceholder(Node changedNode) {
            super(changedNode);
        }
        
    }
    
    public static class ToNonPlaceholder extends DescendantChangeEvent {

        public ToNonPlaceholder(Node changedNode) {
            super(changedNode);
        }
        
    }
    
}
