package ui;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import entity.VeXe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class Form_ThongTinTraXe extends JFrame {
    protected VeXe veXe;
    private JLabel lblgth;
    private JLabel lblMaVe, lblLoaiXe, lblBienSo, lblMauXe, lblKhuVuc, lblViTri, lblNgayNhan, lblGioNhan, lblNgayTra, lblGioTra, lblTongTien;
    private JTextField txtMaVe, txtLoaiXe, txtNgayNhan, txtKhuVuc, txtViTri, txtBienSo, txtMauXe, txtGioNhan, txtNgayTra, txtGioTra, txtTongTien;

    public Form_ThongTinTraXe(VeXe veXe){
        this.veXe = veXe;
    }
    public void doShow(){
        setSize(650,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Thông Tin Trả Xe");
        Container cp = getContentPane();

        //North
        JPanel pnNorth = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        Box b = Box.createHorizontalBox();
        JPanel pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
        JPanel pnCenter1 = new JPanel();
        pnCenter1.setLayout(new BorderLayout());

        JLabel lblImages = new JLabel();
        pnWest1.add(lblImages,BorderLayout.CENTER);
        b.setPreferredSize(new Dimension(500,80));

        b.add(pnWest1);

        Box a,a1,a2,a3,a4;
        a = Box.createVerticalBox();
        a.add(Box.createVerticalStrut(15));
        a.add(a1 = Box.createHorizontalBox());
        a1.add(lblgth = new JLabel("BÃI GIỮ XE ABC"));
        a.add(Box.createVerticalStrut(5));
        a.add(a4 = Box.createHorizontalBox());
        a4.add(lblgth = new JLabel("Hotline: 0985345999 - 0985567999 - 0985999999"));
        a.add(Box.createVerticalStrut(5));
        pnCenter1.add(a);
        b.add(pnCenter1);
        b.add(Box.createHorizontalStrut(150));


        //Center
        JPanel pnCenter = new JPanel();
        JPanel pnNorthCen = new JPanel();
        JPanel pnCenterCen = new JPanel();
        JPanel pnSouthCen = new JPanel();
        pnSouthCen.setLayout(new BorderLayout());
        pnCenter.setLayout(new BorderLayout());
        //Tiêu đề
        JLabel lblTieuDe = new JLabel("THÔNG TIN TRẢ XE");
        lblTieuDe.setFont(new Font("Arial",Font.BOLD,18));
        pnNorthCen.add(lblTieuDe);

        //Thông Tin
        Box bCen,bCen1,bCen2,bCen3,bCen4,bCen5,bCen6,bCen7,bCen8,bCen9,bCen10;
        bCen = Box.createVerticalBox();
        bCen.setPreferredSize(new Dimension(500,320));
        bCen.add(bCen1 = Box.createHorizontalBox());
        bCen1.add(lblMaVe = new JLabel("Mã Vé:"));
        bCen1.add(Box.createHorizontalStrut(30));
        bCen1.add(txtMaVe = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen2 = Box.createHorizontalBox());
        bCen2.add(lblBienSo = new JLabel("Biển Số:"));
        bCen2.add(Box.createHorizontalStrut(30));
        bCen2.add(txtBienSo = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen3 = Box.createHorizontalBox());
        bCen3.add(lblLoaiXe = new JLabel("Loại Xe:"));
        bCen3.add(Box.createHorizontalStrut(30));
        bCen3.add(txtLoaiXe = new JTextField());
        //        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen10 = Box.createHorizontalBox());
        bCen10.add(lblMauXe = new JLabel("Màu Xe:"));
        bCen10.add(Box.createHorizontalStrut(30));
        bCen10.add(txtMauXe = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen4 = Box.createHorizontalBox());
        bCen4.add(lblKhuVuc = new JLabel("Khu Vực:"));
        bCen4.add(Box.createHorizontalStrut(30));
        bCen4.add(txtKhuVuc = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen5 = Box.createHorizontalBox());
        bCen5.add(lblViTri = new JLabel("Vị Trí:"));
        bCen5.add(Box.createHorizontalStrut(30));
        bCen5.add(txtViTri = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen6 = Box.createHorizontalBox());
        bCen6.add(lblNgayNhan = new JLabel("Ngày Nhận: "));
        bCen6.add(Box.createHorizontalStrut(30));
        bCen6.add(txtNgayNhan = new JTextField());
        
        bCen.add(bCen7 = Box.createHorizontalBox());
		bCen7.add(lblGioNhan = new JLabel("Giờ Nhận: "));
        bCen7.add(Box.createHorizontalStrut(30));
        bCen7.add(txtGioNhan = new JTextField());
        
        bCen.add(bCen8 = Box.createHorizontalBox());
        bCen8.add(lblNgayTra = new JLabel("Ngày Trả: "));
        bCen8.add(Box.createHorizontalStrut(30));
        bCen8.add(txtNgayTra = new JTextField());
        
        bCen.add(bCen9 = Box.createHorizontalBox());
        bCen9.add(lblGioTra = new JLabel("Giờ Trả: "));
        bCen9.add(Box.createHorizontalStrut(30));
        bCen9.add(txtGioTra = new JTextField());
        bCen.add(Box.createVerticalStrut(30));

        Box bc1, bc2;
        JButton btnIn;
        bCen.add(bc1 = Box.createHorizontalBox());
        bc1.add(Box.createHorizontalStrut(250));
        bc1.add(lblTongTien = new JLabel("Tổng Tiền:"));
        bc1.add(Box.createHorizontalStrut(10));
        bc1.add(txtTongTien = new JTextField());
        bCen.add(Box.createVerticalStrut(50));


        bCen.add(bc2 = Box.createHorizontalBox());
        bc2.add(btnIn = new JButton("In Hóa Đơn:"));
        btnIn.setIcon(new ImageIcon(getClass().getResource("/icons/print_icon.png")));
        btnIn.setBackground(Color.decode("#ff6900"));
        btnIn.setForeground(Color.decode("#FFFFFF"));

        lblMaVe.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblLoaiXe.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblBienSo.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblMauXe.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblKhuVuc.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblViTri.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblGioNhan.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblNgayTra.setPreferredSize(lblNgayNhan.getPreferredSize());
        lblGioTra.setPreferredSize(lblNgayNhan.getPreferredSize());

        pnSouthCen.setLayout(new BorderLayout());

        txtTongTien.setBorder(null);
        txtTongTien.setEditable(false);
        txtTongTien.setForeground(Color.BLACK);

        bCen.setBackground(Color.WHITE);
        bCen1.setBackground(Color.WHITE);
        pnCenterCen.setBackground(Color.WHITE);
        pnNorthCen.setBackground(Color.WHITE);
        pnSouthCen.setBackground(Color.WHITE);

        if (veXe != null) {
            txtMaVe.setText(veXe.getMaVe());
            txtLoaiXe.setText(veXe.getLoaiXe());
            txtBienSo.setText(veXe.getBienSo());
            txtMauXe.setText(veXe.getMauXe());
            if (veXe.getKhuVuc() != null)
                txtKhuVuc.setText(veXe.getKhuVuc().getTenKhuVuc());
            if (veXe.getViTri() != null)
                txtViTri.setText(veXe.getViTri().getTenViTri());
            txtNgayNhan.setText(String.valueOf(veXe.getNgayNhan()));
            txtGioNhan.setText(String.valueOf(veXe.getGioNhan()));
            txtNgayTra.setText(String.valueOf(veXe.getNgayTra()));
            txtGioTra.setText(String.valueOf(veXe.getGioTra()));
            if (veXe.getLoaiXe().equals("Xe Đạp")) {
                txtTongTien.setText(3000 + "    VNĐ");
            } else if (veXe.getLoaiXe().equals("Xe Máy")) {
                txtTongTien.setText(5000 + "    VNĐ");
            } else
                txtTongTien.setText(20000 + "   VNĐ");
        }


        txtMaVe.setBorder(null);
        txtMaVe.setEditable(false);
        txtGioTra.setBorder(null);
        txtGioTra.setEditable(false);
        txtNgayTra.setBorder(null);
        txtNgayTra.setEditable(false);
        txtLoaiXe.setBorder(null);
        txtLoaiXe.setEditable(false);
        txtBienSo.setBorder(null);
        txtBienSo.setEditable(false);
        txtMauXe.setBorder(null);
        txtMauXe.setEditable(false);
        txtKhuVuc.setBorder(null);
        txtKhuVuc.setEditable(false);
        txtViTri.setBorder(null);
        txtViTri.setEditable(false);
        txtNgayNhan.setBorder(null);
        txtNgayNhan.setEditable(false);
        txtGioNhan.setBorder(null);
        txtGioNhan.setEditable(false);
        txtNgayTra.setBorder(null);
        txtNgayTra.setEditable(false);
        txtGioTra.setBorder(null);
        txtGioTra.setEditable(false);

        pnCenterCen.add(bCen);

        pnCenter.add(pnNorthCen,BorderLayout.NORTH);
        pnCenter.add(pnCenterCen,BorderLayout.CENTER);
		
        pnNorth.setBackground(Color.WHITE);
        pnWest1.setBackground(Color.WHITE);
        pnCenter1.setBackground(Color.WHITE);
        pnNorth.add(b);
        cp.add(pnNorth,BorderLayout.NORTH);
        cp.add(pnCenter,BorderLayout.CENTER);

        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Document document = new Document();
                    Calendar c1 = Calendar.getInstance();
                    FileOutputStream fos = new FileOutputStream("HoaDon" + veXe.getMaVe() + "data.pdf");
                    OutputStreamWriter os = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                    PdfWriter.getInstance(document, fos);
                    document.open();

                    Paragraph tieuDe    = new Paragraph("THONG TIN TRA XE");
                    Paragraph maVe  = new Paragraph("Ma Ve:     \t" + txtMaVe.getText());
                    Paragraph loaiXe = new Paragraph("Loai Xe:    \t" + txtLoaiXe.getText());
                    Paragraph bienSo  = new Paragraph("Bien So: \t" + txtBienSo.getText());
                    Paragraph mauXe   = new Paragraph("Mau Xe:      \t" + txtMauXe.getText());
                    Paragraph khuVuc   = new Paragraph("Khu Vuc:      \t" + txtKhuVuc.getText());
                    Paragraph viTri    = new Paragraph("Vi Tri:       \t" + txtViTri.getText());
                    Paragraph ngayNhan    = new Paragraph("Ngay Nhan:       \t" + txtNgayNhan.getText());
                    Paragraph gioNhan  = new Paragraph("Gio Nhan:    \t" + txtGioNhan.getText());
                    Paragraph ngayTra = new Paragraph("Ngay Tra:    \t" + txtNgayTra.getText());
                    Paragraph gioTra  = new Paragraph("Gio Tra:     \t" + txtGioTra.getText());
                    Paragraph tongTien  = new Paragraph("Tong Tien:     \t" + txtTongTien.getText());
                    //Định dạng đoạn văn bản thứ nhất
                    tieuDe.setIndentationLeft(80);
                    tieuDe.setIndentationRight(80);
                    tieuDe.setAlignment(Element.ALIGN_CENTER);
                    tieuDe.setSpacingAfter(15);
                    //Đinh dạng đoạn văn bản thứ 2
                    maVe.setSpacingBefore(15);
                    maVe.setAlignment(Element.ALIGN_LEFT);

                    loaiXe.setSpacingBefore(10);
                    loaiXe.setAlignment(Element.ALIGN_LEFT);

                    bienSo.setSpacingBefore(10);
                    bienSo.setAlignment(Element.ALIGN_LEFT);

                    mauXe.setSpacingBefore(10);
                    mauXe.setAlignment(Element.ALIGN_LEFT);

                    khuVuc.setSpacingBefore(10);
                    khuVuc.setAlignment(Element.ALIGN_LEFT);

                    viTri.setSpacingBefore(10);
                    viTri.setAlignment(Element.ALIGN_LEFT);

                    ngayNhan.setSpacingBefore(10);
                    ngayNhan.setAlignment(Element.ALIGN_LEFT);

                    gioNhan.setSpacingBefore(10);
                    gioNhan.setAlignment(Element.ALIGN_LEFT);

                    ngayTra.setSpacingBefore(10);
                    ngayTra.setAlignment(Element.ALIGN_LEFT);

                    gioTra.setSpacingBefore(15);
                    gioTra.setAlignment(Element.ALIGN_LEFT);
                    gioTra.setSpacingAfter(25);

                    tongTien.setSpacingBefore(20);
                    tongTien.setAlignment(Element.ALIGN_CENTER);

                    document.add(tieuDe);
                    document.add(maVe);
                    document.add(loaiXe);
                    document.add(bienSo);
                    document.add(mauXe);
                    document.add(khuVuc);
                    document.add(viTri);
                    document.add(ngayNhan);
                    document.add(gioNhan);
                    document.add(ngayTra);
                    document.add(gioTra);

                    document.add(tongTien);

                    document.close();
                    JOptionPane.showMessageDialog(null, "Success Export to PDF ", "Success", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""));
                    setVisible(false);
                } catch (Exception ex) {
                }
            }
        });
    }

    public static void main(String[] args) {

    }
}
