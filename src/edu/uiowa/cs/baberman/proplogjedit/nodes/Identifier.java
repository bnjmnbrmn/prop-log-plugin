package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class Identifier extends SelectableNode {

    private String identifierString;

    @Override
    public String getText() {
        if (isARequiredPlaceholder()) {
            return getPlaceholderText();
        } else if (isAnOptionalPlaceholder() && isSelectedChild()) {
            return getPlaceholderText();
        } else if (isAnOptionalPlaceholder() && !isSelectedChild()) {
            return "";
        } else {
            return identifierString;
        }
    }

    public void respondToLetterPress(String letter) {
        if (isARequiredPlaceholder() || isAnOptionalPlaceholder()) {
            setPlaceholderStatus(PlaceholderStatus.NONPLACEHOLDER);
            setIdentifierString(letter);
        } else {
            setIdentifierString(identifierString + letter);
        }
    }

    Identifier(boolean required) {
        super(required);
    }

    /**
     * @param text the text to set
     */
    public void setIdentifierString(String text) {
        this.identifierString = text;
    }

}
