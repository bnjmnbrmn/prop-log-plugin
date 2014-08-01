package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMore<N extends SelectableNode> extends InnerNode {

    N itemPrototype;

    public OneOrMore(boolean required, N typePlaceholder) {
        super(required);
        this.itemPrototype
                = typePlaceholder;
    }

    @Override
    public String getPlaceholderText() {
        return "(" + itemPrototype.getPlaceholderText() + ")+";
    }

    @Override
    public SelectableNode clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
