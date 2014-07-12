package edu.uiowa.cs.baberman.proplogjedit;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gjt.sp.jedit.Buffer;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofModel {

    Proof proof;
    SelectableNode currentChildNode;

    ProofModel() {
        proof = new Proof();
        currentChildNode = proof.getSelectableSubnodes().get(0);
    }

//    private void updateViews() {
//        updateBuffer();
//        updateHighlights();
//        updateUnderline();
//    }
    List<ProofView> proofViews = new ArrayList<ProofView>();

    void addProofView(ProofView proofView) {
        proofViews.add(proofView);

        proofView.setProofModel(this);

        updateViews();
    }

    private void updateViews() {
        for (ProofView proofView : proofViews) {
            proofView.update();
        }
    }

    Proof getProof() {
        return proof;
    }

}

abstract class Node {

    abstract String getText();
    
}

class Leaf extends Node {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public Leaf(String text) {
        this.text = text;
    }

    public Leaf(String text, int repeats) {
        if (repeats < 0) {
            throw new RuntimeException(
                    "repeats parameter must be non-negative");
        }

        String temp = "";
        for (int i = 0; i < repeats; i++) {
            temp += text;
        }

        this.text = temp;
    }

}

interface SelectableNode {
    
}

abstract class InsertionPoint extends Node implements SelectableNode {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public InsertionPoint(String text) {
        this.text = text;
    }

}

class OptionalInsertionPoint extends InsertionPoint implements SelectableNode {

    public OptionalInsertionPoint(String text) {
        super(text);
    }
    
}

class RequiredInsertionPoint extends InsertionPoint implements SelectableNode {

    public RequiredInsertionPoint(String text) {
        super(text);
    }

}

abstract class InnerNode extends Node implements SelectableNode {
    List<Node> subnodes = new ArrayList<Node>();
    
    @Override
    String getText() {
        String text = "";
        for (Node subnode : subnodes) {
            text += subnode.getText();
        }
        return text;
    }

    List<SelectableNode> getSelectableSubnodes() {
        List<SelectableNode> selectableSubnodes = new ArrayList<SelectableNode>();
        
        for (Node subnode : subnodes) {
            if (subnode instanceof SelectableNode) {
                selectableSubnodes.add((SelectableNode)subnode);
            }
        }
        
        return selectableSubnodes;
    }
}


final class Proof extends InnerNode {
    
    Proof() {
        subnodes.add(new Leaf("Parameters "));
        subnodes.add(new RequiredInsertionPoint(PropVar.getPlaceholderText()));
        subnodes.add(new Leaf(" : Prop.\n\n"));
        subnodes.add(new RequiredInsertionPoint(ProofItem.getPlaceholderText()));
    }
    
    static String getPlaceholderText() {
        return "PROOF";
    }

}

final class ProofItem extends InnerNode {

    static String getPlaceholderText() {
        return "PROOF_ITEM";
    }
   
}

final class Section extends InnerNode {

    static String getPlaceholderText() {
         return "SECTION";
    }

}

final class ProofLine extends InnerNode {
    
    static String getPlaceholderText() {
         return "PROOF_LINE";
    }
    
}

final class PropVar extends InnerNode {
    
    static String getPlaceholderText() {
         return "PROP_VAR";
    }
    
}
