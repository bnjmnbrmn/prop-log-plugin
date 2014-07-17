
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
class SpacePropVar extends LS<PropVar> {

    public SpacePropVar() {
        super(new NonSelectableNode(" "), new PropVar());
    }
    
}
