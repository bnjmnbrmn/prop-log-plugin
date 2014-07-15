
import edu.uiowa.cs.baberman.proplogjedit.PropLogPlugin;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.gjt.sp.jedit.jEdit;


/**
 *
 * @author bnjmnbrmn
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        jEdit.main(args);
        while(!jEdit.isStartupDone()) {
            Thread.sleep(500);
        }
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                PropLogPlugin plp = (PropLogPlugin) 
                        jEdit.getPlugin("edu.uiowa.cs.baberman.proplogjedit.PropLogPlugin", true);
                plp.createNewProof();
            }
        });
        
    }
    
}
