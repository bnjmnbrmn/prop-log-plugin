package edu.uiowa.cs.baberman.proplogjedit;

import java.util.ArrayList;
import java.util.List;
import org.gjt.sp.jedit.Buffer;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofView {

    final Buffer buffer;
    
    Buffer getBuffer() {
        return buffer;
    }

    final List<Highlight> highlights = new ArrayList<Highlight>();
    
    private ProofModel proofModel;

    ProofView(Buffer buffer) {
        this.buffer = buffer;
        buffer.setReadOnly(true);
    }

    void setProofModel(ProofModel proofModel) {
        this.proofModel = proofModel;
    }

    void update() {
        
        buffer.setReadOnly(false);
        buffer.insert(0, proofModel.getRoot().getText());
        buffer.setReadOnly(true);
        
        //to do:  underline, potential spots, and highlights
        
    }
}
