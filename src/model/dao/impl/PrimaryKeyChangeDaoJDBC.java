package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.PrimaryKeyChangeDao;
import model.entities.Parametro;
import model.entities.PrimaryKeyChange;

public class PrimaryKeyChangeDaoJDBC implements PrimaryKeyChangeDao {

	private Connection conn;
	public PrimaryKeyChangeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public PrimaryKeyChange findById(String cod_empresa,
			                         String cod_estab,
			                         Date data_fiscal,
			                         Date data_emissao,
			                         String movto_e_s,
			                         String norm_dev,
			                         String cod_fis_jur,
			                         String num_docfis,
			                         String serie_docfis) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select * from fiscal.tab_aux_ajuste_campos_chave " + 
                    " where cod_empresa                  = ?   " +
                    "   and cod_estab                    = ?   " +
                    "   and data_fiscal                  = ?   " +
                    "   and data_emissao                 = ?   " +
                    "   and movto_e_s                    = ?   " +
                    "   and norm_dev                     = ?   " +
                    "   and cod_fis_jur                  = ?   " +
                    "   and num_docfis                   = ?   " +
                    "   and serie_docfis                 = ?   " );
			st.setString(1, cod_empresa);
			st.setString(2, cod_estab);
			st.setDate(3,   new java.sql.Date(data_fiscal.getTime()));
			st.setDate(4,   new java.sql.Date(data_emissao.getTime()));
			st.setString(5, movto_e_s);
			st.setString(6, norm_dev);
			st.setString(7, cod_fis_jur);
			st.setString(8, num_docfis);
			st.setString(9, serie_docfis);
			
			rs = st.executeQuery();
			if (rs.next()) {
				PrimaryKeyChange obj = new PrimaryKeyChange();
				obj.setCod_empresa(rs.getString("cod_empresa"));
				obj.setCod_estab(rs.getString("cod_estab"));
				obj.setData_fiscal(new java.util.Date(rs.getDate("data_fiscal").getTime()));
				obj.setData_emissao(new java.util.Date(rs.getDate("data_emissao").getTime()));
				obj.setMovto_e_s(rs.getString("movto_e_s"));
				obj.setNorm_dev(rs.getString("norm_dev"));
				obj.setCod_fis_jur(rs.getString("cod_fis_jur"));
				obj.setNum_docfis(rs.getString("num_docfis"));
				obj.setSerie_docfis(rs.getString("serie_docfis"));
				obj.setSub_serie_docfis(rs.getString("sub_serie_docfis"));
				obj.setCod_empresa_aj(rs.getString("cod_empresa_aj"));
				obj.setCod_estab_aj(rs.getString("cod_estab_aj"));
				obj.setData_fiscal_aj(new java.util.Date(rs.getDate("data_fiscal_aj").getTime()));
				obj.setData_emissao_aj(new java.util.Date(rs.getDate("data_emissao_aj").getTime()));
				obj.setMovto_e_s_aj(rs.getString("movto_e_s_aj"));
				obj.setNorm_dev_aj(rs.getString("norm_dev_aj"));
				obj.setCod_fis_jur_aj(rs.getString("cod_fis_jur_aj"));
				obj.setNum_docfis_aj(rs.getString("num_docfis_aj"));
				obj.setSerie_docfis_aj(rs.getString("serie_docfis_aj"));
				obj.setSub_serie_docfis_aj(rs.getString("sub_serie_docfis_aj"));
				obj.setCod_class_docfis_aj(rs.getString("cod_class_docfis_aj"));
				obj.setCod_docto_aj(rs.getString("cod_docto_aj"));
				obj.setCod_modelo_aj(rs.getString("cod_modelo_aj"));
				obj.setCod_modelo_cotepe_aj(rs.getString("cod_modelo_cotepe_aj"));
				obj.setNum_autentic_nfe_aj(rs.getString("num_autentic_nfe_aj"));
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
	
	private PrimaryKeyChange instantiatePrimaryKeyChange(ResultSet rs) throws SQLException {
		PrimaryKeyChange obj = new PrimaryKeyChange();
		obj.setCod_empresa(rs.getString("cod_empresa"));
		obj.setCod_estab(rs.getString("cod_estab"));
		obj.setData_fiscal(new java.util.Date(rs.getTimestamp("data_fiscal").getTime()));
		obj.setData_emissao(new java.util.Date(rs.getTimestamp("data_emissao").getTime()));
		obj.setMovto_e_s(rs.getString("movto_e_s"));
		obj.setNorm_dev(rs.getString("norm_dev"));
		obj.setCod_fis_jur(rs.getString("cod_fis_jur"));
		obj.setNum_docfis(rs.getString("num_docfis"));
		obj.setSerie_docfis(rs.getString("serie_docfis"));
		obj.setSub_serie_docfis(rs.getString("sub_serie_docfis"));
		obj.setCod_empresa_aj(rs.getString("cod_empresa_aj"));
		obj.setCod_estab_aj(rs.getString("cod_estab_aj"));
		if (rs.getTimestamp("data_fiscal_aj") == null) {
			obj.setData_fiscal_aj(null);
		} else {
			obj.setData_fiscal_aj(new java.util.Date(rs.getTimestamp("data_fiscal_aj").getTime()));
		}
		if (rs.getTimestamp("data_emissao_aj") == null) {
			obj.setData_emissao_aj(null);
		} else {
			obj.setData_emissao_aj(new java.util.Date(rs.getTimestamp("data_emissao_aj").getTime()));
		}
		obj.setMovto_e_s_aj(rs.getString("movto_e_s_aj"));
		obj.setNorm_dev_aj(rs.getString("norm_dev_aj"));
		obj.setCod_fis_jur_aj(rs.getString("cod_fis_jur_aj"));
		obj.setNum_docfis_aj(rs.getString("num_docfis_aj"));
		obj.setSerie_docfis_aj(rs.getString("serie_docfis_aj"));
		obj.setSub_serie_docfis_aj(rs.getString("sub_serie_docfis_aj"));
		obj.setCod_class_docfis_aj(rs.getString("cod_class_docfis_aj"));
		obj.setCod_docto_aj(rs.getString("cod_docto_aj"));
		obj.setCod_modelo_aj(rs.getString("cod_modelo_aj"));
		obj.setCod_modelo_cotepe_aj(rs.getString("cod_modelo_cotepe_aj"));
		obj.setNum_autentic_nfe_aj(rs.getString("num_autentic_nfe_aj"));
		return obj;
	}

	@Override
	public List<PrimaryKeyChange> findAll(Parametro pr) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select cod_empresa,           " + 
					"		cod_estab,             " + 
					"		data_fiscal,           " + 
					"		data_emissao,          " + 
					"		movto_e_s,             " + 
					"		norm_dev,              " + 
					"		cod_fis_jur,           " + 
					"		num_docfis,            " + 
					"		serie_docfis,          " + 
					"		sub_serie_docfis,      " + 
					"		cod_empresa_aj,        " + 
					"		cod_estab_aj,          " + 
					"		data_fiscal_aj,        " + 
					"		data_emissao_aj,       " + 
					"		movto_e_s_aj,          " + 
					"		norm_dev_aj,           " + 
					"		cod_fis_jur_aj,        " + 
					"		num_docfis_aj,         " + 
					"		serie_docfis_aj,       " + 
					"		sub_serie_docfis_aj,   " + 
					"		cod_class_docfis_aj,   " + 
					"		cod_docto_aj,          " + 
					"		cod_modelo_aj,         " + 
					"		cod_modelo_cotepe_aj,  " + 
					"		num_autentic_nfe_aj    " +
					"  from fiscal.tab_aux_ajuste_campos_chave " +
					" where cod_empresa = ?        " +
					"   and cod_estab   = ?        " +
					" order by cod_estab");
			st.setString(1, pr.getCodEmpresa());
			st.setString(2, pr.getCodEstab());
			rs = st.executeQuery();
			List<PrimaryKeyChange> list = new ArrayList<>();
			while (rs.next()) {
				PrimaryKeyChange obj = instantiatePrimaryKeyChange(rs);
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
	public void update(PrimaryKeyChange obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
			          "update fiscal.tab_aux_ajuste_campos_chave " +
			                  "   set cod_empresa_aj               = ? , " +
			                  "       cod_estab_aj                 = ? , " +
			                  "       data_fiscal_aj               = ? , " +
			                  "       data_emissao_aj              = ? , " +
			                  "       movto_e_s_aj                 = ? , " +
			                  "       norm_dev_aj                  = ? , " +
			                  "       cod_fis_jur_aj               = ? , " +
			                  "       num_docfis_aj                = ? , " +
			                  "       serie_docfis_aj              = ? , " +
			                  "       sub_serie_docfis_aj          = ? , " +
			                  "       cod_class_docfis_aj          = ? , " +
			                  "       cod_docto_aj                 = ? , " +
			                  "       cod_modelo_aj                = ? , " +
			                  "       cod_modelo_cotepe_aj         = ? , " +
			                  "       num_autentic_nfe_aj          = ?   " +
			                  " where cod_empresa                  = ?   " +
			                  "   and cod_estab                    = ?   " +
			                  "   and data_fiscal                  = ?   " +
			                  "   and data_emissao                 = ?   " +
			                  "   and movto_e_s                    = ?   " +
			                  "   and norm_dev                     = ?   " +
			                  "   and cod_fis_jur                  = ?   " +
			                  "   and num_docfis                   = ?   " +
			                  "   and serie_docfis                 = ?   " );
			st.setString(1,  obj.getCod_empresa_aj());
			st.setString(2,  obj.getCod_estab_aj());
			if (obj.getData_fiscal_aj() == null) {
				st.setDate(3, null);
			} else {
				st.setDate(3, new java.sql.Date(obj.getData_fiscal_aj().getTime()));
			}
			if (obj.getData_emissao_aj() == null) {
				st.setDate(4, null);
			} else {
				st.setDate(4, new java.sql.Date(obj.getData_emissao_aj().getTime()));
			}
			st.setString(5,  obj.getMovto_e_s_aj());
			st.setString(6,  obj.getNorm_dev_aj());
			st.setString(7,  obj.getCod_fis_jur_aj());
			st.setString(8,  obj.getNum_docfis_aj());
			st.setString(9,  obj.getSerie_docfis_aj());
			st.setString(10, obj.getSub_serie_docfis_aj());
			st.setString(11, obj.getCod_class_docfis_aj());
			st.setString(12, obj.getCod_docto_aj());
			st.setString(13, obj.getCod_modelo_aj());
			st.setString(14, obj.getCod_modelo_cotepe_aj());
			st.setString(15, obj.getNum_autentic_nfe_aj());
			st.setString(16, obj.getCod_empresa());
			st.setString(17, obj.getCod_estab());
			st.setDate(18,   new java.sql.Date(obj.getData_fiscal().getTime()));
			st.setDate(19,   new java.sql.Date(obj.getData_emissao().getTime()));
			st.setString(20, obj.getMovto_e_s());
			st.setString(21, obj.getNorm_dev());
			st.setString(22, obj.getCod_fis_jur());
			st.setString(23, obj.getNum_docfis());
			st.setString(24, obj.getSerie_docfis());
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
	public void deleteById(PrimaryKeyChange obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("delete from fiscal.tab_aux_ajuste_campos_chave " +
	                                   " where cod_empresa                  = ?   " +
	                                   "   and cod_estab                    = ?   " +
	                                   "   and data_fiscal                  = ?   " +
	                                   "   and data_emissao                 = ?   " +
	                                   "   and movto_e_s                    = ?   " +
	                                   "   and norm_dev                     = ?   " +
	                                   "   and cod_fis_jur                  = ?   " +
	                                   "   and num_docfis                   = ?   " +
	                                   "   and serie_docfis                 = ?   " );
			st.setString(1, obj.getCod_empresa());
			st.setString(2, obj.getCod_estab());
			st.setDate(3,   new java.sql.Date(obj.getData_fiscal().getTime()));
			st.setDate(4,   new java.sql.Date(obj.getData_emissao().getTime()));
			st.setString(5, obj.getMovto_e_s());
			st.setString(6, obj.getNorm_dev());
			st.setString(7, obj.getCod_fis_jur());
			st.setString(8, obj.getNum_docfis());
			st.setString(9, obj.getSerie_docfis());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
	
	
//	@Override
//	public void atualiza(Loja lj) {
//		PreparedStatement st = null;		
//		try {
//			st = conn.prepareStatement(
//					"delete from fiscal.tab_aux_ajuste_campos_chave                                                                          " +
//					"      where x07.cod_empresa       = ?                                                                                   " +
//					"        and x07.cod_estab         = ?                                                                                   " +
//					"insert into fiscal.tab_aux_ajuste_campos_chave                                                                          " +
//					"     select x07.cod_empresa,                                                                                            " +
//					"            x07.cod_estab,                                                                                              " +
//					"            x07.data_fiscal,                                                                                            " +
//					"            x07.data_emissao,                                                                                           " +
//					"            x07.movto_e_s,                                                                                              " +
//					"            x07.norm_dev,                                                                                               " +
//					"            p.cod_fis_jur,                                                                                              " +
//					"            x07.num_docfis,                                                                                             " +
//					"            x07.serie_docfis,                                                                                           " +
//					"            x07.sub_serie_docfis,                                                                                       " +
//					"            '' as cod_empresa_aj,                                                                                       " +
//					"            '' as cod_estab_aj,                                                                                         " +
//					"            '' as data_fiscal_aj,                                                                                       " +
//					"            '' as data_emissao_aj,                                                                                      " +
//					"            '' as movto_e_s_aj,                                                                                         " +
//					"            '' as norm_dev_aj,                                                                                          " +
//					"            '' as cod_fis_jur_aj,                                                                                       " +
//					"            '' as num_docfis_aj,                                                                                        " +
//					"            '' as serie_docfis_aj,                                                                                      " +
//					"            '' as sub_serie_docfis_aj,                                                                                  " +
//					"            '' as cod_class_docfis_aj,                                                                                  " +
//					"            '' as cod_docto_aj,                                                                                         " +
//					"            '' as cod_modelo_aj,                                                                                        " +
//					"            '' as cod_modelo_cotepe_aj,                                                                                 " +
//					"            '' as num_autentic_nfe_aj                                                                                   " +
//					"       from msaf.x07_docto_fiscal x07                                                                                   " +
//					"      inner join msaf.x04_pessoa_fis_jur p                                                                              " +
//					"         on p.ident_fis_jur       = x07.ident_fis_jur                                                                   " +
//					"      inner join msaf.x2024_modelo_docto x2024                                                                          " +
//					"         on x2024.ident_modelo    = x07.ident_modelo                                                                    " +
//					"      where x07.cod_empresa       = ?                                                                                   " +
//					"        and x07.cod_estab         = ?                                                                                   " +
//					"        and x07.dsc_reservado1    = 'ORACLE'                                                                            " +
//					"        and x07.cod_class_doc_fis = '1'                                                                                 " +
//					"        and x07.data_fiscal       between to_date('01/04/2020', 'dd/mm/rrrr') and to_date('30/04/2020', 'dd/mm/rrrr')   " +
//					"        and not exists (select 1 from fiscal.tab_aux_ajuste_campos_chave x                                              " +
//					"                     where x.cod_empresa       = x07.cod_empresa                                                        " +
//					"                       and x.cod_estab         = x07.cod_estab                                                          " +
//					"                       and x.data_fiscal       = x07.data_fiscal                                                        " +
//					"                       and x.movto_e_s         = x07.movto_e_s                                                          " +
//					"                       and x.norm_dev          = x07.norm_dev                                                           " +
//					"                       and x.cod_fis_jur       = p.cod_fis_jur                                                          " +
//					"                       and x.num_docfis        = x07.num_docfis                                                         " +
//					"                       and x.serie_docfis      = x07.serie_docfis)                                                      " +
//					"        and (lpad(nvl(trim(substr(x07.num_autentic_nfe,7,14)),'0'),14,'0') != lpad(trim(p.cpf_cgc),14,'0') or           " +
//					"             lpad(nvl(trim(substr(x07.num_autentic_nfe,23,3)),'0'),3,'0')  != lpad(trim(x07.serie_docfis),3,'0') or     " +
//					"             lpad(nvl(trim(substr(x07.num_autentic_nfe,26,9)),'0'),9,'0')  != lpad(trim(x07.num_docfis),9,'0'))         " );
//			st.setString(1, lj.getCod_empresa());
//			st.setString(2, lj.getCod_estab());
//			st.executeQuery();
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//		}
//	}


	
}