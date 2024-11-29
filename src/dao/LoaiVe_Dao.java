package dao;

import connection.MyConnection;
import entity.LoaiVe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiVe_Dao {

    private Connection con;

    public LoaiVe_Dao() {
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
    public List<LoaiVe> getLS() {
        List<LoaiVe> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_LV");
            while(rs.next()){
                LoaiVe loaiVe =new LoaiVe(rs.getString(1),rs.getString(2));
                ds.add(loaiVe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public LoaiVe TimKiemMa(String ma){
        LoaiVe loaiVe = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LOAIVE where MALV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                loaiVe = new LoaiVe(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return loaiVe;
    }
}
