/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade8;

//1º PASSO - IMPORTAR OS PACOTES NECESSÁRIOS
import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Juliano
 */
public class Unidade8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AplicacaoMDI frameAplicacaoMDI = new AplicacaoMDI();
        frameAplicacaoMDI.setVisible(true);
    }
    
    public static String selectJobId (String name) {
	ResultSet result;
	String sql = "SELECT id FROM job WHERE name = '"+name+"';";
        String jobId = "";
	
	try {
            String url = "jdbc:postgresql://localhost:5432/PA";
            String usuario = "postgres";
            String senha = "postgres";
            //2º PASSO - REGISTRAR O DRIVER JDBC
            Class.forName ("org.postgresql.Driver");
            //3º PASSO - ABRIR UMA CONEXÃO
            Connection con = DriverManager.getConnection(url, usuario, senha);
            //4º PASSO - EXECUTAR A QUERY
            Statement stm = con.createStatement();
            result = stm.executeQuery (sql);
            //5º PASSO - EXTRAIR AS INFORMAÇÕES DESEJADAS DO RESULTADO OBTIDO
            while (result.next()){
                jobId = result.getString("id");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobId;
    }
    
    public static String selectDepartmentId (String name) {
	ResultSet result;
	String sql = "SELECT id FROM department WHERE name = '"+name+"';";
        String departmentId = "null";
	
	try {
            String url = "jdbc:postgresql://localhost:5432/PA";
            String usuario = "postgres";
            String senha = "postgres";
            Class.forName ("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, usuario, senha);
            Statement stm = con.createStatement();
            result = stm.executeQuery (sql);
            while (result.next()){
                departmentId = result.getString("id");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departmentId;
    }
    
    public static void insertEmployee (String firstname, String lastname, String email, String phone, String hire, String job_id, String salary, String commission, String manager_id, String department_id) {
        String sql = "INSERT INTO employee (firstname, lastname, email, phone, hire, job_id, salary, commission, manager_id, department_id)"
            + "VALUES ('"+firstname+"', '"+lastname+"', '"+email+"', "+phone+", '"+hire+"', "+job_id+", "+salary+", "+commission+", "+manager_id+", "+department_id+");";
	
        try {
            String url = "jdbc:postgresql://localhost:5432/PA";
            String usuario = "postgres";
            String senha = "postgres";
            Class.forName ("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, usuario, senha);
            Statement stm = con.createStatement();
            stm.executeUpdate (sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void selectEmployee (String firstname, String lastname) {
        String sql = "";
	
        try {
            String url = "jdbc:postgresql://localhost:5432/PA";
            String usuario = "postgres";
            String senha = "postgres";
            Class.forName ("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, usuario, senha);
            Statement stm = con.createStatement();
            stm.executeUpdate (sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void reportEmployee (String nome, String sobrenome) throws ClassNotFoundException, SQLException {
        ResultSet result;
        String sql =
            "SELECT e.firstname AS \"Nome\",\n" +
            "   e.lastname AS \"Sobrenome\",\n" +
            "	e.email AS \"E-mail\",\n" +
            "	e.phone AS \"Fone\",\n" +
            "	j.name AS \"Função\",\n" +
            "	e.salary AS \"Salário\",\n" +
            "	e.commission AS \"Comissão\",\n" +
            "	m.firstname || ' ' || m.lastname AS \"Chefe\",\n" +
            "	d.name AS \"Departamento\"\n" +
            "FROM employee e\n" +
            "	INNER JOIN job j ON (e.job_id = j.id)\n" +
            "	INNER JOIN employee m ON (e.manager_id = m.id)\n" +
            "	INNER JOIN department d ON (e.department_id = d.id)\n" +
            "WHERE e.firstname LIKE '%"+nome+"%'" +
            "   AND e.lastname LIKE '%"+sobrenome+"%';";
	
        try {
            String url = "jdbc:postgresql://localhost:5432/PA";
            String usuario = "postgres";
            String senha = "postgres";
            Class.forName ("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, usuario, senha);
            Statement stm = con.createStatement();
            result = stm.executeQuery (sql);
            JRResultSetDataSource relatResult = new JRResultSetDataSource(result);
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/Users/Juliano/Documents/iReport/Unidade6.jasper", new HashMap(), relatResult);
            JasperViewer jpViewer = new JasperViewer (jpPrint, false); //false - não encerra a aplicação ao fechar a janela
            jpViewer.setVisible(true);
            jpViewer.toFront(); //apresenta o relatório acima das outras janelas
        } catch (JRException jpExc) {
            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: "+jpExc);
        }
    }
}
