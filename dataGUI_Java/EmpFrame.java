/**
This class is the Application component of the EmpFrame Application.
It will instantiate the JFrame application and add the JPanel component

Amanda Henry, May1, 2013
 */
import java.awt.*; import java.awt.event.*; import javax.swing.*;
public class EmpFrame extends JFrame
{
    AddEmpGUI gui;//declare the GUI reference variable    
    public EmpFrame()//throws Exception
    {
        gui = new AddEmpGUI(); // Instantiate the GUI class
        //gui.empNum.requestFocus();
        
        add(gui); // Add the GUI to the container
        // Set the JFrame properties
        setTitle("Add Record to Payroll Table");
        setVisible(true);
        setSize(450,375);
    }
    // Instantiate the JFrame application in the main method
    public static void main(String args[])//throws Exception
    {
        EmpFrame app = new EmpFrame();
        //Shut down the DB when the user closes the application window
        app.addWindowListener
        (
            new WindowAdapter()
            {

                public void windowClosing(WindowEvent e)
                { PayrollDatabase.close(); System.exit(0); }
            }
        );
    } // end main
} // end class EmpFrame
