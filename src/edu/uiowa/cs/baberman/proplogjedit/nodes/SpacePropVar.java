
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class SpacePropVar extends InnerNode {

	public SpacePropVar(boolean required) {
		super(required);
	}
	
	@Override
    public String getPlaceholderText() {
        return " PROP_VAR";
    }
}
