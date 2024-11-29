package ui;

import dao.VeThang_Dao;
import entity.VeThang;
import entity.VeXe;
import model.VeThang_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Form_DangKyVeThang extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblMa, lblTenKH, lblSoDT, lblBienSoXe, lblMauXe, lblLoaiXe;
    JTextField txtMa, txtBienSoXe, txtMauXe;
    private JTextField txtTenKH, txtSoDT;
    private JTextField txtTimKiem;
    private JComboBox<String> cbcLoaiXe;
    private JButton btnThem;

    public Form_DangKyVeThang() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("ĐĂNG KÝ VÉ GỬI XE THÁNG");
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
        JLabel lblLoaive, lblBienSo;
        JComboBox cbcLoaiVe, cbcBienSo;
        JButton btnTimVe;

        bNorth.add(Box.createVerticalStrut(15));
        bNorth.setPreferredSize(new Dimension(840, 30));
        bNorth.add(lblLoaive = new JLabel("Tìm Kiếm Thông Tin: "));
        bNorth.add(txtTimKiem = new JTextField());
        bNorth.add(Box.createHorizontalStrut(20));
        bNorth.add(cbcBienSo = new JComboBox());
        cbcBienSo.addItem("Theo Mã Vé");
        cbcBienSo.addItem("Theo Biển Số");
        cbcBienSo.setPreferredSize(new Dimension(317, 20));
        bNorth.add(Box.createHorizontalStrut(20));
        bNorth.add(btnTimVe = new JButton("Tìm Kiếm"));
        btnTimVe.setIcon(new ImageIcon(getClass().getResource("/icons/search_client_icon.png")));
        btnTimVe.setBackground(Color.decode("#00bcd4"));
        btnTimVe.setForeground(Color.decode("#FFFFFF"));
        bNorth.add(Box.createVerticalStrut(15));

//        pnCenN.add(bNorth);


        b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(30));
        b.setPreferredSize(new Dimension(840, 170));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Vé: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblLoaiXe = new JLabel("Loại Xe:    "));
        b1.add(cbcLoaiXe = new JComboBox<>());
        cbcLoaiXe.addItem("Xe Đạp");
        cbcLoaiXe.addItem("Xe Máy");
        cbcLoaiXe.addItem("Xe Oto");
        cbcLoaiXe.setPreferredSize(new Dimension(310, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblTenKH = new JLabel("Tên Khách Hàng: "));
        b2.add(txtTenKH = new JTextField(30));
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblSoDT = new JLabel("Số Điện Thoại:"));
        b2.add(txtSoDT = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblBienSoXe = new JLabel("Biển Số Xe: "));
        b3.add(txtBienSoXe = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblMauXe = new JLabel("Màu Xe:    "));
        b3.add(txtMauXe = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        if (cbcLoaiXe.getSelectedItem().equals("Xe Đạp")) {
            txtBienSoXe.setEditable(false);
        } else
            txtBienSoXe.setEditable(true);

        cbcLoaiXe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbcLoaiXe.getSelectedItem().equals("Xe Đạp")) {
                    txtBienSoXe.setEditable(false);
                } else
                    txtBienSoXe.setEditable(true);
            }
        });

        lblMa.setPreferredSize(lblTenKH.getPreferredSize());
        lblLoaiXe.setPreferredSize(lblTenKH.getPreferredSize());
        lblMauXe.setPreferredSize(lblTenKH.getPreferredSize());
        lblBienSoXe.setPreferredSize(lblTenKH.getPreferredSize());
        lblSoDT.setPreferredSize(lblTenKH.getPreferredSize());


        JButton btnInHoaDon, btnXoa, btnSua, btnLamMoi, btnXoaRong;
        pnCenS.add(btnThem = new JButton("Thêm Vé Tháng"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenS.add(btnXoa = new JButton("Xóa Vé Tháng"));
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/icons/delete_icon.png")));
        btnXoa.setBackground(Color.decode("#f44336"));
        btnXoa.setForeground(Color.decode("#FFFFFF"));
        pnCenS.add(btnSua = new JButton("Sửa Thông Tin"));
        btnSua.setIcon(new ImageIcon(getClass().getResource("/icons/update_icon.png")));
        btnSua.setBackground(Color.decode("#00bcd4"));
        btnSua.setForeground(Color.decode("#FFFFFF"));
        pnCenS.add(btnXoaRong = new JButton("Xóa Rỗng"));
        btnXoaRong.setIcon(new ImageIcon(getClass().getResource("/icons/clear_icon.png")));
        btnXoaRong.setBackground(Color.decode("#ff6900"));
        btnXoaRong.setForeground(Color.decode("#FFFFFF"));

        pnCenC.add(b);
        pnCenC.setBorder(new TitledBorder("Thông tin đăng ký vé tháng"));
        pnCenter.add(pnCenN, BorderLayout.NORTH);
        pnCenter.add(pnCenC, BorderLayout.CENTER);
        pnCenter.add(pnCenS, BorderLayout.SOUTH);

        //pnSouth
        VeThang_Dao veThang_dao = new VeThang_Dao();
        pnSouth = new JPanel();
        List<VeXe> ls = new ArrayList<>();
        for (VeThang vt : veThang_dao.getLS()) {
            ls.add(vt);
        }

        VeThang_Table model = new VeThang_Table(ls);
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtMa.setText(table.getValueAt(r,1).toString());
                    if (table.getValueAt(r,2) != null) {
                        txtTenKH.setText(table.getValueAt(r, 2).toString());
                        txtSoDT.setText(table.getValueAt(r, 3).toString());
                    }
                    txtBienSoXe.setText(table.getValueAt(r, 4).toString());
                    cbcLoaiXe.setSelectedItem(table.getValueAt(r,5).toString());
                    txtMauXe.setText(table.getValueAt(r,6).toString());
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

        txtMa.setEditable(false);
        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Vé Đã Đăng Ký"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = (String) formatter.format(Date.valueOf(LocalDate.now()));

//        Su kien luu
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenKH.getText().trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Tên khách hàng không được rỗng");
                } else {
                    if (txtSoDT.getText().trim().matches("\\d{10}")) {
                        VeThang veThang = new VeThang(txtMa.getText().trim(), cbcLoaiXe.getSelectedItem().toString(),
                                txtBienSoXe.getText(),
                                txtMauXe.getText(),
                                txtTenKH.getText(),
                                txtSoDT.getText().trim(),
                                Date.valueOf(LocalDate.now()));
                        if (veThang_dao.addVeThang(veThang)) {
                            try {
                                List<VeXe> list = new ArrayList<>();
                                for (VeThang vt : veThang_dao.getLS()) {
                                    list.add(vt);
                                }
                                table.setModel(new VeThang_Table(list));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else
                            JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                        clearTextField();
                        List<VeXe> list = new ArrayList<>();
                        for (VeThang vt : veThang_dao.getLS()) {
                            list.add(vt);
                        }
                        table.setModel(new VeThang_Table(list));

                    } else {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
                    }
                }

            }
        });
//
//        //Sự kiện xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa dòng này?", "Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (tb == JOptionPane.YES_OPTION) {
                        String maX = txtMa.getText().trim();
                        if (veThang_dao.deleteVeThang(maX)) {
                            try {
                                List<VeXe> list = new ArrayList<>();
                                for (VeThang vt : veThang_dao.getLS()) {
                                    list.add(vt);
                                }
                                table.setModel(new VeThang_Table(list));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        clearTextField();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
                }
            }
        });

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String dateTime = (String) formatter.format(namSinh.getDate());
                int r = table.getSelectedRow();
                txtMa.setEnabled(false);
                if (r != -1) {
                    String maS = txtMa.getText().trim();
                    VeThang veThang = new VeThang(maS, cbcLoaiXe.getSelectedItem().toString(),
                            txtBienSoXe.getText(),
                            txtMauXe.getText(),
                            txtTenKH.getText(),
                            txtSoDT.getText().trim(),
                            Date.valueOf(LocalDate.now()));
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?", "option", JOptionPane.YES_NO_OPTION);
                    if (lc == JOptionPane.YES_OPTION) {
                        if (veThang_dao.updateTTVeThang(veThang)) {
                            try {
                                List<VeXe> list = new ArrayList<>();
                                for (VeThang vt : veThang_dao.getLS()) {
                                    list.add(vt);
                                }
                                table.setModel(new VeThang_Table(list));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    clearTextField();
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần sửa!");
                }
            }
        });

        btnXoaRong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextField();
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);

    }

    public void clearTextField() {
        txtMa.setText("");
        txtTenKH.setText("");
        cbcLoaiXe.setSelectedIndex(0);
        txtSoDT.setText("");
        txtBienSoXe.setText("");
        txtBienSoXe.setEditable(false);
        txtMauXe.setText("");
    }
}