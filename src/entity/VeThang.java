package entity;

import java.sql.Date;
import java.sql.Time;

public class VeThang extends VeXe{
    protected String tenKH;
    protected String soDienThoai;

    protected Date ngayDangKy;

    public VeThang() {
    }

    public VeThang(String maVe, String loaiXe, String bienSo, String mauXe, String tenKH, String soDienThoai, Date ngayDangKy) {
        super(maVe, loaiXe, bienSo, mauXe);
        this.tenKH = tenKH;
        this.soDienThoai = soDienThoai;
        this.ngayDangKy = ngayDangKy;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public ViTri getViTri() {
        return viTri;
    }

    public void setViTri(ViTri viTri) {
        this.viTri = viTri;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }
}
