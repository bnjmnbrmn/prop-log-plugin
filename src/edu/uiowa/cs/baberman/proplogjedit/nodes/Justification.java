
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class Justification extends InnerNode {

    public Justification(boolean required) {
        super(required);
    }

    @Override
    public String getPlaceholderText() {
        return "JUSTIFICATION";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


	public void setToAndIntro() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("and_intro "));
		addSubnode(new LineId(true));
		addSubnode(new Terminal(" "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToAndElimR() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("and_elim_r "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToAndElimL() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("and_elim_l "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToOrIntroR() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("or_intro_r "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToOrIntroL() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("or_intro_l "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToOrElim() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("or_elim "));
		addSubnode(new LineId(true));
		addSubnode(new Terminal(" "));
		addSubnode(new LineId(true));
		addSubnode(new Terminal(" "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToImplElim() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("impl_elim "));
		addSubnode(new LineId(true));
		addSubnode(new Terminal(" "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToFalseElim() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("false_elim "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}

	public void setToNotNotElim() {
		removeAllSubnodes();
		setIsPlaceholder(false);
		
		addSubnode(new Terminal("not_not_elim "));
		addSubnode(new LineId(true));
		
		getProofModel().setSelectedNode((LineId) getSubnode(1));
	}
	
}
