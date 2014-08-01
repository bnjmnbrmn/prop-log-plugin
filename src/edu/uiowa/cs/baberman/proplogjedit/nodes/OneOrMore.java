package edu.uiowa.cs.baberman.proplogjedit.nodes;

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
    
    

    @Override
    public String getPlaceholderText() {
        return "(" + itemPrototype.getPlaceholderText() + ")+";
    }

    @Override
    public SelectableNode clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
