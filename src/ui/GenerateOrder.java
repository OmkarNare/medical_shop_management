package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.String.format;

public class GenerateOrder extends JFrame implements ActionListener {

    int srNo = 1;
    ImageIcon img = new ImageIcon("src/Images/TitleLogo.png");

    String[] columns = {
            "Sr. No", "Medicine Name", "Remaining Quantity", "Agency"
    };

    DefaultTableModel modelview = new DefaultTableModel(columns, 0);
    JTable table = new JTable(modelview);
    JPanel medicineDetailsPanel = new JPanel(new BorderLayout(5, 10));

    GenerateOrder() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Image img1 = img.getImage();
        Image img2 = img1.getScaledInstance(1100, 200, Image.SCALE_SMOOTH);
        ImageIcon Img = new ImageIcon(img2);
        JPanel labelPanel = new JPanel();
        JLabel mainLabel = new JLabel(Img);

        labelPanel.add(mainLabel);
        add(labelPanel);
        medicineDetailsPanel.setBorder(BorderFactory.createTitledBorder("Medicine Details"));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setReorderingAllowed(false);

        table.setFont(new Font("Arial", 0, 15));
        table.setRowHeight(22);

        medicineDetailsPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        add(medicineDetailsPanel);

        JButton generateOrder = new JButton("Generate Order");
        generateOrder.addActionListener(this);
        JButton home = new JButton("Home");
        home.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(home);
        buttonPanel.add(generateOrder);


        medicineDetailsPanel.add(buttonPanel, BorderLayout.SOUTH);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ImageIcon titleicon = new ImageIcon("src/Images/MainLogo.png");
        setIconImage(titleicon.getImage());

    }


    public static void main(String args[]) {
        new GenerateOrder();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       String str = e.getActionCommand();

       if(str.equals("Generate Order"))
       {
           Connection con = SecondScreen.getConnection();
           Statement smt = null;
           ResultSet rs = null;

           try {
               String qry = "SELECT `MEDICINE_NAME`, `AGENCY` ,`QUANTITY` FROM `medical_stock` WHERE `QUANTITY` <= `MINIMUM_QUANTITY`;";

               smt = con.createStatement();
               rs = smt.executeQuery(qry);

               while (rs.next()) {
                   modelview.addRow(
                           new Object[]{
                                   "" + srNo++,
                                   rs.getString(1),
                                   rs.getInt(3),
                                   rs.getString(2)
                           }

                   );
               }
           } catch (SQLException ex) {
               ex.printStackTrace();
           } catch (Exception ae) {
               JOptionPane.showMessageDialog(null, "no medicines avalable for order");
               return;
           }
       }


       if(str.equals("Home"))
       {
           dispose();
       }

    }
}

