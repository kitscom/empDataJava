
/**
 * The MainGui class unites surrounding classes by using instance variables of classes. These classes are added 
 * to thee MainGUI panel. Event listeners are added here.
 * 
 * Amanda Henry
 * February 21,2013
 */
import javax.swing.*;//Needed for Panels
import java.awt.*;//Needed for Layout Manager
import java.awt.event.*;//Needed got listeners
public class MainGUI extends JPanel 
{
    protected EmpDataGUI empGUI;    
    public MainGUI()//Constructor
    {
        setLayout(new BorderLayout());  
        empGUI = new EmpDataGUI();
        add(empGUI);
        //ItemHandler ih = new ItemHandler(this);
        ActionHandler1 ah = new ActionHandler1(this);
        //empGUI.employeeNo.addItemListener(ih);
        empGUI.employeeNo.addActionListener(ah);
        //empGUI.text1.addItemListener(ih);  
                
    }
}
