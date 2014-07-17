
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class PropVar extends SelectableNode {

    private String nonPlaceholderText;
    
    @Override
    String getNonPlaceholderText() {
        return nonPlaceholderText;
    }

    @Override
    String getPlaceholderText() {
        return "PROP_VAR";
    }
    
}
