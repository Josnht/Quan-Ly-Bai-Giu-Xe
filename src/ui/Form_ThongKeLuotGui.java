package ui;

import com.toedter.calendar.JDateChooser;
import dao.VeThang_Dao;
import dao.VeXe_Dao;
import entity.VeThang;
import entity.VeXe;
import model.LuotGui_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Form_ThongKeLuotGui extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblTongTien, lblTongLuot;
    JTextField txtTongTien, txtKhuE, txtKhuF, txtLuongCB, txtPhuCap, txtTen, txtDiaChi;
    JComboBox<String> cbcGT, cbcViTri, cbcHeSoLuong;
    JDateChooser ngayNhan;
    private JTextField txtTongLuot;
    private JLabel lblUnit, lblLuot;
    private JDateChooser ngayThongKe;

    public Form_ThongKeLuotGui() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("THỐNG KÊ LƯỢT GỬI XE");
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
        Box bNorth, bNorth1, bNorth2, bNorth3;
        bNorth = Box.createVerticalBox();
        JRadioButton rdThang, rdNgay;
        ButtonGroup btnGR;
        JComboBox cbcThang;
        JButton btnTraCuu;
        bNorth.setPreferredSize(new Dimension(400, 110));

        bNorth.add(bNorth1 = Box.createHorizontalBox());
        bNorth1.add(rdThang = new JRadioButton("Tháng"));
        bNorth1.add(Box.createHorizontalStrut(10));
        bNorth1.add(cbcThang = new JComboBox<>());
        for (int i = 1; i <= 12; i++) {
            cbcThang.addItem(String.valueOf(i));
        }
        bNorth.add(Box.createVerticalStrut(10));

        bNorth.add(bNorth2 = Box.createHorizontalBox());
        bNorth2.add(rdNgay = new JRadioButton("Ngày"));
        bNorth2.add(Box.createHorizontalStrut(10));
        ngayThongKe = new JDateChooser();
        ngayThongKe.setSize(new Dimension(30,20));
        ngayThongKe.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        ngayThongKe.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            ngayThongKe.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bNorth2.add(ngayThongKe);
        bNorth.add(Box.createVerticalStrut(20));

        rdNgay.setPreferredSize(rdThang.getPreferredSize());
        btnGR = new ButtonGroup();
        btnGR.add(rdThang);
        btnGR.add(rdNgay);

        btnTraCuu = new JButton("Tra Cứu");
        btnTraCuu.setIcon(new ImageIcon(getClass().getResource("/icons/search_client_icon.png")));
        btnTraCuu.setBackground(Color.decode("#00bcd4"));
        btnTraCuu.setForeground(Color.decode("#FFFFFF"));


        bNorth.add(bNorth3 = Box.createHorizontalBox());
        bNorth3.add(btnTraCuu);


        pnCenN.add(bNorth);

        b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(10));
        b.setPreferredSize(new Dimension(300, 80));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblTongTien = new JLabel("Tổng Tiền:"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(txtTongTien = new JTextField());
        txtTongTien.setBorder(null);
        b1.add(Box.createHorizontalStrut(5));
        b1.add(lblUnit = new JLabel("VNĐ"));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblTongLuot = new JLabel("Tổng Số Lượt Gửi:"));
        b2.add(Box.createHorizontalStrut(10));
        b2.add(txtTongLuot = new JTextField());
        txtTongLuot.setBorder(null);
        b2.add(Box.createHorizontalStrut(5));
        b2.add(lblLuot = new JLabel("Lượt"));
        b.add(Box.createVerticalStrut(10));

        txtTongTien.setEditable(false);
        txtTongLuot.setEditable(false);

        lblTongTien.setPreferredSize(lblTongLuot.getPreferredSize());

        JButton btnInHoaDon, btnXoa, btnSua, btnLamMoi, btnXoaRong;
        pnCenS.add(btnInHoaDon = new JButton("Xuất Excel"));
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
        double tongTien = 0;
        int soLuot = 0;
        for (int i = 0; i < table.getRowCount(); i++){
            tongTien += Double.valueOf(String.valueOf(table.getValueAt(i, 11)));
            soLuot++;
        }
        txtTongTien.setText(String.valueOf(tongTien));
        txtTongLuot.setText(String.valueOf(soLuot));
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {

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

        txtTongTien.setEditable(false);
        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Xe Trong Bãi"));


        btnTraCuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdThang.isSelected()) {
                    List<VeXe> list = new ArrayList<>();
                    for (VeThang vt : veThang_dao.TimKiemThangGui(Integer.parseInt(cbcThang.getSelectedItem().toString()))) {
                        if (vt.getNgayNhan() != null)
                            list.add(vt);
                    }
                    for (VeXe vn : veXe_dao.TimKiemThang(Integer.parseInt(cbcThang.getSelectedItem().toString()))) {
                        list.add(vn);
                    }
                    table.setModel(new LuotGui_Table(list));
                    double tongTien = 0;
                    int soLuot = 0;
                    for (int i = 0; i < table.getRowCount(); i++){
                        tongTien += Double.valueOf(String.valueOf(table.getValueAt(i, 11)));
                        soLuot++;
                    }
                    txtTongTien.setText(String.valueOf(tongTien));
                    txtTongLuot.setText(String.valueOf(soLuot));
                } else {
                    List<VeXe> list = new ArrayList<>();
                    for (VeThang vt : veThang_dao.TimKiemNgayGui((Date) ngayThongKe.getDate())) {
                        if (vt.getNgayNhan() != null)
                            list.add(vt);
                    }
                    for (VeXe vn : veXe_dao.TimKiemNgay((Date) ngayThongKe.getDate())) {
                        list.add(vn);
                    }
                    table.setModel(new LuotGui_Table(list));
                    double tongTien = 0;
                    int soLuot = 0;
                    for (int i = 0; i < table.getRowCount(); i++){
                        tongTien += Double.valueOf(String.valueOf(table.getValueAt(i, 11)));
                        soLuot++;
                    }
                    txtTongTien.setText(String.valueOf(tongTien));
                    txtTongLuot.setText(String.valueOf(soLuot));
                }
            }
        });

        btnInHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportExcel(table);
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);

    }

    public void exportExcel(JTable table) {
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bwrite = new BufferedWriter(out);
                LuotGui_Table model = (LuotGui_Table) table.getModel();
                // ten Cot
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bwrite.write(model.getColumnName(j) + "\t");
                }
                bwrite.write("\n");
                // Lay du lieu dong
                for (int j = 0; j < model.getRowCount(); j++) {
                    for (int k = 0; k < model.getColumnCount(); k++) {
                        bwrite.write(model.getValueAt(j, k) + "\t");
                        System.out.println(model.getValueAt(j, k));
                    }
                    bwrite.write("\n");
                }
                bwrite.close();
                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }

    public void clearTextField() {
    }
}