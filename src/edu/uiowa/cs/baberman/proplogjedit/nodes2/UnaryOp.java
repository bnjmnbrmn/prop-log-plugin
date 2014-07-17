
package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
public class UnaryOp extends SelectableNode {
    private UnaryOpEnum op;

    public static enum UnaryOpEnum {
        NOT("~");
        private final String text;
        
        public String getText() {
            return text;
        }
        
        UnaryOpEnum(String text) {
            this.text = text;
        }
    } 
    
    
    public void setOp(UnaryOpEnum uo) {
        this.op = uo;
    }
    
    public UnaryOpEnum getOp() {
        return this.op;
    }
    
    @Override
    String getNonPlaceholderText() {
        return getOp().getText();
    }

    @Override
    String getPlaceholderText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
