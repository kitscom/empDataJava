// This entity class represents ONE empRecord from the Payroll.mdb ACCESS Database
import java.awt.event.*; import java.sql.*;
import java.awt.*;
import javax.swing.*;
public class EmpRec
{
    // variables
    private int empNum;// salary; // Employee number
    private String firstName, lastName, address, city, province, pCode;//salary;// overtime;
    double salaryWage, hrWage;
    boolean salary, overtime;

    public EmpRec(int empNum, double salaryWage, double hrWage ) //3-arg constructor

    {
        setEmpNum(empNum); setFirstName(" "); setLastName(" "); setAddress(" ");
        setCity(" "); setProvince(" "); setPCode(" "); setSalary(false);setSalaryWage(0.00);
        setHrWage(0.00); setOvertime(false);
    } 
    // Constructor that accepts all data attributes
    public EmpRec(int empNum , String firstName, String lastName, String address,
    String city, String province, String pCode,boolean salary, double salaryWage, double hrWage, boolean overtime)// int overtime)
    {
        setEmpNum(empNum); setFirstName(firstName); setLastName(lastName); setAddress(address);
        setCity(city); setProvince(province); setPCode(pCode);setSalary(salary); setSalaryWage(salaryWage);
        setHrWage(hrWage); setOvertime(overtime); //setSalary(salary);
    }//
    // Accessor methods (Getters)
    public int getEmpNum() {return empNum;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getAddress() {return address;}

    public String getCity() {return city;}

    public String getProvince() {return province;}

    public String getPCode() {return pCode;}

    public int getSalary() {return salary?1:0;}// int

    public double getSalaryWage() {return salaryWage;}

    public double getHrWage() {return hrWage;} 
    // public boolean getOvertime() {return overtime;}
    public int getOvertime(){return overtime?1:0;}
    //Mutator methods
    public void setEmpNum(int empNum) {this.empNum = empNum;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setAddress(String address) {this.address = address;}

    public void setCity(String city) {this.city = city;}

    public void setProvince(String province) {this.province = province;}

    public void setPCode(String pCode) {this.pCode = pCode;}   

    public void setSalary (boolean salary) {this.salary = salary;}

    public void setSalaryWage(double salaryWage) {this.salaryWage = salaryWage;}  

    public void setHrWage(double hrWage) {this.hrWage = hrWage;} 

    public void setOvertime(boolean overtime) {this.overtime = overtime;}

    private class EmpHandler implements ActionListener
    {
        private AddEmpGUI gui; // Declare a reference to the GUI class
        private EmpRec empRec; // Declare a reference to a EmpRec object    
        public EmpHandler(AddEmpGUI gui )//Acccept an AddEmpGUI object so it can link to the GUI class
        { this.gui= gui;
            PayrollDatabase.initialize(); }//Initialize the database class

        public void actionPerformed(ActionEvent e)
        {
            int result = 0;
            int result2 = 0;

            boolean salary  = true;
            boolean overtime = false;        

            if(e.getSource()== gui.salRad)//if the source is salary radio button
            {
                salary = true;
                overtime = false;

                gui.hrWage.setEditable(false);
                gui.salaryWage.setEditable(true);
                gui.salaryWage.setBackground(Color.WHITE);
                gui.hrWage.setBackground(Color.GRAY);
                gui.hrWage.setText("");
                return;
            }
            if(e.getSource()== gui.hrRad)//if source is hourly radio button
            {
                salary = false;
                overtime = true;

                gui.hrWage.setEditable(true);
                gui.hrWage.setBackground(Color.WHITE);
                gui.salaryWage.setEditable(false);
                gui.salaryWage.setBackground(Color.GRAY);
                gui.salaryWage.setText("");
                return;
            }
            if(e.getSource()==gui.addRecordButton)//if source is the addRecordButton
            {
                if(gui.empNum.getText().length()==0)
                {                             
                    String text1 = gui.empNum.getText();//get the text and assign to text1
                    int num1;
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
                gui.errorMsg.setText("");
                //call doStuff() method that will error handle user entered text
                doStuff(gui.pCode);doStuff(gui.province);doStuff(gui.city);doStuff(gui.address);
                doStuff(gui.lastName);doStuff(gui.firstName);           

                double wages;
                if(gui.salRad.isSelected())//if salary radio button is selected when source is addRecordButton
                {
                    try
                    {
                        wages=Double.parseDouble(gui.salaryWage.getText());                   
                    }
                    catch(NumberFormatException ex)
                    {
                        if(gui.salaryWage.getText().length()==0)
                        {gui.salaryWage.setText("Fill in the field");}
                        else
                        {gui.salaryWage.setText("Not a double");}
                        gui.salaryWage.setBackground(Color.GREEN);
                        gui.salaryWage.requestFocus();
                        //gui.salaryWage.selectAll();  
                        return;
                    }
                }            
                //System.out.println("after salary wage");//debug
                if(gui.hrRad.isSelected())//if hourly radio button is selected when source is addRecordButton
                {
                    try
                    {
                        wages=Double.parseDouble(gui.hrWage.getText());                    
                    }
                    catch(NumberFormatException ex)
                    {
                        if(gui.hrWage.getText().length()==0)
                        {gui.hrWage.setText("Fill in the field");}
                        else
                        {gui.hrWage.setText("Not a double");}
                        gui.hrWage.setBackground(Color.GREEN);
                        gui.hrWage.requestFocus();                    
                        return;
                    }
                }            
                //System.out.println("beforere empRec");//debug
                try
                {
                    empRec= new EmpRec(Integer.parseInt(gui.empNum.getText()),//Create new empRec
                        gui.firstName.getText(), gui.lastName.getText(),
                        gui.address.getText(), gui.city.getText(),
                        gui.province.getText(), gui.pCode.getText(),salary,
                        (gui.salRad.isSelected()?Double.parseDouble(gui.salaryWage.getText()):0),
                        (gui.hrRad.isSelected()?Double.parseDouble(gui.hrWage.getText()):0), overtime);

                    //System.out.print(" This address  should be the new empRec =  " + empRec + " i am in EmpHandler ");//debug

                    result = PayrollDatabase.addRecord(empRec);//Perform update query to add new EmpRec record
                }
                catch(Exception ex)
                {
                    System.out.println ("I AM THE Ecxeption");
                }
                gui.statMsg.setText( result == 1 ? "Record Added" : "Record not Added");
            }
        }

        private void doStuff(JTextField thing)//Method for error handling user text
        {
            if (thing.getText().length()==0)
            {
                try
                {
                    thing.setBackground(Color.GREEN);
                    thing.requestFocus();
                    gui.errorMsg.setText("Please fill in highlighted fields");
                    //return;
                }
                catch(Exception ex){}                
            }
            else
            {
                thing.setBackground(Color.WHITE);
            }            
        }

    }

} // end of class EmpRec