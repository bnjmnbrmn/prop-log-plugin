
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
class Section<N0 extends SectionID,
        N1 extends LineID, 
        N2 extends Formula, 
        N3 extends ZeroOrMore<ProofItem>, 
        N4 extends SectionID> extends LSLSLSLSLSL<N0,N1,N2,N2,N3,N4> {

    @Override
    String getPlaceholderText() {
        return "SECTION";
    }
    
}
