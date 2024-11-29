package entity;

public class ViTri {
    protected String maViTri;
    protected String tenViTri;
    protected KhuVuc khuVuc;

    public ViTri(String maViTri) {
        this.maViTri = maViTri;
    }

    public ViTri(String maViTri, String tenViTri) {
        this.maViTri = maViTri;
        this.tenViTri = tenViTri;
    }

    public String getMaViTri() {
        return maViTri;
    }

    public void setMaViTri(String maViTri) {
        this.maViTri = maViTri;
    }

    public String getTenViTri() {
        return tenViTri;
    }

    public void setTenViTri(String tenViTri) {
        this.tenViTri = tenViTri;
    }

    public KhuVuc getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(KhuVuc khuVuc) {
        this.khuVuc = khuVuc;
    }
}
