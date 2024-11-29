package ui;

import com.toedter.calendar.JDateChooser;
import dao.VeThang_Dao;
import dao.VeXe_Dao;
import entity.VeNgay;
import entity.VeThang;
import entity.VeXe;
import model.LuotGui_Table;
import model.XeTrongBai_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Form_TraXe extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblMa, lblGioNhan, lblNgayNhan, lblBienSoXe, lblMauXe, lblKhuVuc, lblViTri, lblLoaiXe;
    JTextField txtMa, txtBienSoXe, txtMauXe;
    private JTextField txtGioNhan, txtLoaiXe, txtNgayNhan, txtKhuVuc, txtViTri;
    private JTextField txtTimKiem;

    public Form_TraXe() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("TRẢ XE");
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
        JComboBox cbcLoaiVe, cbcTim;
        JButton btnTimVe;

        bNorth.add(Box.createVerticalStrut(15));
        bNorth.setPreferredSize(new Dimension(520, 30));
        bNorth.add(lblLoaive = new JLabel("Tìm Kiếm Thông Tin: "));
        bNorth.add(txtTimKiem = new JTextField());
        bNorth.add(Box.createHorizontalStrut(20));
        bNorth.add(cbcTim = new JComboBox());
        cbcTim.addItem("Theo Mã Vé");
        cbcTim.addItem("Theo Biển Số");
        cbcTim.setPreferredSize(new Dimension(120, 20));
        bNorth.add(Box.createHorizontalStrut(20));
        bNorth.add(btnTimVe = new JButton("Tìm Kiếm"));
        btnTimVe.setIcon(new ImageIcon(getClass().getResource("/icons/search_client_icon.png")));
        btnTimVe.setBackground(Color.decode("#00bcd4"));
        btnTimVe.setForeground(Color.decode("#FFFFFF"));
        bNorth.add(Box.createVerticalStrut(15));

        pnCenN.add(bNorth);


        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840, 180));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Vé: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblLoaiXe = new JLabel("Loại Xe:    "));
        b1.add(txtLoaiXe = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioNhan = new JLabel("Giờ Nhận: "));
        b2.add(txtGioNhan = new JTextField(30));
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNgayNhan = new JLabel("Ngày Nhận:"));
        b2.add(txtNgayNhan = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblBienSoXe = new JLabel("Biển Số Xe: "));
        b3.add(txtBienSoXe = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblMauXe = new JLabel("Màu Xe:    "));
        b3.add(txtMauXe = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblKhuVuc = new JLabel("Khu Vực: "));
        b4.add(txtKhuVuc = new JTextField());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblViTri = new JLabel("Vị Trí:    "));
        b4.add(txtViTri = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblLoaiXe.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblMauXe.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblKhuVuc.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblGioNhan.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblNgayNhan.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblViTri.setPreferredSize(lblBienSoXe.getPreferredSize());

        txtBienSoXe.setEditable(false);
        txtKhuVuc.setEditable(false);
        txtViTri.setEditable(false);
        txtGioNhan.setEditable(false);
        txtNgayNhan.setEditable(false);
        txtMauXe.setEditable(false);
        txtLoaiXe.setEditable(false);


        JButton btnTraXe,btnLamMoi;
        pnCenS.add(btnTraXe = new JButton("Trả Xe"));
        btnTraXe.setIcon(new ImageIcon(getClass().getResource("/icons/clear_icon.png")));
        btnTraXe.setBackground(Color.decode("#ff6900"));
        btnTraXe.setForeground(Color.decode("#FFFFFF"));
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
        VeXe_Dao veXe_dao = new VeXe_Dao();
        VeThang_Dao veThang_dao = new VeThang_Dao();
        List<VeXe> list = new ArrayList<>();
        for (VeThang vt : veThang_dao.getLS()) {
            if (vt.getNgayNhan() != null)
                list.add(vt);
        }
        for (VeXe vn : veXe_dao.getLS()) {
            list.add(vn);
        }
        LuotGui_Table model = new LuotGui_Table(list);
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtMa.setEditable(false);
                    txtMa.setText(table.getValueAt(r, 1).toString());
                    txtBienSoXe.setText(table.getValueAt(r, 2).toString());
                    txtLoaiXe.setText(table.getValueAt(r, 3).toString());
                    txtMauXe.setText(table.getValueAt(r, 4).toString());
                    txtKhuVuc.setText(table.getValueAt(r, 5).toString());
                    txtViTri.setText(table.getValueAt(r, 6).toString());
                    txtNgayNhan.setText(table.getValueAt(r, 7).toString());
                    txtGioNhan.setText(table.getValueAt(r, 8).toString());
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
        pnSouth.setBorder(new TitledBorder("Danh Sách Xe Trong Bãi"));


        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextField();
                List<VeXe> list = new ArrayList<>();
                for (VeThang vt : veThang_dao.getLS()) {
                    if (vt.getNgayNhan() != null)
                        list.add(vt);
                }
                for (VeXe vn : veXe_dao.getLS()) {
                    list.add(vn);
                }
                table.setModel(new LuotGui_Table(list));
            }
        });

        btnTimVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbcTim.getSelectedIndex() == 0) { //theo mã vé
                    List<VeXe> list = new ArrayList<>();
                    if (txtTimKiem.getText().contains("VN")) {
                        if (veXe_dao.TimKiemMa(txtTimKiem.getText()) != null) {
                            list.add(veXe_dao.TimKiemMa(txtTimKiem.getText()));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
                        }
                    } else if (txtTimKiem.getText().contains("VT")) {
                        if (veThang_dao.TimKiemMa(txtTimKiem.getText()) != null) {
                            list.add(veThang_dao.TimKiemMa(txtTimKiem.getText()));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Mã Không Tồn tại !");
                    }
                    table.setModel(new LuotGui_Table(list));
                } else {
                    List<VeXe> list = new ArrayList<>();
                        if (veXe_dao.TimKiemBienSo(txtTimKiem.getText().trim()) != null) {
                            list.add(veXe_dao.TimKiemBienSo(txtTimKiem.getText().trim()));
                        } else if (veThang_dao.TimKiemBienSo(txtTimKiem.getText().trim()) != null){
                            list.add(veThang_dao.TimKiemBienSo(txtTimKiem.getText().trim()));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
                        }
                    table.setModel(new LuotGui_Table(list));
                }
            }
        });

        btnTraXe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                VeNgay veNgay = null;
                VeThang veThang = null;
                int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn trả xe này?", "Trả Xe",
                        JOptionPane.YES_NO_OPTION);
                if (tb == JOptionPane.YES_OPTION) {
                    if (!txtMa.getText().equals("")) {
                        if (txtMa.getText().contains("VN")) {
                            if (veXe_dao.TimKiemMa(txtMa.getText()) != null) {
                                veNgay = veXe_dao.TimKiemMa(txtMa.getText());
                                veNgay.setNgayTra(Date.valueOf(LocalDate.now()));
                                java.util.Date date = new java.util.Date();
                                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                                String time = timeFormat.format(date);
                                veNgay.setGioTra(Time.valueOf(time));
                                veXe_dao.updateVeNgay(veNgay);
                            } else {
                                JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
                            }
                        } else if (txtMa.getText().contains("VT")) {
                            if (veThang_dao.TimKiemMa(txtMa.getText()) != null) {
                                veThang = veThang_dao.TimKiemMa(txtMa.getText());
                                veThang.setNgayTra(Date.valueOf(LocalDate.now()));
                                java.util.Date date = new java.util.Date();
                                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                                String time = timeFormat.format(date);
                                veThang.setGioTra(Time.valueOf(time));
                                veThang_dao.updateVeThang(veThang);
                            } else {
                                JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Mã Không Tồn tại !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Xe Cần Trả");
                    }
                }
                if (veNgay != null) {
                    Form_ThongTinTraXe form_thongTinTraXe = new Form_ThongTinTraXe(veNgay);
                    form_thongTinTraXe.doShow();
                    form_thongTinTraXe.setVisible(true);
                } else if (veThang != null) {
                    Form_ThongTinTraXe form_thongTinTraXe = new Form_ThongTinTraXe(veThang);
                    form_thongTinTraXe.doShow();
                    form_thongTinTraXe.setVisible(true);
                }

                List<VeXe> list = new ArrayList<>();
                if (txtMa.getText().contains("VN")) {
                    if (veXe_dao.TimKiemMa(txtMa.getText()) != null) {
                        list.add(veXe_dao.TimKiemMa(txtMa.getText()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
                    }
                } else if (txtMa.getText().contains("VT")) {
                    if (veThang_dao.TimKiemMa(txtMa.getText()) != null) {
                        list.add(veThang_dao.TimKiemMa(txtMa.getText()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Không Tìm Thấy !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mã Không Tồn tại !");
                }
                table.setModel(new LuotGui_Table(list));
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);

    }

    public void clearTextField() {
        txtMa.setText("");
        txtBienSoXe.setText("");
        txtLoaiXe.setText("");
        txtMauXe.setText("");
        txtKhuVuc.setText("");
        txtViTri.setText("");
        txtNgayNhan.setText("");
        txtGioNhan.setText("");
    }
}