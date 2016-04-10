/**
 * This class will connect to a database and allow for the retrieval of employee data based on an employee number 
 * selected by user in a comboBox.It also registers the ActionListener with the comboBox and a JButton. The user may delete an 
 * employee after selecting and displaying them.
 * 
 * Amanda Henry, May 1, 2013
 */ 
import java.awt.*; import javax.swing.*;  
import java.sql.*;
public class EmpDataGUI extends JPanel
{
    Connection connect; //connection object
    //JComponent variables
    protected JComboBox employeeNo;
    protected JTextField text1;
    protected JLabel lab1,lab2, lab3, lab4;
    protected JPanel panel1,panel2;
    protected JButton del;
    
    ActionHandler1 ah;

    public EmpDataGUI()
    {        
        final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver", 
        URL = "jdbc:odbc:payroll";//driver type & DB name
        setLayout(new BorderLayout());//set the layout
        try 
        { Class.forName(DRIVER);//loads the driver and connects to the database
            connect = DriverManager.getConnection(URL);//connects to the database
        }

        catch(ClassNotFoundException cnfex)
        {
            System.err.println("Did not load JDBC/ODBC driver.");//catches error if driver does not load 
            cnfex.printStackTrace(); //trace error
            System.exit(1);//exit
        }         
        catch (SQLException sqlex)
        {
            System.err.println("Unable to connect");//catches error if can not connect to database
            sqlex.printStackTrace();//trace error
        }
        catch (Exception ex)
        {
            System.err.println("Unable to connect"); //catches error if can not connect to database
            ex.printStackTrace();//trace error
        }
        panel1 = new JPanel();//create panel1
        add(panel1, BorderLayout.NORTH);//add panel to north
        panel2 = new JPanel();//create panel2
        add(panel2, BorderLayout.SOUTH);//add panel2 to South
        getData();// Retrieve the database information
        ah = new ActionHandler1(this);//create ActionHandler1
        employeeNo.addActionListener(ah); //register comboBox for events       
        getData2();//retrieve database information

        
    }

    private void getData()//defines the query and executes it
    {
        final String SQL_QUERY = "SELECT pay.employeenumber FROM address,pay WHERE pay.employeenumber = address.employeenumber ";
        Statement statement; //reference variable for Statement object
        ResultSet result;// reference variable for ResultSet object
        try
        {
            statement = connect.createStatement();// Retrieve a statement object from the Connection object            
            result = statement.executeQuery(SQL_QUERY);//ResultSet object from the Statement object by running the query
            showResults(result); // translate the result set into JComboBox
            statement.close(); // Close the statement object
        }
        catch(SQLException sqlex)//catching exception
        { 
            sqlex.printStackTrace(); 
        }
    }    

    private void showResults(ResultSet rs)throws SQLException//accept a ResultSet object,transform data into a JComboBox
    {
        boolean moreRec = rs.next();        
        if (! moreRec)//if false , no records were returned from the database 
        { 
            JOptionPane.showMessageDialog(this,"No records"); 
            return; 
        }
        employeeNo = new JComboBox();//create the ComboBox 

        try
        {
            do//next row
            {
                employeeNo.addItem(rs.getString(1)); //column #1...add item to comboBox
            }while(rs.next());//rs.next() will move ResultSet to next record until false
        }
        catch(SQLException sqlex)
        { 
            sqlex.printStackTrace();  //Catches exceptions throws if query fails
        }
        lab1 = new JLabel("Select an Employee", JLabel.RIGHT);

        employeeNo.setPreferredSize(new Dimension(400,20));//make comboBox bigger
        panel1.add(lab1);//add label
        panel1.add(employeeNo); //add comboBox

        validate(); //repaint the window ensuring all objects are visible on the screen 
    }

    public void getData2()//defines the  second query and executes it
    {
        final String SQL_QUERYB = "SELECT first, last, salarywage, hourlywage FROM address,pay WHERE address.employeenumber = pay.employeenumber AND pay.employeenumber=" + (String)employeeNo.getSelectedItem();

        Statement statement2; //reference variable for Statement object
        ResultSet result2;//reference variable for ResultSet object
        try
        {
            statement2 = connect.createStatement();// Retrieve a statement object from the Connection object 

            result2 = statement2.executeQuery(SQL_QUERYB);//ResultSet object from the Statement object by running the query
            showResults2(result2); // translate the result set into JTextField
            statement2.close(); // Close the statement object
        }
        catch(SQLException sqlex) 
        { 
            sqlex.printStackTrace(); //Catches exceptions throws if query fails
        }
    }    

    public void showResults2(ResultSet rs2)throws SQLException//accept a ResultSet object,transform data into a textField
    {
        if(text1 != null)//if the textField is not empty
        {
            panel2.remove(lab2);//remove a label
            panel2.remove(text1);//remove a textField
            panel2.remove(del);//remove delete button
        } 

        text1 = new JTextField(15);//create new textField
        text1.setEditable(false);//non editable
        lab2 = new JLabel("Employee Information", JLabel.RIGHT);//create new label

        panel2.add(lab2);//add label
        panel2.add(text1);//add textField

        del = new JButton("Delete");//create button
        panel2.add(del);//add button
        //ActionHandler1 aj = new ActionHandler1(this);//create new ActionHandler
        del.addActionListener(ah);//register delete button with ActionHandler

        try
        {
            while(rs2.next())//next row moves to second
            {
                String text = (rs2.getString(1)+ rs2.getString(2) + rs2.getString(3) + rs2.getString(4)); //column #1,2,3,4
                text1.setText(text);//set the textField to show all data from results
               // System.out.println(text);//debug
            }
        }
        catch(SQLException sqlex)
        { 
            sqlex.printStackTrace(); 
        }

        validate(); //repaint the window ensuring all objects are visible on the screen
    }


    public void doDelete(String selection)//method to delete queries
    {
        final String delQuery1 = "DELETE FROM pay WHERE employeenumber = " + selection;//(String)employeeNo.getSelectedItem();
        //+ selection;
        final String delQuery2 = "DELETE FROM address WHERE employeenumber = "+ selection;//(String)employeeNo.getSelectedItem();
        Statement statement3; //reference variable for Statement object

        int result1 = 0;
        int result2 = 0;        
        try
        {
            statement3 = connect.createStatement();// Retrieve a statement object from the Connection object           
            result1 = statement3.executeUpdate(delQuery1);//update query with change
            result2 = statement3.executeUpdate(delQuery2);//update query with change
            statement3.close(); // Close the statement object           
        }
        catch(SQLException sqlex) 
        { 
            sqlex.printStackTrace(); //Catches exceptions throws if query fails
        }
    }

    public void shutDown()// Close the database connection
    {
        try 
        {
            connect.close(); //try to close connection
        }
        catch( SQLException sqlex)
        {
            System.err.println("Could not disconnect");//Catches exceptions throws if cannot disconnect 
            sqlex.printStackTrace();
        }
    }
}
