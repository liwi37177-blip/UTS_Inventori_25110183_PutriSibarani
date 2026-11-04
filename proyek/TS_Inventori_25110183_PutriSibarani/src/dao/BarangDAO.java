package dao;

import model.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {
    private final Connection conn;

    public BarangDAO() {
        conn = Koneksi.getConnection();
    }

    public boolean insert(Barang barang) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, kategori, stok) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, barang.getKodeBarang());
            st.setString(2, barang.getNamaBarang());
            st.setString(3, barang.getKategori());
            st.setInt(4, barang.getStok());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Barang barang) {
        String sql = "UPDATE barang SET nama_barang = ?, kategori = ?, stok = ? WHERE kode_barang = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, barang.getNamaBarang());
            st.setString(2, barang.getKategori());
            st.setInt(3, barang.getStok());
            st.setString(4, barang.getKodeBarang());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String kodeBarang) {
        String sql = "DELETE FROM barang WHERE kode_barang = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, kodeBarang);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Barang b = new Barang();
                b.setKodeBarang(rs.getString("kode_barang"));
                b.setNamaBarang(rs.getString("nama_barang"));
                b.setKategori(rs.getString("kategori"));
                b.setStok(rs.getInt("stok"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}