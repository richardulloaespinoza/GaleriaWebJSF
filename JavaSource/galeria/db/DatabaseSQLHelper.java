package galeria.db;

import galeria.model.Imagen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Utilitario de consulta de base de datos.
 * @author jlgranda jlgranda@eqaula.org
 *
 */
public class DatabaseSQLHelper {
	

	/** Retorna la conexión activa a la base de datos
	 * @return la conexión activa a la base de datos
	 * @throws NamingException
	 * @throws SQLException
	 */
	protected static Connection getConnection() throws SQLException {
		
		
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/galeria", "root", "3q4ul4");
			
		return c;
	}
	
	
	public static String getStringFieldFromSQL(String sql){
		String buffer = new String("-");
		Connection conn;
		ResultSet rs = null;
		try {
			conn = DatabaseSQLHelper.getConnection();
			try {
		      Statement st = conn.createStatement();
		      for (rs = st.executeQuery(sql); rs.next(); buffer = rs.getString(1)){
		      }
		    } catch (SQLException ex) {
		    	System.err.println(ex.getMessage());
		    } 
			conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} 
		return buffer;
	}
	
	public static Long getLongFieldFromSQL(String sql){
		Long buffer = new Long(0);
		Connection conn;
		ResultSet rs = null;
		try {
			conn = DatabaseSQLHelper.getConnection();
			try {
		      Statement st = conn.createStatement();
		      
		      for(rs = st.executeQuery(sql); rs.next(); buffer = new Long(rs.getLong(1))){
		        
		      }
		    } catch (SQLException ex) {
		    	System.err.println(ex.getMessage());
		    } 
			conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} 
		return buffer;
	}
	
		
	public static boolean executeUpdate(String sql) throws  SQLException{
		Connection conn = null;
		boolean valorRetorno = false;
		try {
			conn = DatabaseSQLHelper.getConnection();

			PreparedStatement importarEstructuraOrganizacionalSQL = conn
					.prepareStatement(sql);
			valorRetorno = importarEstructuraOrganizacionalSQL.executeUpdate() != 0;
		} finally {
			if (conn != null)
				conn.close();
		}
		return valorRetorno;
	}

	public static boolean updateImage(Imagen imagen) throws  SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE imagen SET "); 
		sql.append("titulo = "); 
		sql.append("'" + imagen.getTitulo() + "'");
		sql.append(", ruta = "); 
		sql.append("'" + imagen.getRuta() + "'");
		sql.append(" WHERE id = ");
		sql.append(imagen.getId());
		return DatabaseSQLHelper.executeUpdate(sql.toString());
	}
	
	public static boolean insertImage(Imagen imagen) throws  SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO imagen (id, titulo, ruta) VALUES (");
		sql.append("null");
		sql.append(", '");
		sql.append(imagen.getTitulo());
		sql.append("', '");
		sql.append(imagen.getRuta() + "'");
		sql.append(")");
		return DatabaseSQLHelper.executeUpdate(sql.toString());
	}
	
	public static boolean deleteImage(Imagen imagen) throws  SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM imagen "); 
		sql.append(" WHERE id = ");
		sql.append(imagen.getId());
		return DatabaseSQLHelper.executeUpdate(sql.toString());
	}
	
	public static  List<Imagen> getListaImagenes(){
		List<Imagen> buffer = new ArrayList<Imagen>();
		Connection conn;
		ResultSet rs = null;
		try {
			conn = DatabaseSQLHelper.getConnection();
			try {
		      Statement st = conn.createStatement();
		      Imagen img = null;
		      int i = 0;
		      for(rs = st.executeQuery("SELECT id, titulo, ruta FROM imagen"); rs.next(); ){
		        img = new Imagen();
		        img.setId(rs.getInt(1));
		        img.setTitulo(rs.getString(2));
		        img.setRuta(rs.getString(3));
		        img.setPersisted(true);
		        
		        //indice para manejo en memoria
		        img.setIndice(i);
		        i++; 
		        buffer.add(img); //agregar a memoria con estado persistido
		      }
		    } catch (SQLException ex) {
		    	System.err.println(ex.getMessage());
		    } 
			conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} 
		return buffer;
	}
}

