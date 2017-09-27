package br.com.treinaweb.java.basico.cadastropessoas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treinaweb.java.basico.cadastropessaos.model.Pessoa;
import br.com.treinaweb.java.basico.cadastropessoas.utils.DbUtils;

public class PessoaDao implements IAbstractDao<Pessoa> {

	@Override
	public List<Pessoa> all()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			ResultSet rs = DbUtils.getResultSet(conn, "SELECT * FROM pessoas");
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			while (rs.next()) {
				Pessoa p = new Pessoa();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setIdade(rs.getInt("idade"));
			}
			return pessoas;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	public Pessoa findById(int id)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement statement = DbUtils.getPrepareStatement(conn, "SELECT * FROM pessoas WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Pessoa p = new Pessoa();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setIdade(rs.getInt("idade"));
				return p;
			} else {
				return null;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	@Override
	public void insert(Pessoa entidade)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement statement = DbUtils.getPrepareStatement(conn,
					"INSERT INTO pessoas(nome,idade) VALUES(?,?) ");
			statement.setString(1, entidade.getNome());
			statement.setInt(2, entidade.getIdade());
			statement.execute();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	public void update(Pessoa entidade) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement statement = DbUtils.getPrepareStatement(conn, 
							"UPDATE pessoas SET nome = ?, idade = ? WHERE id = ?");
			statement.setString(1, entidade.getNome());
			statement.setInt(2, entidade.getIdade());
			statement.execute();			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	public void delete(Pessoa entidade) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement statement = DbUtils.getPrepareStatement(conn, 
							"DELETE FROM pessoas WHERE id = ?");
			statement.setInt(1, entidade.getId());
			statement.execute();			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
