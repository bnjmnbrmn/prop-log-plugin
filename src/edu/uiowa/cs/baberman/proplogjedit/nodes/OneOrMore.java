
package edu.uiowa.cs.baberman.proplogjedit.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrMore<N extends Node> extends InnerNode {

    List<N> ns;
    
    public OneOrMore(N n) {
        ns = new ArrayList<N>();
        ns.add(n);
    }

    String getPlaceholderText() {
        return "(" + ns.get(0).getText() + ")+";
    }
    
    
    
}
