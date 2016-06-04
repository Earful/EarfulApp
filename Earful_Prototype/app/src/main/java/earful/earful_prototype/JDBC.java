package earful.earful_prototype;
import java.sql.*;
import java.util.ArrayList;

public class JDBC
{
    private String url =  "jdbc:mysql://140.118.155.48:3306/earful";
    private String user = "earful";
    private String password = "FbznaRvgaDQeyvCl";
    private Connection conn = null;
    private SQLException ex = null;
    private Statement queryStmt;
    private ResultSet rs;
    JDBC()
    {
        getConnection();
    }
    JDBC(int a)
    {

    }

    public Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e)
        {
            System.out.println("連接數據庫失敗！"+e.toString());
        }
        Statement q;

        return conn;

    }
    public ArrayList<String> getArticle(String ID)
    {
        ArrayList<String> result = new ArrayList<String>();
        try {
            queryStmt = conn.createStatement();//產生Statement object
            if (queryStmt == null) {
                System.out.println("queryStmt==null : 無法產生Query Statement object");
            } else {
                rs = queryStmt.executeQuery("Select * from article where ID = " + ID);
                {
                    while (rs.next())
                    {
                        result.add(0, rs.getString("ID"));
                        result.add(1, rs.getString("Title"));
                        result.add(2, rs.getString("Content"));
                        result.add(3, rs.getString("Date"));
                        result.add(4, rs.getString("Site"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException :" + e.getMessage());
        }
        //要養成回收資源的好習慣
        finally {
            try {
                if (rs != null)
                    rs.close(); //把 ResultSet object 資源回收
                if (queryStmt != null)
                    queryStmt.close();//把 Statement object 資源回收
                if (conn != null)
                    conn.close();//中斷與database連線
            } catch (Exception vv) {
                vv.printStackTrace();
            }
            queryStmt = null; //把物件資源釋放
            rs = null; //把物件資源釋放
            conn = null; //把物件資源釋放
        }
        return result;
    }
    public ArrayList<String> getAllArticle()
    {
        ArrayList<String> result = new ArrayList<String>();
        try {
            queryStmt = conn.createStatement();//產生Statement object
            if (queryStmt == null) {
                System.out.println("queryStmt==null : 無法產生Query Statement object");
            } else {
                rs = queryStmt.executeQuery("Select * from article order by date ASC");
                int i = 0;
                while (rs.next()) //把所有result loop 出來
                {
                    result.add(i*5+0,rs.getString("ID"));
                    result.add(i*5+1,rs.getString("Title"));
                    result.add(i*5+2,rs.getString("Content"));
                    result.add(i*5+3,rs.getString("Date"));
                    result.add(i*5+4,rs.getString("Site"));

                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException :" + e.getMessage());
        }
        //要養成回收資源的好習慣
        finally {
            try {
                if (rs != null)
                    rs.close(); //把 ResultSet object 資源回收
                if (queryStmt != null)
                    queryStmt.close();//把 Statement object 資源回收
                if (conn != null)
                    conn.close();//中斷與database連線
            } catch (Exception vv) {
                vv.printStackTrace();
            }
            queryStmt = null; //把物件資源釋放
            rs = null; //把物件資源釋放
            conn = null; //把物件資源釋放
        }
        return result;
    }
    public ArrayList<String> getAllreply(String ArticleID)
    {
        ArrayList<String> result = new ArrayList<String>();
        try {
            queryStmt = conn.createStatement();//產生Statement object
            if (queryStmt == null) {
                System.out.println("queryStmt==null : 無法產生Query Statement object");
            } else {
                rs = queryStmt.executeQuery("Select * from response where articleID = "+ ArticleID +" order by date ASC");
                int i = 0;
                while (rs.next()) //把所有result loop 出來
                {
                    result.add(i*5+0,rs.getString("ID"));
                    result.add(i*5+1,rs.getString("Content"));
                    result.add(i*5+2,rs.getString("Date"));


                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException :" + e.getMessage());
        }
        //要養成回收資源的好習慣
        finally {
            try {
                if (rs != null)
                    rs.close(); //把 ResultSet object 資源回收
                if (queryStmt != null)
                    queryStmt.close();//把 Statement object 資源回收
                if (conn != null)
                    conn.close();//中斷與database連線
            } catch (Exception vv) {
                vv.printStackTrace();
            }
            queryStmt = null; //把物件資源釋放
            rs = null; //把物件資源釋放
            conn = null; //把物件資源釋放
        }
        return result;
    }



    public ArrayList<String> InsertQuery(String Query)
    {

        ArrayList<String> result = new ArrayList<String>();
        try {

            queryStmt = conn.createStatement();//產生Statement object

            if (queryStmt == null) {
                System.out.println("queryStmt==null : 無法產生Query Statement object");
            } else {
                queryStmt.executeUpdate(Query);


            }
        } catch (SQLException e) {
            System.out.println("SQLException :" + e.getMessage());
        }
        //要養成回收資源的好習慣
        finally {
            try {
                if (rs != null)
                    rs.close(); //把 ResultSet object 資源回收
                if (queryStmt != null)
                    queryStmt.close();//把 Statement object 資源回收

                if (conn != null)
                    conn.close();//中斷與database連線
            } catch (Exception vv) {
                vv.printStackTrace();
            }
            queryStmt = null; //把物件資源釋放
            rs = null; //把物件資源釋放
            conn = null; //把物件資源釋放
        }
        return result;
    }




}
