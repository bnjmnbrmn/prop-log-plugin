
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class OneOrTheOther<N1 extends Node, N2 extends Node> 
    extends InnerNode{

    N1 forPlaceholder1Text;
    N2 forPlaceholder2Text;
    
    public OneOrTheOther(N1 forPlaceholder1Text, N2 forPlaceholder2Text) {
        this.forPlaceholder1Text = forPlaceholder1Text;
        this.forPlaceholder2Text = forPlaceholder2Text;
    }
    
    @Override
    String getPlaceholderText() {
        return forPlaceholder1Text.getText() 
                + " | " + forPlaceholder2Text.getText();
    }
    
}
