
package edu.uiowa.cs.baberman.proplogjedit.nodes;



/**
 *
 * @author bnjmnbrmn
 */
public class Terminal extends Node {
    private final String text;

    @Override
    public String getText() {
        return text;
    }

    public Terminal(String text) {
        this.text = text;
    }

    public Terminal(String text, int repeats) {
        if (repeats < 0) {
            throw new RuntimeException("repeats parameter must be non-negative");
        }
        String temp = "";
        for (int i = 0; i < repeats; i++) {
            temp += text;
        }
        this.text = temp;
    }
    
}
