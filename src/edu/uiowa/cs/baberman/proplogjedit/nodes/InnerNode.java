
package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class InnerNode extends SelectableNode {
    
    private List<Node> subnodes = new ArrayList<Node>();

    public List<Node> getSubnodes() {
        return subnodes;
    }

    public void setSubnodes(List<Node> subnodes) {
        this.subnodes = subnodes;
    }

    @Override
    String getNonPlaceholderText() {
        String text = "";
        for (Node subnode : subnodes) {
            text += subnode.getText();
        }
        return text;
    }

    
}
