package model;

import entity.VeThang;
import entity.VeXe;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VeThang_Table extends AbstractTableModel {

    private List<VeXe> ds;
    String [] headers = {"STT", "Mã Vé", "Tên Khách Hàng","Số Điện Thoại","Biển Số","Loại Xe","Màu Xe","Ngày Đăng Ký","Thành Tiền"};

    public VeThang_Table(List<VeXe> ds){
        super();
        this.ds = ds;
    }
    public String getColumnName(int column){
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
        VeXe veThang = ds.get(rowIndex);
        if (veThang instanceof VeThang) {
            switch (columnIndex) {
                case 0:
                    return rowIndex + 1;
                case 1:
                    return veThang.getMaVe();
                case 2:
                    return ((VeThang) veThang).getTenKH();
                case 3:
                    return ((VeThang) veThang).getSoDienThoai();
                case 4:
                    return veThang.getBienSo();
                case 5:
                    return veThang.getLoaiXe();
                case 6:
                    return veThang.getMauXe();
                case 7:
                    return ((VeThang) veThang).getNgayDangKy();
                case 8:
                    if (veThang.getLoaiXe().equals("Xe Đạp")) {
                        return 30 * 3000;
                    } else if (veThang.getLoaiXe().equals("Xe Máy")) {
                        return 30 * 5000;
                    } else
                        return 30 * 20000;
                default:
                    return veThang;
            }
        }
        return veThang;
    }
}