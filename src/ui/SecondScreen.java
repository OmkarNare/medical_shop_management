package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ProfileDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import static ui.com.fonts.Font.*;


public class SecondScreen extends JFrame implements ActionListener
{

    JButton addOrderButton,searchButton,addProductButton,viewSellButton;



    Icon orderIcon = new ImageIcon("src/Images/Order1.png");
//    Icon searchIcon = new ImageIcon("out\\production\\MAIN PROJECT\\ui\\Search1.png");
    Icon productIcon = new ImageIcon("src/Images/Product1.png");
    Icon sellIcon = new ImageIcon("src/Images/Sell.png");

//    Font f = new Font("Cambria Math", Font.BOLD,20);

    private SecondScreen()
    {

        setLayout(new BorderLayout(0,0));

//        JPanel Panel = new JPanel();
//        Panel.setSize(1650,1080);

//      JPanel mainPanel = new JPanel(new BorderLayout(20,10));
        JPanel bottompanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel rightpanel = new JPanel();
        JPanel leftpanel = new JPanel();

        ImageIcon img = new ImageIcon("src/Images/TitleLogo.png");
        Image img1 = img.getImage();
        Image img2 = img1.getScaledInstance(900,200,Image.SCALE_SMOOTH);
        ImageIcon Img = new ImageIcon(img2);

        JLabel titleLable = new JLabel();

        titleLable.setIcon(Img);
        titlePanel.add(titleLable);
        add(titlePanel,BorderLayout.NORTH);

        ImageIcon med = new ImageIcon("src/Images/background.png");
        Image med1 = med.getImage();
        Image med2 = med1.getScaledInstance(1650,690,Image.SCALE_SMOOTH);
        ImageIcon meds = new ImageIcon(med2);
//        JLabel main = new JLabel(med);
//        main.add(gf);
        JLabel mo = new JLabel(meds);
        mo.setLayout(new GridLayout( 3,1,5,2));

        JLabel ao = new JLabel();
        ao.setLayout(new FlowLayout());

//        JPanel logoPanel = new JPanel();
//        JLabel logoLable = new JLabel();
//        logoLable.setText("MenuBar & MY Main Screen ");
//        logoLable.setFont(heading);
//        logoPanel.add(logoLable);
//        add(logoPanel,BorderLayout.NORTH);

        addOrderButton = new JButton("  ADD ORDER ");
//        addOrderButton.setSize(250,200);
//        addOrderButton.setLocation(500,200);
        addOrderButton.addActionListener(this);
        addOrderButton.setIcon(orderIcon);                                       //Image
        addOrderButton.setFont(text);
        addOrderButton.setBackground(Color.ORANGE);
        addOrderButton.setForeground(Color.RED);
        addOrderButton.setBorder(null);
        ao.add(addOrderButton);

//        JLabel so = new JLabel();
//        so.setLayout(new FlowLayout());
//
//        searchButton = new JButton("  SEARCH  ");
////        searchButton.setSize(250,200);
////        searchButton.setLocation(850,200);
//        searchButton.setVerticalAlignment(SwingConstants.CENTER);
//        searchButton.addActionListener(this);
//        searchButton.setIcon(searchIcon);                                       //Image
//        searchButton.setFont(text);
//        searchButton.setBackground(Color.WHITE);
//        searchButton.setBorder(null);
//        so.add(searchButton);

        JLabel po = new JLabel();
        po.setLayout(new FlowLayout());

        addProductButton = new JButton("  ADD PRODUCT  ");
//        addProductButton.setSize(250,200);
//        addProductButton.setLocation(500,500);
        addProductButton.addActionListener(this);
//        addProductButton.setFocusable(false);
        addProductButton.setIcon(productIcon);                                       //Image
        addProductButton.setFont(text);
        addProductButton.setBackground(Color.ORANGE);
        addProductButton.setForeground(Color.RED);
        addProductButton.setBorder(null);
        po.add(addProductButton);

        JLabel vo = new JLabel();
        vo.setLayout(new FlowLayout());

        viewSellButton = new JButton("  VIEW SELL  ");
//        viewSellButton.setSize(250,200);
//        viewSellButton.setLocation(850,500);
        viewSellButton.addActionListener(this);
//        viewSellButton.setFocusable(false);
        viewSellButton.setIcon(sellIcon);                                       //Image
        viewSellButton.setFont(text);
        viewSellButton.setBackground(Color.ORANGE);
        viewSellButton.setForeground(Color.RED);
        viewSellButton.setBorder(null);
        vo.add(viewSellButton);

        JLabel to = new JLabel();
        to.setLayout(new FlowLayout());

        JLabel so = new JLabel();
        so.setLayout(new FlowLayout());


        mo.add(to);
        mo.add(so);
        mo.add(ao);
        mo.add(po);
        mo.add(vo);


        add(mo,BorderLayout.CENTER);
        add(bottompanel,BorderLayout.SOUTH);
        add(rightpanel,BorderLayout.EAST);
        add(leftpanel,BorderLayout.WEST);

        /* Main Frame */
        setTitle("Main Screen");
//        setSize(1650,1080);
//        setLocation(0,0);


//        setResizable(false);             //maximize button disable.        please do this uncomment.

        setExtendedState(JFrame.MAXIMIZED_BOTH);     //maximize screen Bydefault.
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //Close Screen Operation Only In this Screen

        ImageIcon titleicon = new ImageIcon("src/Images/MainLogo.png");
        setIconImage(titleicon.getImage());


    }

    public void actionPerformed(ActionEvent ae) {
//        String s = (String)ae.getActionCommand();

        if(ae.getSource() == addOrderButton)
        {

            //dispose();
            CreateOrder.getCreateOrder();         //Singletone Method
        }
//        if(ae.getSource() == searchButton)
//        {
//
//            // dispose();
//            Search.getSearch();                  //Singletone Method
//        }
        if(ae.getSource() == addProductButton)
        {

            // dispose();
            Update.getUpdate();                 //Singletone Method
        }
        if(ae.getSource() == viewSellButton)
        {
            // dispose();
            ViewSell.getViewSell();            //Singletone Method
        }
    }
    public static SecondScreen getSecondScreen(){                     //Singletone Method
        if (obj == null){
            synchronized(SecondScreen.class){
                if (obj == null){
                    obj = new SecondScreen();//instance will be created at request time
                }
            }
        }
        return obj;
    }


    private static SecondScreen obj = new SecondScreen();


    public static Connection getConnection(){

        try
        {
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://localhost:3306/medical";
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection con = DriverManager.getConnection(databaseurl,username,password);
            //System.out.println("Connected Successfully");
            return con;

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"! Connection failed with database Please Start xampp servaer .." + e);
            return null;
            // e.printStackTrace();
        }


    }
}


