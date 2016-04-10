/*
Class: ParseResultSet
StereoType: 'Control' stereotype class. It does some business logic.
Purpose: This class is a generic class that parses any ResultSet object.
It will pull the metadata from the ResultSet object and create a columns
Vector and rows Vector containing all the information in the ResultSet object

Output: Returns the result set's column headers, and rows, as Vectors
 
 Amanda Henry May 1, 2013
*/
import java.sql.*; import java.util.Vector;
public class ParseResultSet
{
    //Instance variables:
    private ResultSet resultSet; // ResultSet object that is passed into the constructor
    private ResultSetMetaData rsMetaData; // ResultSetMetaData object derived from ResultSet
    private Vector heading = new Vector(), // Vector to hold the Headings of the result
    rows = new Vector(); // Vector to hold the data in the rows of the result set
    //Instance methods:
    // Constructor
    public ParseResultSet (ResultSet rs)
    {
        resultSet = rs; // Set the ResultSet object
        try
        {
            // Populate the ResultSetMetaData object based on the passed in result set object
            rsMetaData = resultSet.getMetaData();
            parseHeading(); // Populate the heading Vector
            parseRows(); // Populate the rows Vector
        }
        catch ( SQLException sqlex )
        { sqlex.printStackTrace(); }
    } // End Constructor
    private void parseHeading() throws SQLException
    {
        // Find all the column headings from the ResultSetMetaDataObject
        for ( int i = 1; i <= rsMetaData.getColumnCount(); ++i )
            heading.addElement( rsMetaData.getColumnName( i ) );
    } // End parseHeading
    private void parseRows() throws SQLException


    
    {
        //If there is a result set row, parse and attach its field (column) values
        while ( resultSet.next() ) rows.addElement( getRow() );
    } // End parseRows
    // This method will create and return a Vector containing all the fields of one
    // row in the Result Set
    private Vector getRow() throws SQLException
    {
        Vector currentRow = new Vector();
        for ( int i = 1; i <= rsMetaData.getColumnCount(); ++i )
        //    switch( rsMetaData.getColumnType( i ) )
        //    {
        //        case Types.BIT: currentRow.addElement(new Boolean(resultSet.getBoolean( i )));
        //        break;
        //        case Types.CHAR: currentRow.addElement( resultSet.getString( i ) ); break;
                /*case Types.VARCHAR:*/ 
                
                currentRow.addElement( resultSet.getString( i ) ); 
                
                //break;
        //        case Types.INTEGER: currentRow.addElement( new Long( resultSet.getLong( i ) ) );
        //        break;
        //        case Types.BIGINT: currentRow.addElement(new Long( resultSet.getLong( i ) ) );
        //        break;
        //        case Types.REAL: currentRow.addElement(new Float(resultSet.getFloat( i )));
        //        break;
        //        case Types.FLOAT: currentRow.addElement(new Double(resultSet.getDouble( i )));
        //        break;
        //        case Types.DOUBLE: currentRow.addElement(new Double(resultSet.getDouble( i )));
        //        break;
        //        default: System.out.println( "Type was: " + rsMetaData.getColumnTypeName( i ) );
        //}
        return currentRow;
    } // End getRow method
    // Accessors (Getters)
    // Returns a Vector containing the Column Headings
    public Vector getColHeadings () { return heading; }
    // Returns a 2 D “table” of data
    // – a “vertical” Vector whose elements are each a “horizontal” Vector of row data
    public Vector getRows () { return rows; }
}// end class ParseResultSet