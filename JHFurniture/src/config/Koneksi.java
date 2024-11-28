package config;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Koneksi {
    //atribut
    protected Connection konek;
    protected Statement stmt;
    
    //constructor
    public Koneksi(){
        try {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            konek = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/furniture",
                            "root","");
            }catch(SQLException ex) {
                System.out.println("SQL ERROR: "+ex.getMessage());
                }
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: "+ex.getMessage());
    }
}
public Statement createStatement() throws SQLException {
return stmt = konek.createStatement();
    }
}

