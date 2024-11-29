package entity;

public class LoaiVe {
    protected String maLoaiVe;
    protected String tenLoaiVe;

    public LoaiVe() {
    }

    public LoaiVe(String maLoaiVe, String tenLoaiVe) {
        this.maLoaiVe = maLoaiVe;
        this.tenLoaiVe = tenLoaiVe;
    }

    public String getMaLoaiVe() {
        return maLoaiVe;
    }

    public void setMaLoaiVe(String maLoaiVe) {
        this.maLoaiVe = maLoaiVe;
    }

    public String getTenLoaiVe() {
        return tenLoaiVe;
    }

    public void setTenLoaiVe(String tenLoaiVe) {
        this.tenLoaiVe = tenLoaiVe;
    }
}
