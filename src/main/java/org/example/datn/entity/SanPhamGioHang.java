package org.example.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.datn.service.imp.GioHangAdService;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sp_gio_hang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamGioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "gioHangAd_id", nullable = false)
    private GioHangAdmin gioHangAdmin;

    @ManyToOne
    @JoinColumn(name = "ctsp_id", nullable = false)
    private     SanPhamChiTiet sanPhamChiTiet;

    private int soLuong;

}
