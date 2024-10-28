package org.example.datn.repository;

import org.example.datn.dto.SanPhamChiTietDTO;
import org.example.datn.entity.GioHangAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangAdRepository extends JpaRepository<GioHangAdmin,Long> {

    @Query("SELECT new org.example.datn.dto.SanPhamChiTietDTO(spct.id,sp.anh, sp.ten, ms.ten, s.ten, spct.gia, spct.soLuong) " +
            "FROM SanPhamChiTiet spct " +

            "JOIN spct.idSanPham sp " +
            "JOIN spct.idMauSac ms " +
            "JOIN spct.idSize s " +
            "WHERE sp.ten LIKE %:tenSanPham%")
    List<SanPhamChiTietDTO> findProductDetailsByProductName(@Param("tenSanPham") String tenSanPham);
}
