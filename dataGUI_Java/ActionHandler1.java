/** This class implements ActionListener, a handler for class EmpDataGUI. It will retrieve the selected item from 
 * the JComboBox , and call a method to execute the requested query.
 * 
 * Amanda Henry
 * May1, 2013
 */
import javax.swing.*;//Needed for Panels
import java.awt.*;//Needed for Layout Manager
import java.awt.event.*;//Needed for event handlers
public class ActionHandler1 implements ActionListener 
{
    EmpDataGUI empGUI;//Reference variable for EmpDataGUI class   
    public ActionHandler1(EmpDataGUI empGUI)//pass the reference of Class EmpDataGUI to this constructor
    {
        ActionHandler1.this.empGUI = empGUI;         
    }

    public void actionPerformed( ActionEvent e)    
    {
        if (e.getSource()==empGUI.employeeNo)//if the source is the comboBox on panel2
        {
            String selection;//,text1;
            
            selection = (String)empGUI.employeeNo.getSelectedItem();//cast back to String and get selected item
            empGUI.getData2();//call method     

            //text1 = empGUI.text1.getText();//assign text in text1 of empGUI to text1 of ActionHandler1//to debug

            //System.out.print(selection +"  " +  "KKK" + "   "  + text1 + " " + "--" + " ");// + index);//debug
        }
        else 
        if (e.getSource()==empGUI.del)//if the source is the delete button on panel3
        {
            //System.out.println ("YYYY");//debug
            String selection;
            int index;
            selection = (String)empGUI.employeeNo.getSelectedItem();//cast selectedItem, assign to selection
            empGUI.doDelete(selection);//method that deletes this employee selection from database

            index = empGUI.employeeNo.getSelectedIndex();//get selected index from comboBox
            empGUI.employeeNo.removeItemAt(index);//remove the item from this index

            //System.out.println("DELETE");//debug
            //System.out.println(selection  +index + "Index DELETE" );//+ text1);//debug
        } 
    }
}   

