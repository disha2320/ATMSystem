package atmsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transaction extends Frame implements ActionListener{
    
   public static  String search;
    JLabel lbl_title,lbl;
    Button btn_chk,btn_wid,btn_dep,btn_log;
    Connection con;
    Statement st;
    
    Transaction(){
        super("Transaction");
        setLayout(null);
        lbl = new JLabel("");
        lbl.setText(search);
        
        lbl_title = new JLabel("Please Select a Button");
        lbl_title.setBounds(80, 50, 350, 30);
        lbl_title.setFont(new Font("Elephant",Font.ITALIC,30));
        lbl_title.setForeground(Color.BLUE);
        add(lbl_title);
        
        btn_chk = new Button("Check Balance");
        btn_chk.setBounds(50, 200, 150, 35);
        btn_chk.setBackground(Color.ORANGE);
        btn_chk.setForeground(Color.DARK_GRAY);
        btn_chk.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        add(btn_chk);
        
        btn_wid = new Button("Withdraw");
        btn_wid.setBounds(250, 200, 150, 35);
        btn_wid.setBackground(Color.ORANGE);
        btn_wid.setForeground(Color.DARK_GRAY);
        btn_wid.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        add(btn_wid);
        
        btn_dep = new Button("Deposit");
        btn_dep.setBounds(50, 300, 150, 35);
        btn_dep.setBackground(Color.ORANGE);
        btn_dep.setForeground(Color.DARK_GRAY);
        btn_dep.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        add(btn_dep);
        
        btn_log = new Button("Logout");
        btn_log.setBounds(250, 300, 150, 35);
        btn_log.setBackground(Color.ORANGE);
        btn_log.setForeground(Color.DARK_GRAY);
        btn_log.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        add(btn_log);
        
        btn_chk.addActionListener(this);
        btn_wid.addActionListener(this);
        btn_dep.addActionListener(this);
        btn_log.addActionListener(this);
        
        try{/*
        	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("Jdbc:Odbc:atminfi");
         	  */
         	   Class.forName("com.mysql.jdbc.Driver");  
          	  
            	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");  
             System.out.println("Connected to the database");
               
            }catch(Exception ex){
                
                JOptionPane.showMessageDialog(null, "Failed Connection","Error",JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public void actionPerformed(ActionEvent e){
        
        String str_src = search;
        
        JLabel lbl_dep = new JLabel("");
        JLabel lbl_bal = new JLabel("");
        JLabel lbl_wid = new JLabel("");
        JLabel lbl_log = new JLabel("");
        
      String str_dep = "";
      
      int depo=0;
      int empblnc;
      int mainblnc;
      
      String str_logout = "";
      
      String str_with = "";
      int withdraw;
      
      
      try{
          
          st = con.createStatement();
          
          if(btn_dep==e.getSource()){
              
          ResultSet rs = st.executeQuery("SELECT * FROM info WHERE Account = '" + search+"'");
            
            while(rs.next()){
                
                lbl_bal.setText(rs.getString(6));
            }   
            
            mainblnc = Integer.parseInt(lbl_bal.getText());
            
            str_dep = JOptionPane.showInputDialog(null,"Enter Amount","Deposit",JOptionPane.PLAIN_MESSAGE);
            
            lbl_dep.setText(str_dep);
            
            depo = Integer.parseInt(lbl_dep.getText());
            
            empblnc = depo + mainblnc;
            
            JOptionPane.showMessageDialog(null, "You have Deposited : "+str_dep,"Message",JOptionPane.INFORMATION_MESSAGE);
            
            lbl_log.setText(String.valueOf(empblnc));
            
            PreparedStatement ps = con.prepareStatement("UPDATE info SET Balance = '" + lbl_log.getText()+  "'WHERE Account = '" + search + "'");
            ps.executeUpdate();
            
            st.close();
            addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
            dispose();
            }
});
          }
         
         if(btn_wid==e.getSource()){
             
          ResultSet rs = st.executeQuery("SELECT * FROM info WHERE Account = '" + search+"'");
           
                while(rs.next()){
                    
                    lbl_bal.setText(rs.getString(6));
                }
                
                mainblnc = Integer.parseInt(lbl_bal.getText());
                str_with = JOptionPane.showInputDialog(null,"Enter Amount","Withdraw",JOptionPane.PLAIN_MESSAGE);
                
                lbl_wid.setText(str_with);
                
                withdraw = Integer.parseInt(lbl_wid.getText());
                
                if(mainblnc<withdraw){
                    
                    JOptionPane.showMessageDialog(null, "Your Amount is Low : ","Message",JOptionPane.ERROR_MESSAGE);
                     Transaction ts = new Transaction();
                      ts.setSize(500, 500);
                      ts.setVisible(true);
                      ts.setBackground(Color.pink);
                      ts.setResizable(false);
                      dispose();
                }
                else{
                    
                    empblnc = mainblnc-withdraw;
                    
                    JOptionPane.showMessageDialog(null, "You have Withdrawn : "+ str_with,"Message",JOptionPane.INFORMATION_MESSAGE);
                    
                    lbl_log.setText(String.valueOf(empblnc));
                    
                 	PreparedStatement ps = con.prepareStatement("UPDATE info SET Balance = '" + lbl_log.getText()+  "'WHERE Account = '" + search + "'");
                    ps.executeUpdate();
                   
                }
                   st.close();
                   
         }
         
         if(btn_chk==e.getSource()){
             
             ResultSet rs = st.executeQuery("SELECT * FROM info WHERE Account= '" +search+"'");
           
                while(rs.next()){
                    
                    lbl_bal.setText(rs.getString(6));
                    
                }
                str_logout = lbl_bal.getText();
                
                JOptionPane.showMessageDialog(null, "Your Balance is : "+str_logout,"Balance",JOptionPane.INFORMATION_MESSAGE);
                st.close();
         }
          
      }catch(Exception ex){
          
       System.err.println(ex.getMessage());
      }
      
      if(btn_log==e.getSource()){
          
         lbl.setText("");
         System.out.println(lbl.getText());
            Login lg = new Login();
            lg.setVisible(true);
            lg.setSize(400, 350);
            lg.setBackground(Color.ORANGE);
            lg.setResizable(false);
            dispose();
            
      }
        
    }
}
