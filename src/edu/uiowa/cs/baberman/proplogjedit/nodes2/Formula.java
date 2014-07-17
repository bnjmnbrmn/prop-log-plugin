
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
public class Formula extends OneOrTheOther<PropVar,
        OneOrTheOther<LSSL<UnaryOp,Formula>,
                        LSSSL<Formula,InfixBinaryOp,Formula>>> {

    public Formula(PropVar forPlaceholder1Text, OneOrTheOther<LSSL<UnaryOp, Formula>, LSSSL<Formula, InfixBinaryOp, Formula>> forPlaceholder2Text) {
        super(forPlaceholder1Text, forPlaceholder2Text);
    }

    
    
    
}
