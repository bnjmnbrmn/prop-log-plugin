package edu.uiowa.cs.baberman.proplogjedit.nodes2;

/**
 *
 * @author bnjmnbrmn
 */
public class ZeroOrMore<N extends Node> extends InnerNode {

    private final N forPlaceholderText;

    public ZeroOrMore(N forPlaceholderText) {
        this.forPlaceholderText = forPlaceholderText;
    }

    @Override
    String getPlaceholderText() {

        return "(" + forPlaceholderText.getText() + ")*";

    }

}
