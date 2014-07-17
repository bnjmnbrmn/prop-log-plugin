package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class Justification extends
        OneOrTheOther<
        LS<LineID>, OneOrTheOther<
            LSS<LineID, LineID>, LSSS<LineID, LineID, LineID>>> {

    public Justification(LS<LineID> forPlaceholder1Text, OneOrTheOther<LSS<LineID, LineID>, LSSS<LineID, LineID, LineID>> forPlaceholder2Text) {
        super(forPlaceholder1Text, forPlaceholder2Text);
    }

}
