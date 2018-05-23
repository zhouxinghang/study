package com.zhouxinghang.study.dao;

import com.zhouxinghang.study.BaseTest;
import com.zhouxinghang.study.dao.jdbc.JdbcPool;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

/**
 * Created by zhouxinghang on 2018/5/23.
 */

public class ConnPoolTest extends BaseTest{

    @Resource
    private JdbcPool jdbcPool;
    @Test
    public void testSelect() {
        Connection conn = null;
        try {
            conn = jdbcPool.getConnection();
            PreparedStatement pst =conn.prepareStatement("select * from user");
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("account") + "--" + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
