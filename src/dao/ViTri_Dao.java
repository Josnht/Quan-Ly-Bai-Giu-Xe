package dao;

import connection.MyConnection;
import entity.KhuVuc;
import entity.ViTri;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViTri_Dao {
    private Connection con;
    private KhuVuc_Dao khuVuc_dao;

    public ViTri_Dao() {
        con = MyConnection.getInstance().getConnection();
        khuVuc_dao = new KhuVuc_Dao();
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
    public List<ViTri> getLS() {
        List<ViTri> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_VT");
            while(rs.next()){
                ViTri viTri =new ViTri(rs.getString(1),rs.getString(2));
                viTri.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(3)));
                ds.add(viTri);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public List<ViTri> TimKiemMaKV(String ma){
        List<ViTri> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VITRI where MAKV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ViTri viTri = new ViTri(rs.getString(1),rs.getString(2));
                viTri.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(3)));
                list.add(viTri);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public ViTri TimKiemViTriByTen(String ma){
        ViTri viTri = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VITRI where TENVT = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                viTri = new ViTri(rs.getString(1),rs.getString(2));
                viTri.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(3)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return viTri;
    }

    public ViTri TimKiemViTriByMa(String ma){
        ViTri viTri = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VITRI where MAVT = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                viTri = new ViTri(rs.getString(1),rs.getString(2));
                viTri.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(3)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return viTri;
    }
}
