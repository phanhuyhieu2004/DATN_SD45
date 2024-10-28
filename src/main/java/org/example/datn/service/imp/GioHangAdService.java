package org.example.datn.service.imp;

import org.example.datn.dto.SanPhamChiTietDTO;
import org.example.datn.entity.GioHangAdmin;
import org.example.datn.repository.GioHangAdRepository;
import org.example.datn.repository.SanPhamGioHangRepository;
import org.example.datn.service.IGioHangAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class GioHangAdService implements IGioHangAdService {
    @Autowired
    private GioHangAdRepository gioHangAdRepository;
    @Autowired
    private SanPhamGioHangRepository sanPhamGioHangRepository;
    @Override
    public Iterable<GioHangAdmin> findAll() {
        return gioHangAdRepository.findAll();
    }

    @Override
    public Optional<GioHangAdmin> findById(Long id) {
        return Optional.empty();
    }
    public List<SanPhamChiTietDTO> searchProductDetailsByName(String tenSanPham) {
        return gioHangAdRepository.findProductDetailsByProductName(tenSanPham);
    }
    @Override
    public GioHangAdmin save(GioHangAdmin gioHangAdmin) {
        return gioHangAdRepository.save(gioHangAdmin);
    }
    public long countCarts() {
        return gioHangAdRepository.count();
    }
    @Override
    public void remove(Long id) {
        gioHangAdRepository.deleteById(id);
    }
    @Transactional
    public void deleteGioHang(Long gioHangId) {
        // Xóa tất cả sản phẩm liên quan đến giỏ hàng
        sanPhamGioHangRepository.deleteByGioHangAdminId(gioHangId);
        // Sau đó xóa giỏ hàng
        gioHangAdRepository.deleteById(gioHangId);
    }
    public GioHangAdmin createCart() {
        GioHangAdmin gioHangAdmin = new GioHangAdmin();
        gioHangAdmin.setCreatedAt(LocalDateTime.now());
        return gioHangAdRepository.save(gioHangAdmin);
    }
}
