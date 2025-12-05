package finals.proj;

import static finals.proj.DBConnection.getConnection;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudent extends javax.swing.JFrame {

    private Students parent;

    public AddStudent(Students parent) {
        this.parent = parent;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new JLabel("Name:");
        jLabel2 = new JLabel("Course:");
        jLabel3 = new JLabel("Year Level:");

        txtName = new JTextField();
        txtCourse = new JTextField();
        txtYear = new JTextField();

        btnSave = new JButton("Save");

        btnSave.addActionListener(evt -> btnSaveActionPerformed(evt));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Student");

        // 🔴 Set background to maroon
        getContentPane().setBackground(new java.awt.Color(128, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtName)
                    .addComponent(txtCourse)
                    .addComponent(txtYear)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGap(20)
        ));

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20)
        ));

        pack();
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String name = txtName.getText().trim();
        String course = txtCourse.getText().trim();
        String year = txtYear.getText().trim();

        if (name.isEmpty() || course.isEmpty() || year.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (saveStudentToDB(name, course, year)) {
            parent.addStudentRow(name, course, year);

            JOptionPane.showMessageDialog(this, "Student Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); 
        }
    }

    private boolean saveStudentToDB(String name, String course, String year) {
        String sql = "INSERT INTO students (student_name, course, year_level) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection("students"); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Failed to connect to the students database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            pstmt.setString(1, name);
            pstmt.setString(2, course);
            pstmt.setString(3, year);

            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database insertion failed: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } catch (NullPointerException npe) {
            System.err.println("Null connection object.");
            return false;
        }
    }
    


    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3;
    private javax.swing.JTextField txtName, txtCourse, txtYear;
}
