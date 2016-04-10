/**
This class is the database component of the PayFrame and EmpFrame Applications
It contains the code required to connect to and manipulate the database
It is a static class (doesn't need to be instantiated) that
1. Initialize the DB: load DB driver and connect to the DB
2. Runs a query and return a result set
3. Closes the DB 
OUTPUT: Returns a query result set

Amanda Henry May 1, 2013
 */
import java.sql.*;
public class PayrollDatabase
{
    private final static String DB_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver",
    URL= "jdbc:odbc:payroll"; //driver type & DB name
    private static Connection connect;//Connection object
    private static Statement statement; // Statement object for query
    // Initialize class to initially load the ODBC driver and connect to the database
    // This method must be called before any others
    public static void initialize()
    {
        try
        {
            Class.forName(DB_DRIVER); // loads the DB driver
            connect = DriverManager.getConnection(URL); //connects to the database
            statement = connect.createStatement(); //creates the statement for queries
        }
        catch(ClassNotFoundException cnfex)
        { //catches exception thrown when driver is not loaded
            System.err.println("Did not load JDBC/ODBC driver.");
            cnfex.printStackTrace();
            System.exit(1);
        }
        catch (SQLException sqlex)
        { //catches exception thrown when not connected to database
            System.err.println("Unable to connect to database"); sqlex.printStackTrace();
        }
    }// end initialize

    // This method will run the passed in query on the database and return the result set
    public static ResultSet runQuery (String query)
    {
        ResultSet resultSet = null; // ResultSet object for query
        try
        { resultSet = statement.executeQuery( query ); 
        }
        catch ( SQLException sqlex ) //catches exceptions throws if query fails
        {
            System.err.println("\nQuery terminated abnormally!\n"+ query);
            sqlex.printStackTrace();
        }
        catch (NullPointerException npex)
        {
            System.err.println(npex.getMessage()+"\nQuery was "+query);
        }
        return resultSet;
    } // end runQuery

    public static int addRecord(EmpRec empRec)
    {
        int result = 0;
        int result2 = 0;
        try
        { //Builds SQL insert by getting data from GUI fields              
            String ins = "INSERT INTO ADDRESS (employeenumber,FIRST, LAST, ADDRESS, CITY," +
                "PROVINCE, Postal) VALUES ("+ empRec.getEmpNum()+ ",'" +
                empRec.getFirstName() +"','" + empRec.getLastName() + "','" + 
                empRec.getAddress() + "','" + empRec.getCity()  + "','" +
                empRec.getProvince() + "','" + empRec.getPCode() + "'" + ");";

            // Execute the SQL insert statement. When running the executeUpdate method,
            // the number of rows affected is returned (contained in the result variable)
            // Since this is an insert of 1 record, the result variable should always be 1       

            String ins2 = "INSERT INTO PAY (employeenumber,salary,salarywage, hourlywage, overtime ) VALUES(" +
                empRec.getEmpNum() + " ," + empRec.getSalary()  + ", " + 
                empRec.getSalaryWage() + ", " + empRec.getHrWage()  +", " + "" + 
                empRec.getOvertime() + "" + ");";            
            
            //System.out.print("  LLL  ");//debug           

            //System.out.print(ins + " and " + ins2);//debug
            try
            {
                result = statement.executeUpdate(ins);
            }
            catch (Exception ex){return 0;}

            if(result!=1)//if result != 1, there is an error on this query.
            {throw new SQLException("Insert into address table failed. " + result);}
             
            try
            {
                 result = statement.executeUpdate(ins2);
            }
            catch (Exception ex){return 0;}
             if(result!=1)//if result != 1, there is an error on this query.
            {throw new SQLException("Insert2 into address table failed. " + result);}
           
            //result2 = statement.executeUpdate(ins2);

            
            //A good program would probably delete that previous ADDRESS insert before throwing an error
        }
        catch(SQLException sqlex)
        {
            System.err.println(sqlex.getMessage());
            result = -1;
        }
        return result;
    }// end addRecord  
    // Close the connection
    public static void close()
    {
        try
        { statement.close(); connect.close(); }
        catch( SQLException sqlex)
        {
            System.err.println("Could not disconnect"); sqlex.printStackTrace();
        }
    } // end close
}//end class PayrollDatabase