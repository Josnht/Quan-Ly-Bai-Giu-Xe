package dao;

import connection.MyConnection;
import entity.VeNgay;
import entity.VeThang;
import entity.VeXe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeXe_Dao {
        private Connection con;

        public VeXe_Dao() {
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
        public List<VeXe> getLS() {
            List<VeXe> ds = new ArrayList<>();
            try {
                ResultSet rs = getResultSet("select_VN");
                while(rs.next()){
                    VeXe veXe =new VeXe(rs.getString(1),rs.getString(2), rs.getString(3),
                            rs.getString(4));
                    veXe.setNgayNhan(rs.getDate(5));
                    veXe.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veXe.setNgayTra(rs.getDate(7));
                        veXe.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veXe.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    veXe.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    if (rs.getString(11) != null)
                        veXe.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));
                    ds.add(veXe);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ds;
        }
        public VeNgay TimKiemMa(String ma){
            VeNgay veNgay = null;
            try{
                PreparedStatement stmt = con.prepareStatement("select * from VENGAY where MAVE = ?");
                stmt.setString(1,ma);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    veNgay = new VeNgay(rs.getString(1),rs.getString(2), rs.getString(3),
                            rs.getString(4));
                    veNgay.setNgayNhan(rs.getDate(5));
                    veNgay.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veNgay.setNgayTra(rs.getDate(7));
                        veNgay.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veNgay.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    veNgay.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    if (rs.getString(11) != null)
                        veNgay.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));

                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return veNgay;
        }

        public boolean addveNgay(VeNgay veNgay) {
            try {
                PreparedStatement cnAdd = con.prepareStatement("INSERT INTO VeNgay ([LOAIXE],[BIENSO],[MAUXE],[NGAYNHAN],[GIONHAN]," +
                        "[NGAYTRA],[GIOTRA],[MALV], [MAKV], [MAVT]) VALUES(?,?,?,?,?,?,?,?)");
                cnAdd.setString(1,veNgay.getLoaiXe());
                if (veNgay.getLoaiXe().equals("Xe Đạp")) {
                    cnAdd.setString(2, "Không");
                } else {
                    cnAdd.setString(2, veNgay.getBienSo());
                }
                cnAdd.setString(3,veNgay.getMauXe());
                cnAdd.setDate(4,veNgay.getNgayNhan());
                cnAdd.setString(5,String.valueOf(veNgay.getGioNhan()));
                cnAdd.setDate(6,veNgay.getNgayTra());
                cnAdd.setString(7, String.valueOf(veNgay.getGioTra()));
                cnAdd.setString(8,"LV001");
                cnAdd.setString(9,veNgay.getKhuVuc().getMaKhuVuc());
                cnAdd.setString(10,veNgay.getViTri().getMaViTri());
                int n = cnAdd.executeUpdate();
                if(n > 0)
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

        public boolean deleteVeNgay(String maVe) {
            try {
                PreparedStatement stmt = con.prepareStatement("delete from VeNgay where MAVE = ?");
                stmt.setString(1, maVe);
                int n = stmt.executeUpdate();
                if(n > 0)
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

        public boolean updateVeNgay(VeNgay veNgay) {
            String sql = "update VeThang set NGAYNHAN = ?, GIONHAN = ? ,NGAYTRA = ? where MAVE = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setDate(1,veNgay.getNgayNhan());
                stmt.setString(2,String.valueOf(veNgay.getGioNhan()));
                stmt.setDate(3,veNgay.getNgayTra());
                stmt.setString(4,String.valueOf(veNgay.getGioTra()));
                stmt.setString(5,veNgay.getMaVe());

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
        public VeNgay TimKiemBienSo(String bienSo){
            VeNgay veNgay = null;
            try{
                PreparedStatement stmt = con.prepareStatement("select * from VeNgay where BIENSO = ?");
                stmt.setString(1,bienSo);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    veNgay = new VeNgay(rs.getString(1),rs.getString(2), rs.getString(3),
                            rs.getString(4));
                    veNgay.setNgayNhan(rs.getDate(5));
                    veNgay.setGioNhan(Time.valueOf(rs.getString(6)));
                    if (rs.getString(7) != null) {
                        veNgay.setNgayTra(rs.getDate(7));
                        veNgay.setGioTra(Time.valueOf(rs.getString(8)));
                    }
                    LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                    veNgay.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                    KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                    veNgay.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                    ViTri_Dao viTri_dao = new ViTri_Dao();
                    if (rs.getString(11) != null)
                        veNgay.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));

                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return veNgay;
        }

    public List<VeNgay> TimKiemLoaiXe(String loaiXe){
        List<VeNgay> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VENGAY where MAVE = ?");
            stmt.setString(1,loaiXe);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeNgay veNgay = new VeNgay(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4));
                veNgay.setNgayNhan(rs.getDate(5));
                veNgay.setGioNhan(Time.valueOf(rs.getString(6)));
                if (rs.getString(7) != null) {
                    veNgay.setNgayTra(rs.getDate(7));
                    veNgay.setGioTra(Time.valueOf(rs.getString(8)));
                }
                LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                veNgay.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                veNgay.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                ViTri_Dao viTri_dao = new ViTri_Dao();
                if (rs.getString(11) != null)
                    veNgay.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));

                list.add(veNgay);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<VeNgay> TimKiemThang(int thang){
        List<VeNgay> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VENGAY vn where MONTH(vn.NGAYNHAN) = ?");
            stmt.setInt(1,thang);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeNgay veNgay = new VeNgay(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4));
                veNgay.setNgayNhan(rs.getDate(5));
                veNgay.setGioNhan(Time.valueOf(rs.getString(6)));
                if (rs.getString(7) != null) {
                    veNgay.setNgayTra(rs.getDate(7));
                    veNgay.setGioTra(Time.valueOf(rs.getString(8)));
                }
                LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                veNgay.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                veNgay.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                ViTri_Dao viTri_dao = new ViTri_Dao();
                if (rs.getString(11) != null)
                    veNgay.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));

                list.add(veNgay);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<VeNgay> TimKiemNgay(Date ngay){
        List<VeNgay> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from VENGAY vn where NGAYNHAN = ?");
            stmt.setDate(1,ngay);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                VeNgay veNgay = new VeNgay(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4));
                veNgay.setNgayNhan(rs.getDate(5));
                veNgay.setGioNhan(Time.valueOf(rs.getString(6)));
                if (rs.getString(7) != null) {
                    veNgay.setNgayTra(rs.getDate(7));
                    veNgay.setGioTra(Time.valueOf(rs.getString(8)));
                }
                LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
                veNgay.setLoaiVe(loaiVe_dao.TimKiemMa(rs.getString(9)));
                KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
                veNgay.setKhuVuc(khuVuc_dao.TimKiemMa(rs.getString(10)));
                ViTri_Dao viTri_dao = new ViTri_Dao();
                if (rs.getString(11) != null)
                    veNgay.setViTri(viTri_dao.TimKiemViTriByMa(rs.getString(11)));

                list.add(veNgay);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}

