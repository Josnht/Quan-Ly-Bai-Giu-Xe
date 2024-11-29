package ui;

import com.toedter.calendar.JDateChooser;
import dao.VeThang_Dao;
import dao.VeXe_Dao;
import entity.VeThang;
import entity.VeXe;
import model.XeTrongBai_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Form_KhuVuc extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblKhuA, lblKhuC, lblKhuD, lblKhuE, lblKhuF, lblKhuVuc, lblViTri, lblKhuB;
    JTextField txtKhuA, txtKhuE, txtKhuF, txtLuongCB, txtPhuCap, txtTen, txtDiaChi;
    JComboBox<String> cbcGT, cbcViTri, cbcHeSoLuong;
    JDateChooser ngayNhan;
    private JTextField txtKhuC, txtKhuB, txtKhuD, txtKhuVuc, txtViTri;
    private JComboBox<String> cbcKhuVuc;
    private JTextField txtTimKiem;
    private JLabel lblUnit;

    public Form_KhuVuc() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ XE TRONG BÃI");
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b, b1, b2, b3, b4, b5, b6;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        JPanel pnCenS = new JPanel();

        //Center North
        Box bNorth;
        bNorth = Box.createHorizontalBox();
        JLabel lblLoaive, lblLoaiXe;
        JComboBox cbcLoaiVe, cbcLoaiXe;
        JButton btnTimVe;

        bNorth.add(Box.createVerticalStrut(15));
        bNorth.setPreferredSize(new Dimension(840, 30));
//        bNorth.add(lblLoaive = new JLabel("Loại Vé: "));
//        bNorth.add(cbcLoaiVe = new JComboBox());
        bNorth.add(Box.createHorizontalStrut(20));
        bNorth.add(lblLoaiXe = new JLabel("Loại Xe: "));
        bNorth.add(cbcLoaiXe = new JComboBox());
        cbcLoaiXe.addItem("Tất cả");
        cbcLoaiXe.addItem("Xe Đạp");
        cbcLoaiXe.addItem("Xe Máy");
        cbcLoaiXe.addItem("Xe Oto");
        bNorth.add(Box.createVerticalStrut(15));

        pnCenN.add(bNorth);


        b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(10));
        b.setPreferredSize(new Dimension(840, 170));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblKhuA = new JLabel("Xe Đang Trong Khu A:"));
        b1.add(txtKhuA = new JTextField());
        b1.add(Box.createHorizontalStrut(5));
        b1.add(lblUnit = new JLabel("Chiếc"));
        b1.add(Box.createHorizontalStrut(40));
        b1.add(lblKhuB = new JLabel("Xe Đang Trong Khu B:"));
        b1.add(txtKhuB = new JTextField());
        b1.add(Box.createHorizontalStrut(5));
        b1.add(lblUnit = new JLabel("Chiếc"));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblKhuC = new JLabel("Xe Đang Trong Khu C:"));
        b2.add(txtKhuC = new JTextField());
        b2.add(Box.createHorizontalStrut(5));
        b2.add(lblUnit = new JLabel("Chiếc"));
        b2.add(Box.createHorizontalStrut(40));
        b2.add(lblKhuD = new JLabel("Xe Đang Trong Khu D:"));
        b2.add(txtKhuD = new JTextField());
        b2.add(Box.createHorizontalStrut(5));
        b2.add(lblUnit = new JLabel("Chiếc"));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblKhuE = new JLabel("Xe Đang Trong Khu E:   "));
        b3.add(txtKhuE = new JTextField());
        b3.add(Box.createHorizontalStrut(5));
        b3.add(lblUnit = new JLabel("Chiếc"));
        b3.add(Box.createHorizontalStrut(40));
        b3.add(lblKhuF = new JLabel("Xe Đang Trong Khu F:"));
        b3.add(txtKhuF = new JTextField());
        b3.add(Box.createHorizontalStrut(5));
        b3.add(lblUnit = new JLabel("Chiếc"));
        b.add(Box.createVerticalStrut(10));

        txtKhuA.setEditable(true);
        txtKhuB.setEditable(true);
        txtKhuC.setEditable(true);
        txtKhuD.setEditable(true);
        txtKhuE.setEditable(true);
        txtKhuF.setEditable(true);

        lblKhuA.setPreferredSize(lblKhuE.getPreferredSize());
        lblKhuB.setPreferredSize(lblKhuE.getPreferredSize());
        lblKhuF.setPreferredSize(lblKhuE.getPreferredSize());
        lblKhuC.setPreferredSize(lblKhuE.getPreferredSize());
        lblKhuD.setPreferredSize(lblKhuE.getPreferredSize());


        JButton btnInHoaDon, btnLamMoi;
        pnCenS.add(btnInHoaDon = new JButton("In Hóa Đơn"));
        btnInHoaDon.setIcon(new ImageIcon(getClass().getResource("/icons/print_icon.png")));
        btnInHoaDon.setBackground(Color.decode("#ff6900"));
        btnInHoaDon.setForeground(Color.decode("#FFFFFF"));
        pnCenS.add(btnLamMoi = new JButton("Làm Mới"));
        btnLamMoi.setIcon(new ImageIcon(getClass().getResource("/icons/update_icon.png")));
        btnLamMoi.setBackground(Color.decode("#00bcd4"));
        btnLamMoi.setForeground(Color.decode("#FFFFFF"));

        pnCenC.add(b);
        pnCenC.setBorder(new TitledBorder("Thông tin vé xe"));
        pnCenter.add(pnCenN, BorderLayout.NORTH);
        pnCenter.add(pnCenC, BorderLayout.CENTER);
        pnCenter.add(pnCenS, BorderLayout.SOUTH);

        //pnSouth
        pnSouth = new JPanel();
        int slKhuA = 0, slKhuB = 0, slKhuC = 0, slKhuD = 0, slKhuE = 0, slKhuF = 0;
        VeXe_Dao veXe_dao = new VeXe_Dao();
        VeThang_Dao veThang_dao = new VeThang_Dao();
        List<VeXe> list = new ArrayList<>();
        for (VeThang vt : veThang_dao.getLS()) {
            if (vt.getNgayNhan() != null) {
                list.add(vt);
                if (vt.getKhuVuc() != null) {
                    if (vt.getKhuVuc().getMaKhuVuc().equals("KV001")) {
                        slKhuA += 1;
                    } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV002")) {
                        slKhuB += 1;
                    } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV003")) {
                        slKhuC += 1;
                    } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV004")) {
                        slKhuD += 1;
                    } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV005")) {
                        slKhuE += 1;
                    } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV006")) {
                        slKhuF += 1;
                    }
                }
            }
        }
        for (VeXe vn : veXe_dao.TimKiemLoaiXe(cbcLoaiXe.getSelectedItem().toString())) {
            list.add(vn);
            if (vn.getKhuVuc() != null) {
                if (vn.getKhuVuc().getMaKhuVuc().equals("KV001")) {
                    slKhuA += 1;
                } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV002")) {
                    slKhuB += 1;
                } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV003")) {
                    slKhuC += 1;
                } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV004")) {
                    slKhuD += 1;
                } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV005")) {
                    slKhuE += 1;
                } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV006")) {
                    slKhuF += 1;
                }
            }
        }
        txtKhuA.setText(String.valueOf(slKhuA));
        txtKhuB.setText(String.valueOf(slKhuB));
        txtKhuC.setText(String.valueOf(slKhuC));
        txtKhuD.setText(String.valueOf(slKhuD));
        txtKhuE.setText(String.valueOf(slKhuE));
        txtKhuF.setText(String.valueOf(slKhuF));

        cbcLoaiXe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int slKhuA = 0, slKhuB = 0, slKhuC = 0, slKhuD = 0, slKhuE = 0, slKhuF = 0;
                List<VeXe> list = new ArrayList<>();
                if (cbcLoaiXe.getSelectedIndex() != 0) {
                    for (VeThang vt : veThang_dao.TimKiemLoaiXe(cbcLoaiXe.getSelectedItem().toString())) {
                        if (vt.getNgayNhan() != null) {
                            list.add(vt);
                            if (vt.getKhuVuc() != null) {
                                if (vt.getKhuVuc().getMaKhuVuc().equals("KV001")) {
                                    slKhuA += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV002")) {
                                    slKhuB += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV003")) {
                                    slKhuC += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV004")) {
                                    slKhuD += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV005")) {
                                    slKhuE += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV006")) {
                                    slKhuF += 1;
                                }
                            }
                        }
                    }
                    for (VeXe vn : veXe_dao.getLS()) {
                        list.add(vn);
                        if (vn.getKhuVuc() != null) {
                            if (vn.getKhuVuc().getMaKhuVuc().equals("KV001")) {
                                slKhuA += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV002")) {
                                slKhuB += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV003")) {
                                slKhuC += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV004")) {
                                slKhuD += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV005")) {
                                slKhuE += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV006")) {
                                slKhuF += 1;
                            }
                        }
                    }
                    txtKhuA.setText(String.valueOf(slKhuA));
                    txtKhuB.setText(String.valueOf(slKhuB));
                    txtKhuC.setText(String.valueOf(slKhuC));
                    txtKhuD.setText(String.valueOf(slKhuD));
                    txtKhuE.setText(String.valueOf(slKhuE));
                    txtKhuF.setText(String.valueOf(slKhuF));
                } else {
                    for (VeThang vt : veThang_dao.getLS()) {
                        if (vt.getNgayNhan() != null) {
                            list.add(vt);
                            if (vt.getKhuVuc() != null) {
                                if (vt.getKhuVuc().getMaKhuVuc().equals("KV001")) {
                                    slKhuA += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV002")) {
                                    slKhuB += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV003")) {
                                    slKhuC += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV004")) {
                                    slKhuD += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV005")) {
                                    slKhuE += 1;
                                } else if (vt.getKhuVuc().getMaKhuVuc().equals("KV006")) {
                                    slKhuF += 1;
                                }
                            }
                        }
                    }
                    for (VeXe vn : veXe_dao.TimKiemLoaiXe(cbcLoaiXe.getSelectedItem().toString())) {
                        list.add(vn);
                        if (vn.getKhuVuc() != null) {
                            if (vn.getKhuVuc().getMaKhuVuc().equals("KV001")) {
                                slKhuA += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV002")) {
                                slKhuB += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV003")) {
                                slKhuC += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV004")) {
                                slKhuD += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV005")) {
                                slKhuE += 1;
                            } else if (vn.getKhuVuc().getMaKhuVuc().equals("KV006")) {
                                slKhuF += 1;
                            }
                        }
                    }
                    txtKhuA.setText(String.valueOf(slKhuA));
                    txtKhuB.setText(String.valueOf(slKhuB));
                    txtKhuC.setText(String.valueOf(slKhuC));
                    txtKhuD.setText(String.valueOf(slKhuD));
                    txtKhuE.setText(String.valueOf(slKhuE));
                    txtKhuF.setText(String.valueOf(slKhuF));
                }

            }
        });

        XeTrongBai_Table model = new XeTrongBai_Table(list);
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtKhuA.setEditable(false);
                    txtKhuA.setText(table.getValueAt(r, 0).toString());
                    txtTen.setText(table.getValueAt(r, 1).toString());
                    txtKhuE.setText(table.getValueAt(r, 2).toString());
                    ngayNhan.setDate(Date.valueOf(table.getValueAt(r, 3).toString()));
                    if (table.getValueAt(r, 4).toString().equalsIgnoreCase("Nam"))
                        cbcGT.setSelectedItem("Nam");
                    else
                        cbcGT.setSelectedItem("Nữ");
                    txtDiaChi.setText(table.getValueAt(r, 5).toString());
                    txtKhuF.setText(table.getValueAt(r, 6).toString());
                    txtLuongCB.setText(table.getValueAt(r, 7).toString());
                    txtPhuCap.setText(table.getValueAt(r, 8).toString());
                    cbcViTri.setSelectedItem(table.getValueAt(r, 9).toString());
                    cbcHeSoLuong.setSelectedItem(table.getValueAt(r, 10).toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(1100, 330));

        txtKhuA.setEditable(false);
        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Xe Trong Bãi"));

//        Su kien luu
//        btnThem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (txtTen.getText().trim().equalsIgnoreCase("")) {
//                    JOptionPane.showMessageDialog(null, "Tên nhân viên không được rỗng");
//                } else {
//                    if (txtSDT.getText().trim().matches("\\d{10}")) {
//                        if (txtChungMinh.getText().trim().equalsIgnoreCase("")) {
//                            JOptionPane.showMessageDialog(null, "Chưa nhập số CMND");
//                        } else {
//                            String dateTime = (String) formatter.format(namSinh.getDate());
//                            NhanVienHanhChinh nv = new NhanVienHanhChinh(txtMa.getText().trim(), txtTen.getText().trim(),
//                                    Integer.parseInt(txtChungMinh.getText().trim()),
//                                    Date.valueOf(dateTime),
//                                    cbcGT.getSelectedItem().toString(),
//                                    Double.parseDouble(txtLuongCB.getText().trim()),
//                                    txtSDT.getText().trim(),
//                                    txtDiaChi.getText(),
//                                    Double.parseDouble(txtPhuCap.getText().trim()));
//                            HeSoLuong heSoLuong = heSoLuong_dao.TimKiemHeSo(Double.parseDouble(cbcHeSoLuong.getSelectedItem().toString().trim()));
//                            Phongban phongban = phongBan_dao.TimKiemTen(cbcPhongBan.getSelectedItem().toString().trim());
//                            if (heSoLuong != null && phongban != null) {
//                                nv.setHeSoLuong(heSoLuong);
//                                nv.setPhongban(phongban);
//                                if (nhanVienHanhChinh_dao.addNhanVien(nv)) {
//                                    try {
//                                        table.setModel(new NhanVien_Table(nhanVienHanhChinh_dao.getLS()));
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                } else
//                                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Lỗi!");
//                            }
//                            clearTextField();
//                            table.setModel(new NhanVien_Table(nhanVienHanhChinh_dao.getLS()));
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
//                    }
//                }
//
//            }
//        });
//
//        //Sự kiện xóa
//        btnXoa.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int r = table.getSelectedRow();
//                if (r != -1) {
//                    int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa dòng này?", "Delete",
//                            JOptionPane.YES_NO_OPTION);
//                    if (tb == JOptionPane.YES_OPTION) {
//                        String maX = txtMa.getText().trim();
//                        if (nhanVienHanhChinh_dao.deleteNV(maX)) {
//                            try {
//                                table.setModel(new NhanVien_Table(nhanVienHanhChinh_dao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                        clearTextField();
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
//                }
//            }
//        });

        //Su Kien Sua
//        btnSua.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String dateTime = (String) formatter.format(namSinh.getDate());
//                int r = table.getSelectedRow();
//                txtMa.setEnabled(false);
//                if (r != -1) {
//                    String maS = txtMa.getText().trim();
//                    NhanVienHanhChinh nv = new NhanVienHanhChinh(maS, txtTen.getText().trim(),
//                            Integer.parseInt(txtChungMinh.getText().trim()),
//                            Date.valueOf(dateTime),
//                            cbcGT.getSelectedItem().toString(),
//                            Double.parseDouble(txtLuongCB.getText().trim()),
//                            txtSDT.getText().trim(),
//                            txtDiaChi.getText(),
//                            Double.parseDouble(txtPhuCap.getText().trim()));
//                    HeSoLuong heSoLuong = heSoLuong_dao.TimKiemHeSo(Double.parseDouble(cbcHeSoLuong.getSelectedItem().toString().trim()));
//                    Phongban phongban = phongBan_dao.TimKiemTen(cbcPhongBan.getSelectedItem().toString().trim());
//                    nv.setHeSoLuong(heSoLuong);
//                    nv.setPhongban(phongban);
//                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?", "option", JOptionPane.YES_NO_OPTION);
//                    if (lc == JOptionPane.YES_OPTION) {
//                        if (nhanVienHanhChinh_dao.updateNhanVien(maS, nv)) {
//                            try {
//                                table.setModel(new NhanVien_Table(nhanVienHanhChinh_dao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                    }
//                    clearTextField();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần sửa!");
//                }
//            }
//        });

//        btnXoaRong.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                clearTextField();
//            }
//        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);

    }

    public void clearTextField() {
    }
}