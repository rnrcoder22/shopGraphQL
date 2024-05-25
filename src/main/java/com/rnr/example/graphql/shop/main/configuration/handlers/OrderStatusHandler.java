package com.rnr.example.graphql.shop.main.configuration.handlers;

import com.rnr.example.graphql.shop.main.model.OrderStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to handle the OrderStatus enum for MyBatis
 */
public class OrderStatusHandler extends BaseTypeHandler<OrderStatus> {

    @Override 
    public void setNonNullParameter(PreparedStatement ps, int i, OrderStatus parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override 
    public OrderStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return OrderStatus.lookUp(rs.getString(columnName));
    }

    @Override 
    public OrderStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return OrderStatus.lookUp(rs.getString(columnIndex));
    }

    @Override 
    public OrderStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return OrderStatus.lookUp(cs.getString(columnIndex));
    }
}
