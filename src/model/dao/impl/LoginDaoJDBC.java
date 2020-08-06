package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import model.dao.LoginDao;
import model.entities.Login;

public class LoginDaoJDBC implements LoginDao {


	private Connection conn;
	public LoginDaoJDBC(Connection conn) {
		this.conn = conn;
	}

		@Override
		public Boolean findLogin(Login login) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT name, password FROM fiscal.tab_user WHERE upper(name) = upper(?) AND password = ? ");
				st.setString(1, login.getName());
				st.setString(2, login.getPassword());
				rs = st.executeQuery();
				if (rs.next()) {
					Login obj = new Login();
					obj.setName(rs.getString("name"));
					obj.setPassword(rs.getString("password"));
					return true; //obj;
				}
				return false;
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		}

}
