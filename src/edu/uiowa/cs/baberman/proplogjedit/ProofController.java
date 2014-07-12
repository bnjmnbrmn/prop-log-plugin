
package edu.uiowa.cs.baberman.proplogjedit;

import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.jEdit;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofController {
    private ProofModel proofModel;
    private ProofView proofView;

    public ProofController() {
        
        Buffer newFile;
        newFile = jEdit.newFile(jEdit.getActiveView().getEditPane());
        
        proofModel = new ProofModel();
        proofView = new ProofView(newFile);
        proofModel.addProofView(proofView);
        
    }

    /**
     * @return the proofModel
     */
    public ProofModel getProofModel() {
        return proofModel;
    }

    /**
     * @param proofModel the proofModel to set
     */
    public void setProofModel(ProofModel proofModel) {
        this.proofModel = proofModel;
    }

    /**
     * @return the proofView
     */
    public ProofView getProofView() {
        return proofView;
    }

    /**
     * @param proofView the proofView to set
     */
    public void setProofView(ProofView proofView) {
        this.proofView = proofView;
    }
    
    
}
