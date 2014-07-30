
package edu.uiowa.cs.baberman.proplogjedit.nodes;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bnjmnbrmn
 */
public abstract class InsertionPoint extends SelectableNode {
    private String text;
	private final Class<? extends SelectableNode> clazz;
	
	public Class<? extends SelectableNode> getRepresentingClass() {
		return clazz;
	}

    @Override
    public String getText() {
        return text;
    }

    public InsertionPoint(InnerNode parent, Class<? extends SelectableNode> clazz) {
        super(parent);
		
		this.clazz = clazz;
		
        try {
            this.text = "(*" + ((String) clazz.getDeclaredField("placeholderText").get(null)) + "*)";
			
        } catch (SecurityException ex) {
            Logger.getLogger(edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(edu.uiowa.cs.baberman.proplogjedit.nodes.InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
            this.text = "(* *)";
        } catch (NoSuchFieldException ex) {
			Logger.getLogger(InsertionPoint.class.getName()).log(Level.SEVERE, null, ex);
		} 
    }

	@Override
	public void respondToLetterPress(String letter) {
		if (getRepresentingClass())
	}
	
	
    
}
