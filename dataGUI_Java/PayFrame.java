
/**
 Provides a window GUI interface to the user. It will instantiate itself as a JFrame
 application, and add the GUI component.
 It contains the main method that executes a DB query.
 It also closes the data base, gracefully, when it's window (JFrame) is closed & exited.
 Displays the the DB table in a GUI
 
 Amanda Henry May 1, 2013
 */
import java.awt.*; import java.awt.event.*; import javax.swing.*; import java.sql.*;
public class PayFrame extends JFrame
{    
    public PayrollGUI gui; // Reference variable to the GUI object

    public PayFrame()// Constructor
    {
        setLayout (new BorderLayout());
        PayrollDatabase.initialize(); // Initialize the database connection

        PayrollGUI gui = new PayrollGUI();  //create instance of PayrollGUI      
        add(gui);//add gui to panel

        setSize(350,250);//set size
        //setResizable(false);
         //pack(); // Set the window to fit the GUI
        setTitle ("Payroll Query Results"); setVisible(true);
    }

    public static void main( String args[] )
    {
        PayFrame app = new PayFrame();//create an instance of PayFrame
        //Shut down the DB gracefuly, when the user closes this window (JFrame)

        app.addWindowListener
        (
            new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    PayrollDatabase.close(); System.exit(0);
                }
            }
        );
    }
} // end CourseListApp class