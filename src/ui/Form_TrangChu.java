package ui;

import javax.swing.*;
import java.awt.*;

public class Form_TrangChu extends JPanel {
    public Form_TrangChu() {
        doShow();
    }
    public void doShow() {
        JPanel pnCenterN = new JPanel();
        JPanel pnCenterC = new JPanel();
        JLabel lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ BÃI GIỮ XE");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,24));
        lblTieuDe.setForeground(Color.RED);
        pnCenterN.setPreferredSize(new Dimension(1000,80));

        JLabel lbImage = new JLabel();
        lbImage.setIcon(new ImageIcon(getClass().getResource("/icons/background.png")));

        pnCenterN.add(lblTieuDe);
        pnCenterC.add(lbImage);

        this.setLayout(new BorderLayout());
        this.add(pnCenterN, BorderLayout.NORTH);
        this.add(pnCenterC, BorderLayout.CENTER);
    }
}
