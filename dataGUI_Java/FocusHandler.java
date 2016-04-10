/**
 * The FocusHandler class will get the gui data and perform error checking on
 * entries to employee number made by user from AddEmpGUI class.
 * 
 * Amanda Henry May 1, 2013
 */
import java.awt.event.*; import java.sql.*;
import java.awt.*; 
import javax.swing.*;
public class FocusHandler implements FocusListener 
{
    private AddEmpGUI gui; // Declare a reference to the GUI class
    //private EmpRec empRec; // Declare a reference to a EmpRec object ??
    // The constructor will:
    // 1. Acccept a AddEmpGUI object so it can link to the GUI class
    // 2. Initialize the database class
    public FocusHandler(AddEmpGUI gui )//throws Exception
    { this.gui= gui;
        PayrollDatabase.initialize();
    }

    public void focusLost(FocusEvent e) //throws Exception
    {
        String text1, text2, text3, text4,testString;
        int num1;        
        double num2, num3;
        if (e.getSource() == gui.empNum)//if source is employee number textField in class AddEmpGUI
        {
            text1 = gui.empNum.getText();//get the text and assign to text1
            if (gui.empNum.getText().length() < 6)//if the length is greater than zero
            {
                gui.empNum.setText("Employee number must be 6 digits");//error msg ..if not possible, notify user
                gui.empNum.requestFocus();//request focus
                return;
            }
            else if(gui.empNum.getText().length() >= 6)//if the length is greater or equal to 6
            {
                try
                {
                    num1 = Integer.parseInt(gui.empNum.getText());//Parse String in text1 to an integer
                }
                catch (NumberFormatException ex)//catch exception if not an integer
                {
                    gui.empNum.setText("This is not an integer. Please enter a number.");//if not possible, notify user
                    gui.empNum.requestFocus();//request focus
                    return;
                }                             
            }
        }
    }

    public void focusGained(FocusEvent e)
    {
        // Select the text of the appropriate textfield object
        JTextField text = (JTextField) e.getSource();
        text.selectAll();
    }
}
