package model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ParametroDao;
import model.entities.Parametro;

public class ParametroDaoJDBC implements ParametroDao {
	
	private Connection conn;
	public ParametroDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private Parametro instantiateParametro(ResultSet rs) throws SQLException {
		Parametro pr = new Parametro();
		pr.setCodEmpresa(rs.getString("cod_empresa"));
		pr.setCodEstab(rs.getString("cod_estab"));
		pr.setRazaoSocial(rs.getString("razao_social"));
		pr.setDataInicial(new java.util.Date(rs.getTimestamp("data_inicial").getTime()));
		pr.setDataFinal(new java.util.Date(rs.getTimestamp("data_final").getTime()));
		
		return pr;
	}
	
	@Override
	public List<Parametro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select cod_empresa,                                                " + 
					"		cod_estab,                									" + 
					"		razao_social,             									" +
					"       trunc(add_months(last_day(sysdate),-1)+1) as data_inicial,  " +
					"       trunc(last_day(sysdate)) as data_final  					" +
					"  from msaf.estabelecimento e    									" +
					" where e.cod_empresa = '001'     									" +
					"   and e.dt_encerramento is null 									" +
					" order by e.cod_estab");
			rs = st.executeQuery();
			List<Parametro> list = new ArrayList<>();
			while (rs.next()) {
				Parametro pr = instantiateParametro(rs);
				list.add(pr);
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
	public void atualiza(Parametro pr) {
		//PreparedStatement st = null;
		CallableStatement st = null;
		try {
			st = conn.prepareCall("{CALL fiscal.pkg_campos_chave_cproc.atualiza(?,?,?,?)}");
			st.setString(1, pr.getCodEmpresa());
			st.setString(2, pr.getCodEstab());
			st.setDate(3,   new java.sql.Date(pr.getDataInicial().getTime()));
			st.setDate(4,   new java.sql.Date(pr.getDataFinal().getTime()));
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
	public void execute(Parametro pr) {
		//PreparedStatement st = null;
		CallableStatement st = null;
		try {
			st = conn.prepareCall("{CALL fiscal.pkg_campos_chave_cproc.executar(?,?,?,?)}");
			st.setString(1, pr.getCodEmpresa());
			st.setString(2, pr.getCodEstab());
			st.setDate(3,   new java.sql.Date(pr.getDataInicial().getTime()));
			st.setDate(4,   new java.sql.Date(pr.getDataFinal().getTime()));
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}
