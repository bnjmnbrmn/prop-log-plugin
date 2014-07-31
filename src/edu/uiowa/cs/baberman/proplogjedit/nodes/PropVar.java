
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class PropVar extends Identifier {
	
	public PropVar(boolean required) {
		super(required);
	}
	
    static String placeholderText = "PROP_VAR";

    static String getPlaceholderText() {
        return "PROP_VAR";
    }
    
}
