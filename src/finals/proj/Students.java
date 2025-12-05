package finals.proj;

import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame; 
import javax.swing.JOptionPane;

public class Students extends JFrame {

    public Students() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null); 
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnAddStudent = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student List");

        // 🔴 Set background to maroon
        getContentPane().setBackground(new java.awt.Color(128, 0, 0));

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Student Name", "Course", "Year Level"
            }
        ));
        jScrollPane1.setViewportView(tblStudents);

        // BACK BUTTON
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        // ADD STUDENT BUTTON
        btnAddStudent.setText("Add Student");
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        // ---- Layout ----
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(btnAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    ))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    // --- PUBLIC METHOD FOR ADDING A ROW ---
    public void addStudentRow(String name, String course, String year) {
        DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();
        model.addRow(new Object[]{name, course, year});
    }

    // --- BUTTON ACTIONS ---
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
         Dashboard dash = new Dashboard(); 
         dash.setVisible(true);
         dash.pack();
         dash.setLocationRelativeTo(null);
         this.dispose();
    }

    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {
        AddStudent form = new AddStudent(this); 
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(this);
    }

    // Variables declaration
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStudents;
}
