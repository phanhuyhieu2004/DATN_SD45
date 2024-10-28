package org.example.datn.repository;

import org.example.datn.entity.GioHangAdmin;
import org.example.datn.entity.SanPham;
import org.example.datn.entity.SanPhamChiTiet;
import org.example.datn.entity.SanPhamGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface SanPhamGioHangRepository extends JpaRepository<SanPhamGioHang, Long> {
    SanPhamGioHang findByGioHangAdminAndSanPhamChiTiet(GioHangAdmin cart, SanPhamChiTiet product);
    @Query(value = "SELECT gh.*, sp.gia FROM sp_gio_hang gh JOIN chi_tiet_san_pham sp ON  gh.ctsp_id = sp.id WHERE gh.gio_hang_ad_id = :gioHangAdId", nativeQuery = true)
    List<SanPhamGioHang> findByGioHangAdmin(Long gioHangAdId);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM sp_gio_hang WHERE gio_hang_ad_id = ?1", nativeQuery = true)
    void deleteByGioHangAdminId(Long gioHangId);
    @Query("SELECT SUM(s.soLuong) FROM SanPhamGioHang s WHERE s.gioHangAdmin.id = :gioHangAdminId")
    Integer findTotalQuantityByGioHangAdminId(Long gioHangAdminId);
}
