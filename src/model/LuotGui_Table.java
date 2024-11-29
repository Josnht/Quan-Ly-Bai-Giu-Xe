package model;

import entity.VeNgay;
import entity.VeXe;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LuotGui_Table extends AbstractTableModel {

    private List<VeXe> ds;
    String[] headers = {"STT", "Mã Vé", "Biển Số", "Loại Xe", "Màu Xe", "Khu Vực", "Vị Trí", "Ngày Nhận", "Giờ Nhận", "Ngày Trả", "Giờ Trả", "Số Tiền"};

    public LuotGui_Table(List<VeXe> ds) {
        super();
        this.ds = ds;
    }

    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VeXe veXe = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return veXe.getMaVe();
            case 2:
                return veXe.getBienSo();
            case 3:
                return veXe.getLoaiXe();
            case 4:
                return veXe.getMauXe();
            case 5:
                if (veXe.getKhuVuc() != null)
                    return veXe.getKhuVuc().getTenKhuVuc();
                return "";
            case 6:
                if (veXe.getViTri() != null)
                    return veXe.getViTri().getTenViTri();
                return "";
            case 7:
                return veXe.getNgayNhan();
            case 8:
                return veXe.getGioNhan();
            case 9:
                return veXe.getNgayTra();
            case 10:
                return veXe.getGioTra();
            case 11:
                if (veXe instanceof VeNgay) {
                    if (veXe.getLoaiXe().equals("Xe Đạp")) {
                        return 3000;
                    } else if (veXe.getLoaiXe().equals("Xe Máy")) {
                        return 5000;
                    } else
                        return 20000;
                } else
                    return 0;
            default:
                return veXe;
        }
    }
}

