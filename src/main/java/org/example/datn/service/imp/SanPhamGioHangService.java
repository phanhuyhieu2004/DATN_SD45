package org.example.datn.service.imp;

import org.example.datn.dto.SanPhamGioHangDTO;
import org.example.datn.entity.GioHangAdmin;
import org.example.datn.entity.SanPham;
import org.example.datn.entity.SanPhamChiTiet;
import org.example.datn.entity.SanPhamGioHang;
import org.example.datn.repository.GioHangAdRepository;
import org.example.datn.repository.SanPhamChiTietRepository;
import org.example.datn.repository.SanPhamGioHangRepository;
import org.example.datn.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamGioHangService {
    @Autowired
    private GioHangAdRepository gioHangAdRepository;
    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    SanPhamGioHangRepository sanPhamGioHangRepository;
    public List<SanPhamGioHang> getProductsByCartId(Long cartId) {
       return sanPhamGioHangRepository.findByGioHangAdmin(cartId);
    }
    public Integer getTotalQuantityInCart(Long gioHangAdminId) {
        Integer totalQuantity = sanPhamGioHangRepository.findTotalQuantityByGioHangAdminId(gioHangAdminId);
        return totalQuantity != null ? totalQuantity : 0;
    }
    public void addProductToCart(Long cartId, Long productId) {
        GioHangAdmin cart = gioHangAdRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));
        SanPhamChiTiet product = sanPhamChiTietRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        SanPhamGioHang existingCartItem = sanPhamGioHangRepository. findByGioHangAdminAndSanPhamChiTiet(cart, product);

        if (existingCartItem != null) {
            // Nếu sản phẩm đã có trong giỏ hàng, tăng số lượng lên
            existingCartItem.setSoLuong(existingCartItem.getSoLuong() + 1);
            sanPhamGioHangRepository.save(existingCartItem);
        } else {
            // Nếu chưa có, tạo mới một cartItem
            SanPhamGioHang cartItem = new SanPhamGioHang();
            cartItem.setGioHangAdmin(cart);
            cartItem.setSanPhamChiTiet(product);
            cartItem.setSoLuong(1); // Số lượng mặc định là 1
            sanPhamGioHangRepository.save(cartItem); // Lưu cartItem
        }
    }
}