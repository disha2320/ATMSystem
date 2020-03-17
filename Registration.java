package atmsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Registration extends JFrame implements ActionListener{
    
    JLabel l1,l2,l3,l4,l5,l6,lbl_title;
    JButton btn_reg,btn_cancel;
    JTextField t1,t2,t3,t4,t5,t6;
    Connection cont;
    Statement st;
    PreparedStatement ps;
    
    Registration(){
        super("Registration Form");
        setLayout(null);
        
        lbl_title = new JLabel("Registration Form");
        lbl_title.setBounds(80, 30, 350, 30);
        lbl_title.setFont(new Font("Elephant",Font.ITALIC,30));
        lbl_title.setForeground(Color.BLUE);
        add(lbl_title);
        
        l1 = new JLabel("Account No  :");
        l1.setBounds(40, 100, 200, 30);
        l1.setFont(new Font("Cataneo BT",Font.BOLD,18));
        l1.setForeground(Color.RED);
        add(l1);
        
        l2 = new JLabel("Password     :");
        l2.setBounds(40, 150, 200, 30);
        l2.setFont(new Font("Cataneo BT",Font.BOLD,18));
        l2.setForeground(Color.blue);
        
        add(l2);
        
        l3 = new JLabel("SurName     :");
        l3.setBounds(40, 200, 200, 30);
        l3.setFont(new Font("Cataneo BT",Font.BOLD,18));
        l3.setForeground(Color.RED);
        add(l3);
        
        l4 = new JLabel("YourName   :");
        l4.setBounds(40, 250, 200, 30);
        l4.setFont(new Font("Cataneo BT",Font.BOLD,18));
        l4.setForeground(Color.blue);
        add(l4);
        
        l5 = new JLabel("FatherName :");
        l5.setBounds(40, 300, 200, 30);
        l5.setFont(new Font("Cataneo BT",Font.BOLD,18));
        l5.setForeground(Color.RED);
        add(l5);
        
        l6 = new JLabel("Balance  :");
        l6.setBounds(40, 350, 200, 30);
        l6.setFont(new Font("Cataneo BT",Font.BOLD,18));
        l6.setForeground(Color.RED);
        add(l6);
        
        t1 = new JTextField();
        t1.setBounds(150, 100, 220, 24);
        t1.setToolTipText("Enter Account No");
        t1.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        t1.setForeground(Color.black);
        t1.setBackground(Color.PINK);
        add(t1);
        
        t2 = new JPasswordField();
        t2.setBounds(150, 150, 220, 24);
        t2.setToolTipText("Enter Password");
        t2.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        t2.setForeground(Color.black);
        t2.setBackground(Color.PINK);
        add(t2);
        
        t3 = new JTextField();
        t3.setBounds(150, 200, 220, 24);
        t3.setToolTipText("Enter SurName");
        t3.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        t3.setForeground(Color.black);
        t3.setBackground(Color.PINK);
        add(t3);
        
        t4 = new JTextField();
        t4.setBounds(150, 250, 220, 24);
        t4.setToolTipText("Enter YourName");
        t4.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        t4.setForeground(Color.black);
        t4.setBackground(Color.PINK);
        add(t4);
        
        t5 = new JTextField();
        t5.setBounds(150, 300, 220, 24);
        t5.setToolTipText("Enter FatherName");
        t5.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        t5.setForeground(Color.black);
        t5.setBackground(Color.PINK);
        add(t5);
        
        t6 = new JTextField();
        t6.setBounds(150, 350, 220, 24);
        t6.setToolTipText("balance");
        t6.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        t6.setForeground(Color.black);
        t6.setBackground(Color.PINK);
        add(t6);
       
        
        btn_reg = new JButton("Register");
        btn_reg.setBounds(60, 390, 100, 30);
        btn_reg.setBackground(Color.ORANGE);
        btn_reg.setForeground(Color.BLUE);
        btn_reg.setFont(new Font("Comic Sans MS",Font.BOLD,16));
        add(btn_reg);
        
        btn_cancel = new JButton("Cancel");
        btn_cancel.setBounds(200, 390, 100, 30);
        btn_cancel.setBackground(Color.ORANGE);
        btn_cancel.setForeground(Color.BLUE);
        btn_cancel.setFont(new Font("Comic Sans MS",Font.BOLD,16));
        add(btn_cancel);
        
        btn_reg.addActionListener(this);
        btn_cancel.addActionListener(this);
        
       try{  /* Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       con = DriverManager.getConnection("Jdbc:Odbc:atminfi");
    	  */
    	   Class.forName("com.mysql.jdbc.Driver");  
     	  
       	cont=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");  
            System.out.println("Connected to the database");
          // return con1;
                
            }catch(Exception ex){
                
                JOptionPane.showMessageDialog(null, "Failed Connection","Error",JOptionPane.ERROR_MESSAGE);
            }
       addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
            dispose();
            }
});
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==btn_reg){
            
            try{
               if(t1.getText().length()==0 || t2.getText().length()==0 || t3.getText().length()==0 || t4.getText().length()==0 || t5.getText().length()==0||t6.getText().length()==0){
            
            JOptionPane.showMessageDialog(null, "Please Required All Field","Error",JOptionPane.ERROR_MESSAGE);
        }  
               else{
                st = cont.createStatement();
                
                ps = cont.prepareStatement("INSERT INTO info (account,password,surname,yourname,fathername,balance) VALUES(?,?,?,?,?,?)");
                ps.setString(1, t1.getText());
                ps.setString(2, t2.getText());
                ps.setString(3, t3.getText());
                ps.setString(4, t4.getText());
                ps.setString(5, t5.getText());
                ps.setString(6, t6.getText());
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "New Account  has been created Succesfullyy","Message",JOptionPane.INFORMATION_MESSAGE);
                
                st.close();
                     Login lg = new Login();
                     lg.setVisible(true);
                     lg.setSize(400, 350);
                     lg.setBackground(Color.ORANGE);
                     lg.setResizable(false);
                     dispose();
               }       
            }catch(Exception ex){
                
                System.err.println(ex.getMessage());
            }
        }
        
        if(e.getSource()==btn_cancel){
            
                     Login lg = new Login();
                     lg.setVisible(true);
                     lg.setSize(400, 350);
                     lg.setBackground(Color.ORANGE);
                     lg.setResizable(false);
                     dispose();
        }
        
    }
}
