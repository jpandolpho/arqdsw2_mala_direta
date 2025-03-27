package br.com.ifsp.maladireta.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.ifsp.maladireta.model.Aniversariante;
import jakarta.servlet.ServletException;

public class AniversarianteDAO 
{
	private DataSource dataSource;

	public AniversarianteDAO() throws ServletException{
		try {
			InitialContext context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/MalaDireta");
		} catch (NamingException e) {
			throw new ServletException("Erro ao configurar JNDI", e);
		}
	}

	public List<Aniversariante> getAniversariantesDoDia() throws SQLException 
	{
        List<Aniversariante> lista = new ArrayList<>();
        String sql = "SELECT nome, email, data_nascimento FROM clientes " +
                     "WHERE MONTH(data_nascimento) = ? AND DAY(data_nascimento) = ?";
        
        try(Connection conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(sql)){
        	
        	LocalDate hoje = LocalDate.now();
        	stmt.setInt(1, hoje.getMonthValue());
            stmt.setInt(2, hoje.getDayOfMonth());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                lista.add(new Aniversariante(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getDate("data_nascimento").toLocalDate()
                ));
            }
        } catch(Exception e) {e.printStackTrace();}
        
        return lista;
    }

}
