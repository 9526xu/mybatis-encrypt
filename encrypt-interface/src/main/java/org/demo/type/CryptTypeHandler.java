package org.demo.type;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.demo.alias.CryptType;
import org.springframework.util.Base64Utils;

import java.nio.charset.Charset;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
这里加解密方法简单使用 base64，可以相应替换成其他方法即可
 */
@MappedTypes(CryptType.class)
@Slf4j
public class CryptTypeHandler extends BaseTypeHandler<String> {



    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        try {
            String encryptStr = Base64Utils.encodeToString(s.getBytes(Charset.defaultCharset()));
            preparedStatement.setString(i, encryptStr);
        } catch (Exception e) {
            log.error("加密失败", e);
            // 加密失败使用 原始数据
            preparedStatement.setString(i, s);
        }


    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return decrypt(resultSet.getString(columnName));
    }

    /**
     * 解密相关的参数，如果解密失败
     * @param param
     * @return
     */
    private String decrypt(String param) {
        // 判断返回结果是否是密文，减少解密失败的概率
        if (!isEncrypt(param)) {
            return param;
        }
        try {
            byte[] bytes = Base64Utils.decodeFromString(param);
            String result = new String(bytes, Charset.defaultCharset());
            return result;
        } catch (Exception e) {
            log.error("加密失败", e);
            return param;
        }
    }

    /**
     * 判断字符串是否是密文
     * @param param
     * @return
     */
    private boolean isEncrypt(String param) {
        // 可以使用字符串长度，是否包含中文判断是否是密文
        return true;
    }


    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return decrypt(resultSet.getString(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return decrypt(callableStatement.getString(columnIndex));
    }


}
