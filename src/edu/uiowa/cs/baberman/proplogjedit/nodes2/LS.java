
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class LS<N extends SelectableNode> extends InnerNode{

    public LS(NonSelectableNode nsn, N sn) {
        getSubnodes().add(nsn);
        getSubnodes().add(sn);
    }
    
    @Override
    String getPlaceholderText() {
        return getSubnodes().get(0).getText() + 
                getSubnodes().get(1).getText();
    }
    
}
