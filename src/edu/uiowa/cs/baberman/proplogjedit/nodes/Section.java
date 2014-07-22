
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public final class Section extends InnerNode {

    static String getPlaceholderText() {
        return "SECTION";
    }
    static String placeholderText = "SECTION";

    Section(InnerNode parent) {
        super(parent);
    }
    
}
