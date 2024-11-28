package main;
import com.mysql.jdbc.Statement;
import config.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class BarangView extends javax.swing.JInternalFrame {
    private Koneksi konek;
    private javax.swing.table.DefaultTableModel tblModel;
    Statement stmt; //untuk eksekusi sql
    
    public BarangView() {
        initComponents();
    }
    
    //Definisi method--method
    private void textClear() {
        textID.setText(null);
        textName.setText(null);
        cType.setSelectedIndex(0);
        textPrice.setText("0");
        textID.setEnabled(true);
        textID.requestFocus();
    }
    
        public javax.swing.table.DefaultTableModel defaultTabelModel() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Type", "Price"}) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
        }
        
        public void refreshTabel() {
        try {
            String data[]=new String[5];
            tblModel = defaultTabelModel();
            tabelBarang.setModel(tblModel);
            String sqlSelect = "SELECT * FROM barang ORDER BY id ASC";
            konek = (Koneksi) new Koneksi();
            stmt = (Statement) konek.createStatement();
            ResultSet result= stmt.executeQuery(sqlSelect);
            while(result.next()){
                data[0]=result.getString("id");
                data[1]=result.getString("name");
                data[2]=result.getString("type");
                data[3]=result.getString("price");
                tblModel.addRow(data);
            }
        } catch (SQLException ex){
Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tampilData() {
        int row = tabelBarang.getSelectedRow();
        textID.setText(tblModel.getValueAt(row, 0).toString());
        textName.setText(tblModel.getValueAt(row,1).toString());
        cType.setSelectedItem(tblModel.getValueAt(row,2).toString());
        textPrice.setText(tblModel.getValueAt(row, 3).toString());
        textID.setEnabled(false);
    }

    private void validasiKode(String id){
        try {
            konek = (Koneksi) new Koneksi();
            stmt = (Statement) konek.createStatement();
            String sqlSelect="SELECT * FROM barang WHERE id='"+id+"' ORDER BY id ASC";
            ResultSet result= stmt.executeQuery(sqlSelect);
            if (result.first()) {
                int confr = JOptionPane.showConfirmDialog(null, "ID Barang sudah ada"
                        + " \nApakah ingin mengubah data?",
                        "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confr == 0) {
                    textID.setText(result.getString("id"));
                    textName.setText(result.getString("name"));
                    cType.setSelectedItem(result.getString("type"));
                    textPrice.setText(Double.toString(result.getDouble("price")));
                }else{
                    textClear();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
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

        textID = new javax.swing.JTextField();
        textName = new javax.swing.JTextField();
        cType = new javax.swing.JComboBox();
        textPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tombolSimpan = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("JH Furniture Shop");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        textID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textIDFocusLost(evt);
            }
        });

        cType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose", "Bed", "Table", "Chair", "Wardrobe" }));

        textPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel1.setText("Furniture's ID");

        jLabel2.setText("Furniture's Name");

        jLabel3.setText("Furniture's Type");

        jLabel4.setText("Furniture's Price");

        tombolSimpan.setText("Add/Update");
        tombolSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolSimpanActionPerformed(evt);
            }
        });

        tombolHapus.setText("Delete");
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Furniture's ID", "Furniture's Name", "Furniture's Type", "Furniture's Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        jLabel6.setText("Add New Furniture");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tombolSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cType, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tombolSimpan)
                    .addComponent(tombolHapus))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tombolSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolSimpanActionPerformed
        // TODO add your handling code here:
        String id = textID.getText();
        String name = textName.getText();
        String type = cType.getSelectedItem().toString();
        int price = Integer.parseInt(textPrice.getText());
        Koneksi konek = new Koneksi();
        Statement stmt = null;
        try {
            stmt = (Statement) konek.createStatement();
            String sqlInsert = "INSERT INTO barang "
                    + "(id,name,type,price) "
                    + "VALUES ('"+id+"', '"+name+"', '"+type+"','"+price+"')";
            stmt.executeUpdate(sqlInsert);
            JOptionPane.showMessageDialog(this, "Data have been succesfully added",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            textClear();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Validation Error \n Please fill all fields first ",
                    "Alert", JOptionPane.ERROR_MESSAGE);
            try {
                if (ex.getErrorCode() == 1062) {
                    int confr = JOptionPane.showConfirmDialog(null,"ID Barang Sudah Ada \n"
                            + "Apakah ingin mengubah data?", "Alert", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (confr == 0) {
                        String sqlUpdate = "UPDATE barang SET id='"+id+"', name='"+name+"', "
                                + "type='"+type+"', price='"+price+"'"
                                + "WHERE id ='"+id+"'";
               stmt.executeUpdate(sqlUpdate);
               JOptionPane.showMessageDialog(this,"Data have been succesfully updated",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
              } else {
                  textClear();
              }
           }
            } catch (SQLException ex1) {
                System.out.println("Error: "+ex1.getMessage());
                Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex1);
            } 
        }
        refreshTabel();
    }//GEN-LAST:event_tombolSimpanActionPerformed

    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        // TODO add your handling code here:
        try{
            String id = textID.getText();
            Statement stmt = (Statement) konek.createStatement();
            int confr = JOptionPane.showConfirmDialog(null,"Apakah data dengan ID " + id + " akan dihapus?", 
                    "Konfirmasi",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confr == 0) {
                String sqlDelete = "DELETE from barang WHERE id='"+textID.getText()+"'";
                stmt.executeUpdate(sqlDelete);
                JOptionPane.showMessageDialog(this,"Data have been succesfully deleted",
                            "information", JOptionPane.INFORMATION_MESSAGE);
                textClear();
            }
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        refreshTabel();
    }//GEN-LAST:event_tombolHapusActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        refreshTabel();
    }//GEN-LAST:event_formComponentShown

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        // TODO add your handling code here:
        tampilData();
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void textIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textIDFocusLost
        // TODO add your handling code here:
        validasiKode(textID.getText());
    }//GEN-LAST:event_textIDFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTextField textID;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textPrice;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolSimpan;
    // End of variables declaration//GEN-END:variables
}