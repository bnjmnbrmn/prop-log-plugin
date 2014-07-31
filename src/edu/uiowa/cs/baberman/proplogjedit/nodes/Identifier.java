
package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class Identifier extends SelectableNode {
    String text = "";

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	@Override
	public void respondToLetterPress(String letter) {
		text += letter;
	}
    
	Identifier(boolean required) {
		super(required);
	}
}
