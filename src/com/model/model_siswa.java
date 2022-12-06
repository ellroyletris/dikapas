
package com.model;

import com.controller.controller_siswa;
import com.koneksi.koneksi;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import com.view.tampilan;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author PC LAB 2
 */
public class model_siswa implements controller_siswa {
    String jk;

    @Override
    public void Simpan(tampilan t) throws SQLException {
       if (t.rbLaki.isSelected()){
            jk = "Laki-laki";
        } else {
            jk = "Perempuan";
        }
        try{
Connection con = koneksi.getcon();
String sql = "Insert Into abumat Values(?,?,?,?)";
PreparedStatement prepare = con.prepareStatement(sql);
prepare.setString(1, t.txtNIS.getText());
prepare.setString(2, t.txtNama.getText());
prepare.setString(3, jk);
prepare.setString(4, (String) t.cbJurusan.getSelectedItem());
prepare.executeUpdate();
JOptionPane.showMessageDialog(null, "Data berhasil diubah");
prepare.close();
} catch (Exception e){
System.out.println(e);
} finally {
Tampil(t);
    
        } 
    }

    @Override
    public void Reset(tampilan t) throws SQLException {
        t.txtNama.setText("");
        t.txtNIS.setText("");
       
        
    }

    @Override
    public void Ubah(tampilan t) throws SQLException {
        
        if (t.rbLaki.isSelected()){
            jk = "Laki-laki";
        } else {
            jk = "Perempuan";
        }
    try {
    Connection con = koneksi.getcon();
    String sql = "UPDATE abumat SET nama=?, jenis_kelamin=?, " + "jurusan=? WHERE NIS=?";
    PreparedStatement prepare = con.prepareStatement(sql);
    prepare.setString(4, t.txtNIS.getText());
    prepare.setString(1, t.txtNama.getText());
    prepare.setString(2, jk);
    prepare.setString(3, (String) t.cbJurusan.getSelectedItem());
    prepare.executeUpdate();
    JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
    prepare.close();
} catch (Exception e){
        System.out.println(e);
} finally {
    Tampil(t);
    Reset(t);
}
    }

    @Override
    public void Hapus(tampilan t) throws SQLException {
        try{
            Connection con = koneksi.getcon();
            String sql ="DELETE FROM abumat WHERE NIS =?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, t.txtNIS.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
        } catch (Exception e){
            System.out.println(e);
    } finally{
           Tampil(t);
    Reset(t); 
        }
                
      }

    @Override
    public void Tampil(tampilan t) throws SQLException {
        t.tblmodel.getDataVector().removeAllElements();
     t.tblmodel.fireTableDataChanged();
        try {
            Connection con = koneksi.getcon();
            Statement stt = con.createStatement();          
           String sql = "SELECT * FROM abumat";
           ResultSet res = stt.executeQuery(sql);
            while (res.next()) {                
                Object[] ob = new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                t.tblmodel.addRow(ob);
            }
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void KlikTabel(tampilan t) throws SQLException {
        try {
        int pilih = t.table.getSelectedRow();
        if (pilih == -1) {
            return;
        }
        t.txtNIS.setText(t.tblmodel.getValueAt(pilih, 0).toString());
        t.txtNama.setText(t.tblmodel.getValueAt(pilih, 1).toString());
        t.cbJurusan.setSelectedItem(t.tblmodel.getValueAt(pilih, 3).toString());
        jk = String.valueOf(t.tblmodel.getValueAt(pilih, 2));
    } catch (Exception e) {
    if (t.rbLaki.getText().equals(jk)) {
    t.rbLaki.setSelected(true);
} else {
    t.rbPerempuan.setSelected(true);
} 

    }
        }
            }
    