package top.luoqiz.file.generator.create.utils;


import top.luoqiz.file.generator.create.model.ConnectModel;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;

public class DBSqlDeal {

    private static Connection ct = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    private static Statement cs;

    public static ArrayList<Object> executeSql(String sql, ConnectModel connectModel) {
        ArrayList<Object> results = new ArrayList<Object>();
        try {
            ct = DBSqlDeal.getCon(connectModel);
            cs = ct.createStatement();
            rs = cs.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过rsmd获取该结果集有多少列
            int columnNum = rsmd.getColumnCount();
            //循环取出数据，并封装到arraylist中
            while (rs.next()) {
                Object[] objects = new Object[columnNum];
                for (int i = 0; i < objects.length; i++) {
                    objects[i] = rs.getObject(i + 1);
                }
                results.add(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBSqlDeal.closecon(ct, ps, rs, cs);
        return results;
    }

    /**
     * @param connectModel
     * @throws NamingException
     * @throws SQLException
     */

    public static Connection getCon(ConnectModel connectModel) {
        Connection con = null;
        try {
            Class.forName(connectModel.getDbDriverName());
            if (connectModel.getDbUserName() != null) {
                String connectDB = connectModel.getDbConnetAddr();
                con = DriverManager.getConnection(connectDB, connectModel.getDbUserName(), connectModel.getDbUserPWD());
            } else {
                con = DriverManager.getConnection(connectModel.getDbConnetAddr());
            }
        } catch (Exception e) {

//			 System.out.println("shibSQL驱动程序");
            e.printStackTrace();
        }
        return con;
    }

    public static void closecon(Connection con, PreparedStatement ps, ResultSet rs, Statement cs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
            if (cs != null) {
                cs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
