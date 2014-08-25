
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class UnaryOp extends SelectableNode {

    public enum OpType {
        
        NOT("~");
        
        final String text;
        
        private OpType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
    private OpType opType;
    
    public UnaryOp(OpType opType) {
        this.opType = opType;
    }
    
    @Override
    public String getPlaceholderText() {
       return "UNARY OPERATOR";
    }

    @Override
    public String getText() {
        return opType.getText();
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
