
package edu.uiowa.cs.baberman.proplogjedit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bnjmnbrmn
 */
public class ProofModel {
    
}



abstract class Node {
    
    abstract int getLeftOffset();
    abstract int getWidth();
    
    abstract List<Node> getSubnodes();
    
    abstract String getText();
    
}

class Proof extends Node {

    @Override
    int getLeftOffset() {
        return 0;
    }

    @Override
    int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ParameterDeclaration parameterDeclaration;
    
    private List<ProofItem> proofItems = new ArrayList<ProofItem>();
    
    @Override
    List<Node> getSubnodes() {
        List<Node> subnodes = new ArrayList<Node>();
        
        subnodes.add(parameterDeclaration);
        for (ProofItem proofItem : proofItems)
            subnodes.add(proofItem);
        
        return subnodes;
    }

    @Override
    String getText() {
        String text = "";
        
        text += parameterDeclaration.getText() + "\n";
        
        for (ProofItem proofItem : proofItems) {
            text += "\n" + proofItem.getText();
        }
        
        return text;
    }
}

final class ParameterDeclaration extends Node {

    //copy constructor
    ParameterDeclaration(ParameterDeclaration parameterDeclaration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int getLeftOffset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

abstract class ProofItem extends Node {

}

final class Section extends ProofItem {

    //copy constructor
    Section(Section section) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int getLeftOffset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

final class ProofLine extends ProofItem {

    //copy constructor
    ProofLine(ProofLine proofLine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int getLeftOffset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    List<Node> getSubnodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}