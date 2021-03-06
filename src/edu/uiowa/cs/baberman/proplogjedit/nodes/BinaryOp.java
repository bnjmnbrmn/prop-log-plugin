package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class BinaryOp extends SelectableNode {

    public enum OpType {
        IMPLIES("->"), AND("/\\"), OR("\\/");
        
        final String text;
        
        private OpType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
    private OpType opType;

    public BinaryOp(OpType opType) {
        this.opType = opType;
    }

    @Override
    public String getPlaceholderText() {
        return "BINARY OPERATOR";
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
