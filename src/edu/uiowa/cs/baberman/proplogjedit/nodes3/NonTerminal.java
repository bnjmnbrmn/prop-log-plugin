
package edu.uiowa.cs.baberman.proplogjedit.nodes3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class NonTerminal extends Node {
    private boolean isPlaceholder;
    private boolean isOptional = false;
    private boolean isSelected = false;
    
    List<Node> subnodes = new ArrayList<Node>(); 
    
    public abstract String getPlaceholderText();
    

}
