/**
This class is the GUI component of the PayFrame Application.
It uses the ParseResultSet class to parse the Columns and Row information,
from a ResultSet object, to create a JTable component on a Frame. If a scrollPane is already there, it will be removed 
and a new one created. The ActionListener will run a user entered query to the dataBase.

Displays the result set in a JTable
Amanda Henry May 1, 2013
 */
import java.awt.*; import javax.swing.*; 
import java.sql.*; 
import java.util.Vector;
import java.awt.event.*; 
public class PayrollGUI extends JPanel 
{
    // Declare reference variables to the JTable and ParseResultSet class
    JTable resultsTable;
    JScrollPane resultsScrollPane;
    ParseResultSet parseResultSet;

    JTextField text1;
    JButton but1;
    JPanel panel1, panel2;
    ResultSet rs;
    public PayrollGUI()
    {
        setLayout(new BorderLayout());//setLayout
        panel1();//call method

        panel2 = new JPanel(new GridLayout(1,1));//create panel with gridLayout
        add(panel1, BorderLayout.NORTH);//add panel1
        add(panel2);//add panel2
    }

    public void panel1()
    {
        panel1 = new JPanel(new GridLayout(2,0));
        but1 = new JButton("Execute Query");
        //but1.setPreferredSize(new Dimension(400,20));
        text1 = new JTextField(35);       

        panel1.add(but1);
        panel1.add(text1);
        text1.requestFocus();

        ActionHandler ah = new ActionHandler();//create new ActionHandler
        but1.addActionListener(ah);//add to JButton        
        //text1.addActionListener(ah);
    }

    public void getResults(ResultSet rs)
    {
        // Create a ParseResultSet object to get the column heading and row Vectors
        parseResultSet = new ParseResultSet (rs);
        // Retrieve the vectors from the ParseResultSet object
        Vector headings = parseResultSet.getColHeadings(),
        rows = parseResultSet.getRows();
        // Instantiate the JTable based on the retrieved Vectors
        resultsTable = new JTable( rows, headings );
        // Add Scroll Bars to the JTable
        if (resultsScrollPane!=null) //if there is a scrollPane
        {
            panel2.remove(resultsScrollPane);//remove it            
        }
        resultsScrollPane = new JScrollPane( resultsTable );//create a new scrollPane with a JTable

        panel2.add( resultsScrollPane);// Add the JTable to the Panel
        updateUI();//Update the panel
    }

    private class ActionHandler implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String query= text1.getText();//retrieve query in textField using getText()

            ResultSet rs = PayrollDatabase.runQuery(query);// Run the passed in query to get the result set 

            getResults(rs);//add results to panel
        }
    }

}
