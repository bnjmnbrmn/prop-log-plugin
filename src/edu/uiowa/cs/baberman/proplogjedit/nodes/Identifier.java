package edu.uiowa.cs.baberman.proplogjedit.nodes;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class Identifier extends SelectableNode {

    private String identifierString;

    Identifier(boolean required) {
        super(required);
        identifierString = "";
    }

    /**
     * @param text the text to set
     */
    public void setIdentifierString(String text) {
        this.identifierString = text;
    }

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

    public void appendToIdentifierString(String toAppend) {
        this.identifierString += toAppend;
        if (isPlaceholder()) {
            setIsPlaceholder(false);
        }
        getProofModel().getProofView().update();
    }

    public void applyBackspace() {
        if (!isPlaceholder()) {
            identifierString
                    = identifierString
                    .substring(0, identifierString.length() - 1);
            if (identifierString.length() == 0) {
                setIsPlaceholder(true);
            }
        }

        getProofModel().getProofView().update();
    }

}
