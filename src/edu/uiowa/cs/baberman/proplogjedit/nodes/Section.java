
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class Section extends InnerNode {

	Section(boolean required) {
		super(required);
	}
	
	@Override
    public String getPlaceholderText() {
        return "SECTION";
    }
    
    
}
