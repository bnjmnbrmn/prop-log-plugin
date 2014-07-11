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
    Node currentChildNode;

    ProofModel() {
        proof = new Proof();
        currentChildNode = proof.getSubnodes().get(0);
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

interface NodeComponent {

    String getText();
}

class LeafText implements NodeComponent {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public LeafText(String text) {
        this.text = text;
    }

    public LeafText(String text, int repeats) {
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

class InsertionPoint implements NodeComponent {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public InsertionPoint(Node node) {
        text = node.getOptionalPlaceholderText();
    }

}

class Placeholder implements NodeComponent {

    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public Placeholder(Node node) {
        text = node.getRequiredPlaceholderText();
    }

}

abstract class Node implements NodeComponent {

    private final List<NodeComponent> components
            = new ArrayList<NodeComponent>();

    List<NodeComponent> getComponents() {
        return components;
    }

    @Override
    public String getText() {
        String text = "";
        for (NodeComponent component : components) {
            text += component.getText();
        }
        return text;
    }
    
    final String getOptionalPlaceholderText() {
        String text = "(* Optional: ";
        for (NodeComponent nc : components) {
            text += nc.getText();
        }
        return text;
    }

    final String getRequiredPlaceholderText() {
        String text = "(* ";
        for (NodeComponent nc : components) {
            text += nc.getText();
        }
        text += " *)";
        return text;
    }

}

class ZeroOrMore<NC extends NodeComponent> extends Node {

    String optionalPlaceholderText;
    String requiredPlaceholderText;
    
    ZeroOrMore(NC nc) {
        
    }
    
    @Override
    public String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getOptionalPlaceholderText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getRequiredPlaceholderText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

class Concatenation<NC1 extends NodeComponent, NC2 extends NodeComponent> extends Node {


    
    Concatenation(NC1 nc1, NC2 nc2) {
        getComponents().add(nc1);
        getComponents().add(nc2);
    }

    
}

class Sum<N1 extends NodeComponent, N2 extends NodeComponent> extends Node {

    @Override
    String getOptionalPlaceholderText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getRequiredPlaceholderText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

class OneOrMore<N extends NodeComponent> extends Node {

    List<N> ns = new ArrayList<N>();
    
    public OneOrMore(N node) {
        
    }
    
    

    @Override
    public String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getOptionalPlaceholderText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getRequiredPlaceholderText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

class Proof extends Node {

    Proof() {
        getComponents().add(new LeafText("Parameters "));
        getComponents().add(new OneOrMore<PropVar>(new PropVar()));
        getComponents().add(new LeafText(" : Prop.\n"));
        getComponents().add(new OneOrMore<Concatenation<LeafText,ProofItem>>(
            new Concatenation<LeafText, ProofItem>()));
    }

    @Override
    String getOptionalPlaceholderText() {
        return "(* Optional:  PROOF *)";
    }

    @Override
    String getRequiredPlaceholderText() {
        return "(* Required:  PROOF *)";
    }

}

final class ParameterDeclaration extends Node {

    private PropVar firstPropVar;
    private final List<PropVar> subsequentPropVars = new ArrayList<PropVar>();

    @Override
    List<Node> getSubnodes() {
        List<Node> subnodes = new ArrayList<Node>();
        subnodes.add(firstPropVar);
        for (PropVar propVar : subsequentPropVars) {
            subnodes.add(propVar);
        }
        return subnodes;
    }

    @Override
    public String getText() {
        String text = "";
        text += "Parameters ";
        text += firstPropVar.getText();
        for (PropVar propVar : subsequentPropVars) {
            text += " " + propVar.getText();
        }
        text += ".";
        return text;
    }

    @Override
    boolean isComplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    List<Integer> getPotentialInsertionPointOffsets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    List<List<Class<? extends Node>>> getPotentialInsertionPointNodeTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

final class ProofItem extends Node {

    String getPlaceHolderText() {
        return "(* SECTION | PROOF_LINE *)";
    }

    @Override
    List<Node> getSubnodes() {
        return new ArrayList();
    }

    @Override
    String getText() {
        return getPlaceHolderText();
    }

    @Override
    boolean isComplete() {
        return false;
    }

}

final class Section extends Node {

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean isComplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

final class ProofLine extends Node {

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean isComplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

final class PropVar extends Node {

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean isComplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
