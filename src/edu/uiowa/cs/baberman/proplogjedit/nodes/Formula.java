package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public class Formula extends InnerNode {

    public Formula(boolean required) {
        super(required);
    }

    @Override
    public String getPlaceholderText() {
        return "FORMULA";
    }

    @Override
    public SelectableNode deepCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setToBinaryOp(BinaryOp.OpType opType) {
        getSubnodes().clear();
        setIsPlaceholder(false);
        
        addSubnode(new Terminal("("));
        addSubnode(new Formula(true));
        addSubnode(new Terminal(" "));
        addSubnode(new BinaryOp(opType));
        addSubnode(new Terminal(" "));
        addSubnode(new Formula(true));
        addSubnode(new Terminal(")"));

        getProofModel().setSelectedNode((Formula) getSubnode(1));
        
    }

}
