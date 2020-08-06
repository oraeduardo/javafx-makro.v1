package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao {

	private Connection conn;
	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public User findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM fiscal.tab_user WHERE user_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				User obj = new User();
				obj.setId(rs.getInt("user_id"));
				obj.setName(rs.getString("Name"));
				obj.setPassword(rs.getString("password"));
				obj.setAdmin(rs.getString("admin"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public User findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM fiscal.tab_user WHERE name = ? and rownum = 1");
			st.setString(1, name);
			rs = st.executeQuery();
			if (rs.next()) {
				User obj = new User();
				obj.setId(rs.getInt("user_id"));
				obj.setName(rs.getString("Name"));
				obj.setPassword(rs.getString("password"));
				obj.setAdmin(rs.getString("admin"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<User> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM fiscal.tab_user ORDER BY name");
			rs = st.executeQuery();
			List<User> list = new ArrayList<>();
			while (rs.next()) {
				User obj = new User();
				obj.setId(rs.getInt("user_id"));
				obj.setName(rs.getString("Name"));
				obj.setPassword(rs.getString("password"));
				obj.setAdmin(rs.getString("admin"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public void insert(User obj) {
		String generatedColumns[] = { "user_id" };		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO fiscal.tab_user "
							+ "(name, password, admin) "
							+ "VALUES "
							+ "(?, ?, ?)", generatedColumns);
				//Statement.RETURN_GENERATED_KEYS); 
			    //Esse comando para o ORACLE retorno o rowid da tabela e não a primeira coluna
			    //Para resolver o problema foi criado a variavel generatedColumns 
			    //cujo valor é o campo a ser retornado.
			st.setString(1, obj.getName());
			st.setString(2, obj.getPassword());
			st.setString(3, obj.getAdmin());
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(User obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE fiscal.tab_user " +
							"SET name = ?, " +
							"    password = ?, " +
							"    admin = ? " +
							"WHERE user_id = ?");
			st.setString(1, obj.getName());
			st.setString(2, obj.getPassword());
			st.setString(3, obj.getAdmin());
			st.setInt(4, obj.getId());
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM fiscal.tab_user WHERE user_id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}