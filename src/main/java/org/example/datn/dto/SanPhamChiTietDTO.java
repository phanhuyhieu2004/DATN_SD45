package org.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SanPhamChiTietDTO {
    private Long id;
    private String anh;          // Ảnh sản phẩm
    private String tenSanPham;   // Tên sản phẩm
    private String mauSac;       // Tên màu sắc
    private String kichCo;       // Tên kích cỡ
    private Double gia;          // Giá sản phẩm
    private Integer soLuong;     // Số lượng sản phẩm
}