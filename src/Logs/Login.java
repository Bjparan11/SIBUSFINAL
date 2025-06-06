/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logs;

import dbconnect.Session;
import dbConnect.dbConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Admin.AdminDashboard;
import User.UserDashboard;
import dbConnect.passwordHasher;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

      private void logActivity(int i_id, String action) {
        String sql = "INSERT INTO logs_table (i_id, logs_action, logs_date) VALUES (?, ?, NOW())";
        dbConnector db = new dbConnector();

        try (Connection conn = db.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, i_id);
            pst.setString(2, action);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error logging activity: " + e.getMessage());
        }
    }
    
    
    
    
    static String status1;
    static String type1;

    
    public static boolean loginAccount(String username, String password)
    {
        dbConnector connector = new dbConnector();
        try
        {
            
            String query = "SELECT * FROM user WHERE i_username='"+ username +"'";
            ResultSet resultSet = connector.getData(query);
            if(resultSet.next())
            {
                

                String hashedPass = resultSet.getString("i_password");
                String rehashedPass = passwordHasher.hashPassword(password);
                
                if(hashedPass.equals(rehashedPass))
                {
                    

                status1 = resultSet.getString("status");
                type1 = resultSet.getString("i_type");
                Session sess = Session.getInstance();
                sess.setIid(resultSet.getInt("i_id"));
                sess.setFname(resultSet.getString("i_fname"));
                sess.setLname(resultSet.getString("i_lname"));
                sess.setUsername(resultSet.getString("i_username"));
                sess.setType(resultSet.getString("i_type"));
                sess.setEmail(resultSet.getString("i_email"));
                sess.setPhone(resultSet.getString("i_phonenumber"));

                return true;
                }else
                {
                    
                    return false;
                }
            }else
            {
               
                return false;
            }
        }catch(SQLException | NoSuchAlgorithmException ex)
        {
            System.out.println(""+ex); // Always put 
            return false;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LOGIN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        password = new javax.swing.JLabel();
        pw = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        MR_clickhere1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LOGIN.setBackground(new java.awt.Color(255, 255, 255));
        LOGIN.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        LOGIN.setText("Login");
        LOGIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LOGINMouseClicked(evt);
            }
        });
        LOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINActionPerformed(evt);
            }
        });
        getContentPane().add(LOGIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 160, 40));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REAL ESTATE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/33.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 490, 410));
        getContentPane().add(uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 230, 30));

        password.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        password.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        password.setText("Password :");
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 90, 30));

        pw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwActionPerformed(evt);
            }
        });
        getContentPane().add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 230, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setText("LOGIN FORM");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 130, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 3, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("REAL ESTATE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 50));

        username.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("Username :");
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 90, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/33.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 490, 410));

        MR_clickhere1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        MR_clickhere1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MR_clickhere1.setText("Forgot Password? click here");
        MR_clickhere1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MR_clickhere1MouseClicked(evt);
            }
        });
        getContentPane().add(MR_clickhere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 210, 20));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Don't have an account? Click here");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 240, 20));

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setOpaque(true);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 280, 410));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 34, -1, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LOGINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LOGINMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LOGINMouseClicked

    private void LOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINActionPerformed
 if (uname.getText().isEmpty() && pw.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter your username & password !!");
    } else if (uname.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Username cannot be empty.");
    } else if (pw.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Password cannot be empty.");
    } else {
        if (loginAccount(uname.getText(), pw.getText())) {
            if (!status1.equalsIgnoreCase("Active")) {
                JOptionPane.showMessageDialog(null, "Pending Account, Please wait for the approval");
            } else {
                Session session = Session.getInstance();
                String i_username = session.getUsername();
               
                
                JOptionPane.showMessageDialog(null, "Login successful!");
                
                 
                logActivity(session.getIid(), session.getType() + " Logged in");

                dbConnector db = new dbConnector();
                Connection conn = db.getConnection();
                String sql = "SELECT i_image FROM user WHERE i_username = ?";
                
                try (PreparedStatement pst = conn.prepareStatement(sql)) {
                    pst.setString(1, i_username);
                    ResultSet rs = pst.executeQuery();

                    String imgPath = null;
                    if (rs.next()) {
                        imgPath = rs.getString("i_image");
                    }

                    if (type1.equalsIgnoreCase("Admin")) {
                        AdminDashboard ad = new AdminDashboard(i_username, imgPath);
                        ad.setVisible(true);
                        this.dispose();
                    } else if (type1.equalsIgnoreCase("User")) {
                        UserDashboard ud = new UserDashboard(i_username, imgPath);
                        ud.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "No account type found!");
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error retrieving image: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Account, Please register first !!");
        }
    }
    }//GEN-LAST:event_LOGINActionPerformed

    private void pwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwActionPerformed

    private void MR_clickhere1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MR_clickhere1MouseClicked
        ForgetPass efp = new ForgetPass();
        efp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MR_clickhere1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
       Registration r = new Registration();
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LOGIN;
    private javax.swing.JLabel MR_clickhere1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel password;
    private javax.swing.JPasswordField pw;
    private javax.swing.JTextField uname;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
