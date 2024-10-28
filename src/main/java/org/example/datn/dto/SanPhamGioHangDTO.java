package org.example.datn.dto;

import lombok.Data;
import org.example.datn.entity.SanPham;
@Data
public class SanPhamGioHangDTO {

        private Long id;           // ID của sản phẩm trong giỏ hàng
        private Long gioHangAdId; // ID của giỏ hàng admin
        private Long sanPhamId;   // ID sản phẩm
        private int soLuong;      // Số lượng sản phẩm
        private Double gia;       // Giá sản phẩm

 // Đảm bảo có trường này

    public SanPhamGioHangDTO() {
    }

    public SanPhamGioHangDTO(Long id, Long gioHangAdId, Long sanPhamId, int soLuong, Double gia) {
        this.id = id;
        this.gioHangAdId = gioHangAdId;
        this.sanPhamId = sanPhamId;
        this.soLuong = soLuong;
        this.gia = gia;
    }
}

