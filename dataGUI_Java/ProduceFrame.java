/**This class creates a JFrame instance of the EmpDataGUI
 * Amanda Henry
 * May 1, 2013
 */  
import java.awt.*; import java.awt.event.*; import javax.swing.*;
import java.sql.*;
public class ProduceFrame extends JFrame 
{
    protected EmpDataGUI gui;// variable to reference EmpDayaGUI  
    public ProduceFrame()throws SQLException
    {      
        gui = new EmpDataGUI();//Create instance
        add(gui);//add gui object to JFrame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Specify action for close
       
        setTitle("Payroll Info");//set title
        setSize(575,100);//set size        
        pack();//pack contents of window
        //setResizable(false);//set so as not to be able to resize window
        setLocationRelativeTo(null);//Center it
        setVisible(true);//display it
    }

    public static void main (String[]args)throws SQLException
    {
        new ProduceFrame();//Create instance 
    }
}