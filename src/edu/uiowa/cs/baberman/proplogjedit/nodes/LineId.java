package edu.uiowa.cs.baberman.proplogjedit.nodes;

import edu.uiowa.cs.baberman.proplogjedit.PropLogPlugin;

/**
 *
 * @author bnjmnbrmn
 */
public class LineId extends Identifier {

    public LineId(boolean required) {
        super(required);
    }

    @Override
    public String getPlaceholderText() {
        return "LINE_ID";
    }

    @Override
    public void setTo() {
        PropLogPlugin.getInstance().getPropLogKCMS().setCurrentRoot(
                getProofModel().getLineIdKCMRoot());
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
