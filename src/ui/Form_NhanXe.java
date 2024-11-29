package ui;

import com.toedter.calendar.JDateChooser;
import dao.*;
import entity.*;
import model.VeThang_Table;
import model.XeTrongBai_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Form_NhanXe extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblMa, lblGioNhan, lblNgayNhan, lblBienSoXe, lblMauXe, lblKhuVuc, lblViTri, lblLoaiXe, lblHeSoLuong, lblPhuCap, lblDiaChi;
    JTextField txtMa, txtBienSoXe, txtMauXe, txtLuongCB, txtPhuCap, txtTen, txtDiaChi;
    JComboBox<String> cbcGT, cbcViTri, cbcHeSoLuong;
    JDateChooser ngayNhan, NgayVao;
    private JComboBox<String> cbcLoaiXe;
    private JTextField txtGioNhan;
    private JComboBox<String> cbcKhuVuc;
    private JButton btnLamMoi;
    private JFormattedTextField formatText;

    public Form_NhanXe() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("NHẬN XE VÀO BÃI");
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
        bNorth.setPreferredSize(new Dimension(500, 30));
        bNorth.add(lblLoaive = new JLabel("Loại Vé: "));
        bNorth.add(cbcLoaiVe = new JComboBox());
        LoaiVe_Dao loaiVe_dao = new LoaiVe_Dao();
        for (LoaiVe lv : loaiVe_dao.getLS()) {
            cbcLoaiVe.addItem(lv.getTenLoaiVe());
        }
        cbcLoaiVe.setPreferredSize(new Dimension(317, 20));
        bNorth.add(Box.createHorizontalStrut(20));
        bNorth.add(lblBienSo = new JLabel("Biển Số: "));
        bNorth.add(cbcBienSo = new JComboBox());
        cbcBienSo.setPreferredSize(new Dimension(317, 20));
        bNorth.add(Box.createHorizontalStrut(20));
        bNorth.add(btnTimVe = new JButton("Tìm Vé"));
        btnTimVe.setIcon(new ImageIcon(getClass().getResource("/icons/search_client_icon.png")));
        btnTimVe.setBackground(Color.decode("#00bcd4"));
        btnTimVe.setForeground(Color.decode("#FFFFFF"));
        bNorth.add(Box.createVerticalStrut(15));

        pnCenN.add(bNorth);


        b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(10));
        b.setPreferredSize(new Dimension(840, 180));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Vé: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblLoaiXe = new JLabel("Loại Xe:    "));
        b1.add(cbcLoaiXe = new JComboBox<>());
        cbcLoaiXe.addItem("Xe Đạp");
        cbcLoaiXe.addItem("Xe Máy");
        cbcLoaiXe.addItem("Xe Oto");
        cbcLoaiXe.setPreferredSize(new Dimension(340, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioNhan = new JLabel("Giờ Nhận: "));
        b2.add(txtGioNhan = new JTextField(9));

        Timer timer;
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date date = new java.util.Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String time = timeFormat.format(date);
                txtGioNhan.setText(time);
            }
        };
        timer = new Timer(1000, actionListener);
        timer.setInitialDelay(0);
        timer.start();
        b2.add(Box.createHorizontalStrut(20));

        b2.add(lblNgayNhan = new JLabel("Ngày Nhận:"));
        ngayNhan = new JDateChooser();
        ngayNhan.setSize(new Dimension(30, 20));
        ngayNhan.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        ngayNhan.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date1);
            ngayNhan.setDate(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtGioNhan.setEditable(false);
        ngayNhan.setEnabled(false);
        b2.add(ngayNhan);
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
        b4.add(cbcKhuVuc = new JComboBox<>());
        KhuVuc_Dao khuVuc_dao = new KhuVuc_Dao();
        for (KhuVuc kv : khuVuc_dao.getLS()) {
            cbcKhuVuc.addItem(kv.getTenKhuVuc());
        }
        cbcKhuVuc.setPreferredSize(cbcLoaiXe.getPreferredSize());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblViTri = new JLabel("Vị Trí:"));
        cbcViTri = new JComboBox<>();
        cbcViTri.setEnabled(false);
        cbcViTri.setPreferredSize(cbcLoaiXe.getPreferredSize());
        b4.add(cbcViTri);
        b.add(Box.createVerticalStrut(10));

        cbcBienSo.setEnabled(false);
        btnTimVe.setEnabled(false);

        VeThang_Dao veThang_dao = new VeThang_Dao();

        cbcLoaiVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbcLoaiVe.getSelectedItem().equals("Vé Tháng")) {
                    cbcBienSo.setEnabled(true);
                    btnTimVe.setEnabled(true);
                    for (VeThang vt : veThang_dao.getLS()) {
                        cbcBienSo.addItem(vt.getBienSo());
                    }
                } else {
                    cbcBienSo.setEnabled(false);
                    btnTimVe.setEnabled(false);
                }
            }
        });

        btnTimVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VeThang veThang = veThang_dao.TimKiemBienSo(cbcBienSo.getSelectedItem().toString());
                txtMa.setText(veThang.getMaVe());
                cbcLoaiXe.setSelectedItem(veThang.getLoaiXe());
                txtBienSoXe.setText(veThang.getBienSo());
                txtMauXe.setText(veThang.getMauXe());
            }
        });

        ViTri_Dao viTri_dao = new ViTri_Dao();

        cbcKhuVuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbcLoaiXe.getSelectedItem().equals("Xe Oto")) {
                    cbcViTri.setEnabled(true);
                    List<ViTri> list = viTri_dao.TimKiemMaKV(khuVuc_dao.TimKiemTen(cbcKhuVuc.getSelectedItem().toString()));
                    for (ViTri vt : list) {
                        cbcViTri.addItem(vt.getTenViTri());
                    }
                } else {
                    cbcViTri.setEnabled(false);
                }
            }
        });

        lblMa.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblLoaiXe.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblMauXe.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblKhuVuc.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblGioNhan.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblNgayNhan.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblViTri.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblLoaive.setPreferredSize(lblBienSoXe.getPreferredSize());
        lblBienSo.setPreferredSize(lblBienSoXe.getPreferredSize());


        JButton btnNhanXe, btnXoa, btnSua, btnThoat, btnXoaRong;
        pnCenS.add(btnNhanXe = new JButton("Nhận Xe"));
        btnNhanXe.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnNhanXe.setBackground(Color.decode("#4caf50"));
        btnNhanXe.setForeground(Color.decode("#FFFFFF"));
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
        List<VeXe> list = new ArrayList<>();
        for (VeThang vt : veThang_dao.getLS()) {
            if (vt.getNgayNhan() != null)
                list.add(vt);
        }
        for (VeXe vn : veXe_dao.getLS()) {
            list.add(vn);
        }
        XeTrongBai_Table model = new XeTrongBai_Table(list);
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtMa.setEditable(false);
                    txtMa.setText(table.getValueAt(r, 1).toString());
                    cbcLoaiXe.setSelectedItem(table.getValueAt(r, 3).toString());
                    txtBienSoXe.setText(table.getValueAt(r, 2).toString());
                    txtMauXe.setText(table.getValueAt(r, 4).toString());
                    cbcKhuVuc.setSelectedItem(table.getValueAt(r, 5).toString());
                    cbcViTri.setSelectedItem(table.getValueAt(r, 6).toString());
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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = (String) formatter.format(ngayNhan.getDate());

//        Su kien luu
        btnNhanXe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbcLoaiVe.getSelectedItem().equals("Vé Ngày")) {
                    VeNgay veNgay = new VeNgay(txtMa.getText().trim(), cbcLoaiXe.getSelectedItem().toString(),
                            txtBienSoXe.getText(),
                            txtMauXe.getText());
                    veNgay.setNgayNhan(Date.valueOf(dateTime));
                    veNgay.setGioNhan(Time.valueOf(txtGioNhan.getText()));
                    veNgay.setKhuVuc(khuVuc_dao.TimKiemMa(khuVuc_dao.TimKiemTen(cbcKhuVuc.getSelectedItem().toString())));
                    if (cbcLoaiXe.getSelectedItem().equals("Xe Oto")) {
                        veNgay.setViTri(viTri_dao.TimKiemViTriByTen(cbcViTri.getSelectedItem().toString()));
                    }
                    if (veXe_dao.addveNgay(veNgay)) {
                        try {
                            List<VeXe> list = new ArrayList<>();
                            for (VeThang vt : veThang_dao.getLS()) {
                                if (vt.getNgayNhan() != null)
                                    list.add(vt);
                            }
                            for (VeXe vn : veXe_dao.getLS()) {
                                list.add(vn);
                            }
                            table.setModel(new XeTrongBai_Table(list));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                } else {
                    VeThang veThang = veThang_dao.TimKiemMa(txtMa.getText().trim());
                    veThang.setNgayNhan(Date.valueOf(dateTime));
                    veThang.setGioNhan(Time.valueOf(txtGioNhan.getText()));
                    veThang.setKhuVuc(khuVuc_dao.TimKiemMa(khuVuc_dao.TimKiemTen(cbcKhuVuc.getSelectedItem().toString())));
                    if (cbcLoaiXe.getSelectedItem().equals("Xe Oto")) {
                        veThang.setViTri(viTri_dao.TimKiemViTriByTen(cbcViTri.getSelectedItem().toString()));
                    }
                    if (veThang_dao.updateVeThang(veThang)) {
                        try {
                            List<VeXe> list = new ArrayList<>();
                            for (VeThang vt : veThang_dao.getLS()) {
                                if (vt.getNgayNhan() != null)
                                    list.add(vt);
                            }
                            for (VeXe vn : veXe_dao.getLS()) {
                                list.add(vn);
                            }
                            table.setModel(new XeTrongBai_Table(list));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                clearTextField();
                List<VeXe> list = new ArrayList<>();
                for (VeThang vt : veThang_dao.getLS()) {
                    if (vt.getNgayNhan() != null)
                        list.add(vt);
                }
                for (VeXe vn : veXe_dao.getLS()) {
                    list.add(vn);
                }
                table.setModel(new XeTrongBai_Table(list));

            }

        });

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
                table.setModel(new XeTrongBai_Table(list));
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);

    }

    public void clearTextField() {
        txtMa.setText("");
        cbcLoaiXe.setSelectedIndex(0);
        txtBienSoXe.setText("");
        txtMauXe.setText("");
        cbcKhuVuc.setSelectedIndex(0);
        cbcKhuVuc.setSelectedIndex(0);
        cbcViTri.removeAllItems();
        cbcViTri.setEnabled(false);
    }

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
        }
        return formatter;
    }
}