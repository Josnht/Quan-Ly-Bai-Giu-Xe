package dao;

import connection.MyConnection;
import entity.VeThang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeThang_Dao {
    private Connection con;

    public VeThang_Dao() {
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
    public List<VeThang> getLS() {
        List<VeThang> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_VeThang");
            while(rs.next()){
                VeThang veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                }
                ds.add(veThang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public VeThang TimKiemMa(String ma){
        VeThang veThang = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VETHANG where MAVE = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return veThang;
    }

    public boolean addVeThang(VeThang veThang) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO VeThang ([LOAIXE],[BIENSO],[MAUXE],[NGAYNHAN],[GIONHAN]," +
                    "[NGAYTRA],[GIOTRA],[MALV],[TENKH],[SODIENTHOAI], [NGAYDANGKY]) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            cnAdd.setString(1,veThang.getLoaiXe());
            if (veThang.getLoaiXe().equals("Xe Đạp")) {
                cnAdd.setString(2, "Không");
            } else {
                cnAdd.setString(2, veThang.getBienSo());
            }
            cnAdd.setString(3,veThang.getMauXe());
            cnAdd.setDate(4,veThang.getNgayNhan());
            cnAdd.setString(5,String.valueOf(veThang.getGioNhan()));
            cnAdd.setDate(6,veThang.getNgayTra());
            cnAdd.setString(7, String.valueOf(veThang.getGioTra()));
            cnAdd.setString(8,"LV002");
            cnAdd.setString(9,veThang.getTenKH());
            cnAdd.setString(10,veThang.getSoDienThoai());
            cnAdd.setDate(11,veThang.getNgayDangKy());

            int n = cnAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteVeThang(String maVe) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from VeThang where MAVE = ?");
            stmt.setString(1, maVe);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateVeThang(VeThang veThang) {
        String sql = "update VeThang set NGAYNHAN = ?, GIONHAN = ? , NGAYTRA = ?, GIOTRA = ?, MAKV = ?, MAVT = ? where MAVE = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1,veThang.getNgayNhan());
            stmt.setString(2,String.valueOf(veThang.getGioNhan()));
            stmt.setDate(3,veThang.getNgayTra());
            stmt.setString(4,String.valueOf(veThang.getGioTra()));
            stmt.setString(5,veThang.getKhuVuc().getMaKhuVuc());
            if (veThang.getLoaiXe().equals("Xe Oto"))
                stmt.setString(6,veThang.getViTri().getMaViTri());
            else
                stmt.setString(6,"");
            stmt.setString(7,veThang.getMaVe());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTTVeThang(VeThang veThang) {
        String sql = "update VeThang set LOAIXE = ?, TENKH = ? , SODIENTHOAI = ?, BIENSO = ?, MAUXE = ? where MAVE = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,veThang.getLoaiXe());
            stmt.setString(2,veThang.getTenKH());
            stmt.setString(3,veThang.getSoDienThoai());
            if (veThang.getLoaiXe().equals("Xe Đạp")) {
                stmt.setString(4,"Không");
            } else {
                stmt.setString(4,veThang.getBienSo());
            }
            stmt.setString(5,veThang.getMauXe());
            stmt.setString(6,veThang.getMaVe());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public VeThang TimKiemBienSo(String bienSo){
        VeThang veThang = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VETHANG where BIENSO = ?");
            stmt.setString(1,bienSo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return veThang;
    }

    public List<VeThang> TimKiemLoaiXe(String loaiXe){
        List<VeThang> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VETHANG where LOAIXE = ?");
            stmt.setString(1,loaiXe);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeThang veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                    list.add(veThang);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<VeThang> TimKiemThangDK(int thang){
        List<VeThang> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VETHANG vt where MONTH(cn.NGAYDANGKY) = ?");
            stmt.setInt(1,thang);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeThang veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    if (veThang.getKhuVuc() != null)
                        veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                    list.add(veThang);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<VeThang> TimKiemNgayDK(Date ngay){
        List<VeThang> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VETHANG vt where NGAYDANGKY = ?");
            stmt.setDate(1,ngay);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeThang veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    if (veThang.getKhuVuc() != null)
                        veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                    list.add(veThang);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<VeThang> TimKiemThangGui(int thang){
        List<VeThang> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VETHANG vt where MONTH(vt.NGAYNHAN) = ?");
            stmt.setInt(1,thang);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeThang veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                    list.add(veThang);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<VeThang> TimKiemNgayGui(Date ngay){
        List<VeThang> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VETHANG vt where NGAYNHAN = ?");
            stmt.setDate(1,ngay);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeThang veThang = new VeThang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(12),rs.getString(13), rs.getDate(14));
                if (rs.getString(5) != null) {
                    veThang.setNgayNhan(rs.getDate(5));
                    veThang.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veThang.setNgayTra(rs.getDate(7));
                        veThang.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veThang.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    veThang.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    if (veThang.getLoaiXe().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    }
                    list.add(veThang);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
