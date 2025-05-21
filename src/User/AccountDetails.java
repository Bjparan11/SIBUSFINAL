/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Admin.E_Add_Recovery;
import Logs.Login;
import dbConnect.ImageHandler;
import dbConnect.dbConnector;
import dbconnect.Session;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Windows 10
 */
public class AccountDetails extends javax.swing.JFrame {
       private static String userImagePath = null;
      private static String i_username;
    /**
     * Creates new form AccountDetails
     */
    public AccountDetails(String username, String imgPath) {
        initComponents();
        
         this.i_username = username;
        
        userImagePath = imgPath;
        loadUserData();
    }

    public static String Phone, usname;

    public boolean updateCheck()
    {
        dbConnector dbc = new dbConnector();
        Session sess = Session.getInstance();
        String c = acc_cnum.getText().trim();
        String us = acc_username.getText().trim();
        
        try
        {
            System.out.println("1");
            String query = "SELECT * FROM user WHERE (i_username='"+ us +"'OR i_phone='"+ c +"') AND i_id != '"+sess.getIid()+"'";
            ResultSet resultSet = dbc.getData(query);
            if(resultSet.next())
            {
                Phone = resultSet.getString("i_phone");
                if(Phone.equals(c))
                {
                    JOptionPane.showMessageDialog(null, "Phone Number is Already Used");
                    acc_cnum.setText("");
                }
                
                usname = resultSet.getString("i_username");
                if(usname.equals(us))
                {
                    JOptionPane.showMessageDialog(null, "Username is Already Used");
                    acc_username.setText("");
                }
                return true;
            }else
            {
                return false;
            }
        }catch(SQLException ex)
        {
            System.out.println(""+ex);
            return false;
        }
    }
       

     private String saveImageToFolder(String email) {
    try {
        Icon icon = profile.getIcon();
        if (icon instanceof ImageIcon) {
            Image image = ((ImageIcon) icon).getImage();
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.dispose();

            String folderPath = "src/usersImages/";
            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = folderPath + email + ".jpg";
            File outputFile = new File(filePath);
            ImageIO.write(bufferedImage, "jpg", outputFile);

            return filePath;
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return null;
}
       private void loadUserData() {
        String url = "jdbc:mysql://localhost:3306/property";
        String dbUser = "root";
        String dbPass = "";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass)) {
            String query = "SELECT i_image FROM user WHERE i_username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, i_username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String imagePath = rs.getString("i_image");

                        if (imagePath != null && !imagePath.isEmpty()) {
                            File file = new File(imagePath);
                            if (file.exists()) {
                                ImageIcon icon = new ImageIcon(imagePath);
                                Image img = icon.getImage().getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
                                profile.setIcon(new ImageIcon(img));
                            } else {
                                JOptionPane.showMessageDialog(this, "Image file not found: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
       
       private void logActivity(int Iid, String action) {
        String sql = "INSERT INTO logs_table (i_id, logs_action, logs_date) VALUES (?, ?, NOW())";
        dbConnector db = new dbConnector();

        try (Connection conn = db.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, Iid);
            pst.setString(2, action);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error logging activity: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        select = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        acc_Iid = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Save = new javax.swing.JButton();
        acc_username = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        acc_lname = new javax.swing.JTextField();
        acc_fname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        acc_cnum = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        acc_type = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ACCOUNT DETAILS");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 50));

        select.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select.setText("Select File");
        select.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        select.setOpaque(true);
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectMouseExited(evt);
            }
        });
        getContentPane().add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 80, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Update Profile");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 90, 30));

        Back.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Back.setText("Back");
        Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackMouseClicked(evt);
            }
        });
        getContentPane().add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 120, 30));

        acc_Iid.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        acc_Iid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_Iid.setText("USER ID");
        getContentPane().add(acc_Iid, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 120, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 210, 180));

        jLabel7.setBackground(new java.awt.Color(153, 255, 255));
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 270, 390));

        Save.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Save.setText("Save");
        Save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveMouseClicked(evt);
            }
        });
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        getContentPane().add(Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 120, 30));

        acc_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acc_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(acc_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 230, 30));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Username:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 80, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Last Name:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 80, 30));

        acc_lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acc_lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_lnameActionPerformed(evt);
            }
        });
        getContentPane().add(acc_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 230, 30));

        acc_fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acc_fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_fnameActionPerformed(evt);
            }
        });
        getContentPane().add(acc_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 230, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("  First Name:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 90, 30));

        acc_cnum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        acc_cnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_cnumActionPerformed(evt);
            }
        });
        getContentPane().add(acc_cnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 230, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Phone :");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 80, 30));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Recovery");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 223, 38));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setText("Change Password");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 220, 40));

        jLabel2.setBackground(new java.awt.Color(204, 255, 255));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 490, 390));

        acc_type.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        acc_type.setText("Type ");
        getContentPane().add(acc_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 240, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseClicked
        UserDashboard ud = new UserDashboard(i_username, userImagePath);
        ud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackMouseClicked

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        
    }//GEN-LAST:event_SaveActionPerformed

    private void acc_fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acc_fnameActionPerformed

    private void acc_lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acc_lnameActionPerformed

    private void acc_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acc_usernameActionPerformed

    private void acc_cnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_cnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acc_cnumActionPerformed

    private void SaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveMouseClicked
                                         
    }//GEN-LAST:event_SaveMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       Session sess = Session.getInstance();
       if(sess.getIid() == 0)
       {
           JOptionPane.showMessageDialog(null,"No Account, Login FIrst");
           Login l = new Login();
           l.setVisible(true);
           this.dispose();
       }else
       {
           acc_fname.setText(" " + sess.getFname());
           acc_lname.setText(" " + sess.getLname());
           acc_username.setText(" " + sess.getUsername());
           acc_type.setText(" " + sess.getType());
           acc_cnum.setText(""+ sess.getPhone());
           acc_Iid.setText(" " + sess.getIid());
       }
    }//GEN-LAST:event_formWindowActivated

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        E_Add_Recovery ear = new E_Add_Recovery(i_username, userImagePath);
        ear.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
       ChangePassword cp = new ChangePassword(i_username, userImagePath);
        cp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3MouseClicked

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
        ImageHandler.chooseImage(profile);
    }//GEN-LAST:event_selectMouseClicked

    private void selectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseEntered

    }//GEN-LAST:event_selectMouseEntered

    private void selectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseExited

    }//GEN-LAST:event_selectMouseExited

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        String url = "jdbc:mysql://localhost:3306/property";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String selectQuery = "SELECT i_username FROM user WHERE i_username = ?";
            try (PreparedStatement selectPstmt = conn.prepareStatement(selectQuery)) {
                selectPstmt.setString(1, i_username);
                try (ResultSet rs = selectPstmt.executeQuery()) {
                    if (rs.next()) {
                        // Save Image
                        String imagePath = saveImageToFolder(i_username); // Save and get the file path

                        // Update user details along with the image path
                        String updateQuery = "UPDATE user SET  i_image = ? WHERE i_username = ?";
                        try (PreparedStatement updatePstmt = conn.prepareStatement(updateQuery)) {
                            updatePstmt.setString(1, imagePath);
                            updatePstmt.setString(2, i_username);

                            int rowsUpdated = updatePstmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(this, "Account information updated successfully!");

                                Session session = Session.getInstance();
                                int userId = session.getIid();

                                if (userId != -1) {
                                    logActivity(userId, "Updated account image for user: " + i_username);
                                } else {
                                    System.err.println("Session user ID not set. Cannot log password change activity.");
                                }

                            } else {
                                JOptionPane.showMessageDialog(this, "Update failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(AccountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 String imgPath = "path/to/default/image.png";
                new AccountDetails(i_username, imgPath).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Save;
    private javax.swing.JLabel acc_Iid;
    public javax.swing.JTextField acc_cnum;
    public javax.swing.JTextField acc_fname;
    public javax.swing.JTextField acc_lname;
    private javax.swing.JLabel acc_type;
    public javax.swing.JTextField acc_username;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel select;
    // End of variables declaration//GEN-END:variables
}
