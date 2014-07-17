
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
class SectionID extends SelectableNode {

    private String nonPlaceholderText;
    
    @Override
    String getNonPlaceholderText() {
        return nonPlaceholderText;
    }

    @Override
    String getPlaceholderText() {
        return "SECTION_ID";
    }
    
}
