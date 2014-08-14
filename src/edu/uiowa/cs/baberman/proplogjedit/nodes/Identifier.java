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

    Identifier(boolean required) {
        super(required);
    }

    /**
     * @param text the text to set
     */
    public void setIdentifierString(String text) {
        this.identifierString = text;
    }

    public void appendToIdentifierString(String toAppend) {
        this.identifierString += toAppend;
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
