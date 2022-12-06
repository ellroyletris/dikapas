
package com.controller;

import com.view.tampilan;
import java.sql.SQLException;

/**
 *
 * @author PC LAB 2
 */
public interface controller_siswa {
    public void Simpan(tampilan t) throws SQLException;
    public void Reset (tampilan t) throws SQLException;
    public void Ubah (tampilan t) throws SQLException;
    public void Hapus (tampilan t) throws SQLException;
    public void Tampil (tampilan t) throws SQLException;
    public void KlikTabel (tampilan t) throws SQLException;
}
