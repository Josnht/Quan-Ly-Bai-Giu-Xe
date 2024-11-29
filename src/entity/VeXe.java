package entity;

import java.sql.Date;
import java.sql.Time;

public class VeXe {
    protected String maVe;
    protected String loaiXe;
    protected String bienSo;
    protected String mauXe;
    protected Date ngayNhan;
    protected Time gioNhan;
    protected Date ngayTra;
    protected Time gioTra;
    protected LoaiVe loaiVe;
    protected ViTri viTri;
    protected KhuVuc khuVuc;

    public VeXe() {
    }

    public VeXe(String maVe, String loaiXe, String bienSo, String mauXe) {
        this.maVe = maVe;
        this.loaiXe = loaiXe;
        this.bienSo = bienSo;
        this.mauXe = mauXe;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getMauXe() {
        return mauXe;
    }

    public void setMauXe(String mauXe) {
        this.mauXe = mauXe;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Time getGioNhan() {
        return gioNhan;
    }

    public void setGioNhan(Time gioNhan) {
        this.gioNhan = gioNhan;
    }

    public LoaiVe getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(LoaiVe loaiVe) {
        this.loaiVe = loaiVe;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Time getGioTra() {
        return gioTra;
    }

    public void setGioTra(Time gioTra) {
        this.gioTra = gioTra;
    }

    public ViTri getViTri() {
        return viTri;
    }

    public void setViTri(ViTri viTri) {
        this.viTri = viTri;
    }

    public KhuVuc getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(KhuVuc khuVuc) {
        this.khuVuc = khuVuc;
    }
}
