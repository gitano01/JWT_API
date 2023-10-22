package com.sec.dbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


public class PoolConexion {
	
	private static BasicDataSource dataSource = null;
	
	private static DataSource getDataSource() {
		if(dataSource == null) {
			
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUsername("postgres");
			dataSource.setPassword("postgres");
			dataSource.setUrl("jdbc:postgresql://localhost:5432/tokens");
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(15);
			dataSource.setMaxTotal(20);
			dataSource.setMaxWaitMillis(5000);		}
		
		return dataSource;
	}
	
	public static Connection getConnection()throws SQLException{
		return getDataSource().getConnection();
	}
	
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs) throws Exception{
		try
		{
			if( rs != null )
				rs.close();
			
			if( ps != null)
				ps.close();
			
			if(conn!=null && !conn.isClosed())
				conn.close();
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}	
	}
}
