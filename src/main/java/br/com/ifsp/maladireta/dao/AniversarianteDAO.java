package br.com.ifsp.maladireta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.ifsp.maladireta.model.Aniversariante;

public class AniversarianteDAO 
{
	private String url = "jdbc:mariadb://localhost:3306/mala_direta";
    private String user = "root";
    private String password = "root";

	public List<Aniversariante> getAniversariantesDoDia() throws SQLException 
	{
        List<Aniversariante> lista = new ArrayList<>();
        String sql = "SELECT nome, email, data_nascimento FROM clientes " +
                     "WHERE MONTH(data_nascimento) = ? AND DAY(data_nascimento) = ?";
        
        try
        {
        	Class.forName("org.mariadb.jdbc.Driver");
        	Connection connection = DriverManager.getConnection(url, user, password);
        	PreparedStatement stmt = connection.prepareStatement(sql);
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
