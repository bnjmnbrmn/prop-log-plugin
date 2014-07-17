
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class Formula extends OneOrTheOther<PropVar,
        OneOrTheOther<LSSL<UnaryOp,Formula>,
                        LSSSL<Formula,InfixBinaryOp,Formula>>> {
    
}
