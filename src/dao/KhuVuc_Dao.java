package dao;

import connection.MyConnection;
import entity.KhuVuc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhuVuc_Dao {
    private Connection con;

    public KhuVuc_Dao() {
        con = MyConnection.getInstance().getConnection();
    }
    public ResultSet getResultSet(String StoreName)throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName +"}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }
    public List<KhuVuc> getLS() {
        List<KhuVuc> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_KV");
            while(rs.next()){
                KhuVuc khuVuc =new KhuVuc(rs.getString(1),rs.getString(2));
                ds.add(khuVuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public KhuVuc TimKiemMa(String ma){
        KhuVuc khuVuc = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KHUVUC where MAKV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                khuVuc = new KhuVuc(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return khuVuc;
    }

    public String TimKiemTen(String ten){
        KhuVuc khuVuc = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KHUVUC where TENKV = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                khuVuc = new KhuVuc(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return khuVuc.getMaKhuVuc();
    }
}
