/**
 This class is the GUI component of the EmpFrame Application
It contains the SWING components required to retrieve data from the
user for insert into the payroll.mdb ACCESS database and registers JComponents with 
handler classes. 
 */
import java.awt.*; import java.awt.event.*; import javax.swing.*;
import javax.swing.border.*;//Needed for titled border and class Border
public class AddEmpGUI extends JPanel
{
    // Declare the SWING ‘GUI’ Components
    JButton addRecordButton;
    JTextField empNum, firstName, lastName, address, city, province,
    pCode ,salaryWage, hrWage;
    ButtonGroup moneyGroup;
    JRadioButton salRad, hrRad;
    JLabel statMsg,errorMsg, topLab;
    JPanel panel1;
    // Create the Constructor that will setup the JPanel
    public AddEmpGUI()//throws Exception
    {
        //Set the JPanel's Layout as 2 column GridLayout & populate with GUI components
        setLayout(new GridLayout(0,2));

        Font font1 = new Font("Arial", Font.BOLD, 16);//create a font
        
        topLab =  new JLabel("Add New Employee", JLabel.LEFT);//create top label
        topLab.setFont(font1);//set the font
        add(topLab);//add
        add(new JLabel(""));//make space
        add(new JLabel(""));add(new JLabel(""));//make space

        add(new JLabel("Employee")); add( empNum = new JTextField() );
        add(new JLabel("First Name")); add( firstName = new JTextField() );
        add(new JLabel("Last Name")); add( lastName = new JTextField() );
        add(new JLabel("Address"));//create and add labels/textFields

        add( address = new JTextField() );
        add(new JLabel("City")); add( city = new JTextField() );
        add(new JLabel("Province")); add( province = new JTextField() );
        add(new JLabel("Postal Code")); add( pCode = new JTextField() );

        moneyGroup = new ButtonGroup();//create radioButton group

        //Font font2 = new Font ("Arial", Font.ITALIC, 12);
        add( salRad = new JRadioButton("Salary") );//create and add 2 radio buttons
        add( hrRad = new JRadioButton("Hourly"));

        //salRad.setSelected(true);
        //hrRad.setSelected(true);

        moneyGroup.add(salRad); moneyGroup.add(hrRad);// Add the radio buttons to the 

        add(new JLabel("Salary")); add(salaryWage = new JTextField("") );
        add(new JLabel(" Hourly Wage")); add( hrWage = new JTextField("") );
        add(new JLabel(""));
        
        add(errorMsg = new JLabel(""));        
        add(statMsg = new JLabel(""));//create and add statMsg label
        
        add(addRecordButton=new JButton("Add new Record"));//create and add button
        addRecordButton.setBorder(BorderFactory.createRaisedBevelBorder());//create a border for button
              
        EmpHandler ah = new EmpHandler (this);
        // Register the Add Record button to the EmpHandler class
        //addRecordButton.addActionListener(new EmpHandler(this));
        addRecordButton.addActionListener(ah);
        //salRad.addFocusListener(new FocusHandler(this));
        salRad.addActionListener(ah);//(new EmpHandler(this));//register 2 radio buttons with the EmpHandler class
        salRad.doClick();
        //hrRad.addFocusListener(new FocusHandler(this));
        hrRad.addActionListener(ah);
        
        FocusHandler fh = new FocusHandler(this);

        empNum.addFocusListener(fh);//register 3 textFields with the FocusHandler class
        firstName.addFocusListener(fh);
        lastName.addFocusListener(fh);
        address.addFocusListener(fh);
        city.addFocusListener(fh);
        province.addFocusListener(fh);
        pCode.addFocusListener(fh);
        salaryWage.addFocusListener(fh);
        hrWage.addFocusListener(fh);
        

        
        // address, city, province,
               // pCode ,salaryWage, hrWage;
        //salRad.setSelected(true);
        //hrWage.setEditable(false);
        empNum.requestFocus();

    }// end constructor
    
} // end class AddEmpGUI
