package atmsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends Frame implements ActionListener{
    
        TextField t1,t2;
        Button b1,b2,b3;
        ResultSet rs;
        PreparedStatement ps;
        Statement st;
        Connection con;
        
    Login(){
        
             super("Login Form");
             setLayout(null);
        
            t1 = new TextField();
            t1.setBounds(180, 130, 200, 22);
            t1.setFont(new Font("Comic Sans MS",Font.BOLD,16));
            t1.setBackground(Color.PINK);
            t1.setForeground(Color.BLUE);
            add(t1);
             
            t2 = new TextField();
            t2.setBounds(180, 180, 200, 22);
            t2.setFont(new Font("Comic Sans MS",Font.BOLD,20));
            t2.setBackground(Color.PINK);
            t2.setForeground(Color.BLUE);
            t2.setEchoChar('*');
            add(t2);
             
            b1 = new Button("Login");
            add(b1);
            b1.setBounds(70, 230,100, 30);
            b1.setFont(new Font("Comic Sans MS",Font.BOLD,18));
            b1.setBackground(Color.LIGHT_GRAY);
            b1.setForeground(Color.BLUE);
            
            b2 = new Button("Cancel");
            add(b2);
            b2.setBounds(210, 230, 100, 30);
            b2.setFont(new Font("Comic Sans MS",Font.BOLD,18));
            b2.setBackground(Color.LIGHT_GRAY);
            b2.setForeground(Color.BLUE);
            
            b3 = new Button("Create New Account");
            add(b3);
            b3.setBounds(90, 280, 200, 30);
            b3.setFont(new Font("Comic Sans MS",Font.BOLD,18));
            b3.setBackground(Color.LIGHT_GRAY);
            b3.setForeground(Color.BLUE);
            
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            
            try{
            	  /* Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                con = DriverManager.getConnection("Jdbc:Odbc:atminfi"); */
            	  Class.forName("com.mysql.jdbc.Driver");  
             	  
                 	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","");  
                      System.out.println("Connected to the database");
            	
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
    public void paint(Graphics g){
        
        g.setColor(Color.BLUE);
        g.setFont(new Font("Lucida Handwriting",Font.BOLD,26));
        g.drawString("Login Form", 100, 80);
        
        g.setColor(Color.red);
        g.setFont(new Font("Algerian",Font.ITALIC,22));
        g.drawString("Account No :", 20, 150);
        
        g.setColor(Color.red);
        g.setFont(new Font("Algerian",Font.ITALIC,22));
        g.drawString("Password   :", 20, 200);
    }
    
        @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==b1){
          
            String s1="",s2="";
            String bal = "";
            String acc = t1.getText();
            String pass = t2.getText();
              
          try{
              st = con.createStatement();
              
              ResultSet rs = st.executeQuery("select * from info where account='" +t1.getText()+"' AND password='"+t2.getText()+"'");
              
              while(rs.next()){
                  
                  s1 = rs.getString(1);
                  s2 = rs.getString(2);
                  bal = rs.getString(6);
                  
                  if(pass.equalsIgnoreCase(s2)){
                      JOptionPane.showMessageDialog(null, "You have Login Successfullyy !!!!","Message",JOptionPane.INFORMATION_MESSAGE);
                      Transaction ts = new Transaction();
                      Transaction.search = s1;
                      ts.setSize(500, 500);
                      ts.setVisible(true);
                      ts.setBackground(Color.pink);
                      ts.setResizable(false);
                      dispose();
                      
                  }
                  else{
                      
                      JOptionPane.showMessageDialog(null, "Invalid Password","Eror",JOptionPane.ERROR_MESSAGE);
                      t1.setText("");
                      t2.setText("");
                  }
                      
              }
              
              if(s1==""){
                  
                  JOptionPane.showMessageDialog(null, "Invalid Account No","Error",JOptionPane.ERROR_MESSAGE);
                  t1.setText("");
                  t2.setText("");
                  st.close();
                  
              }
          }catch(Exception ex){
            
            System.err.println(ex.getMessage());
        }
            
        }
        
        if(e.getSource()==b2){
            
             int rply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit ?","Error",JOptionPane.YES_NO_OPTION);
            
            if(rply==JOptionPane.YES_OPTION){
                
                System.exit(0);
            }
            else
            {
                
                
            }
        }
        
        if(e.getSource()==b3){
            
            Registration reg = new Registration();
            reg.setVisible(true);
            reg.setSize(450, 500);
            reg.setResizable(false);
            dispose();
        }
        
    }
    
}
