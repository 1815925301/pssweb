package com.sinosoft.business.base;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.mysql.jdbc.Blob;
/**
 * 解决mybaits 转换blob的时候乱码问题
 * 并将此类配置在mybaits的xml文件中
 * @author zzq
 *
 */
public class MyBlobTypeHandler extends BaseTypeHandler<Object> {
	
	private static final String DEFAULT_CHARSET = "utf-8";
	
	@Override
	public Object getNullableResult(ResultSet arg0, String arg1)
			throws SQLException {
		Blob blob = (Blob) arg0.getBlob(arg1);
        byte[] returnValue = null;
        if (null != blob&&blob.length()>1) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }else{
        	return "";
        }
        try {
           //把byte转化成string
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
	}


	@Override
	public Object getNullableResult(CallableStatement arg0, int arg1)
			throws SQLException {
		    Blob blob = (Blob) arg0.getBlob(arg1);
	        byte[] returnValue = null;
	        if (null != blob) {
	            returnValue = blob.getBytes(1, (int) blob.length());
	        }
	        try {
	            return new String(returnValue, DEFAULT_CHARSET);
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("Blob Encoding Error!");
	        }
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1,
			Object arg2, JdbcType arg3) throws SQLException {
			ByteArrayInputStream bis;
	        try {
	            //把String转化成byte流
	            bis = new ByteArrayInputStream(((String)arg2).getBytes(DEFAULT_CHARSET));
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("Blob Encoding Error!");
	        }   
	        arg0.setBinaryStream(arg1, bis, ((String)arg2).length());
	}


	@Override
	public Object getNullableResult(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
