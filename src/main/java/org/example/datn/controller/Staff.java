package org.example.datn.controller;
import org.example.datn.dto.SanPhamChiTietDTO;
import org.example.datn.dto.SanPhamGioHangDTO;
import org.example.datn.entity.GioHangAdmin;
import org.example.datn.entity.Profile;
import org.example.datn.entity.SanPhamGioHang;
import org.example.datn.service.ProfileService;
import org.example.datn.service.imp.SanPhamGioHangService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.example.datn.service.imp.GioHangAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class Staff {
    @Autowired
    private GioHangAdService gioHangAdService;
    @Autowired
    private SanPhamGioHangService sanPhamGioHangService;
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create-cart")
    public String createCart(RedirectAttributes redirectAttributes) {
        long currentCartCount = gioHangAdService.countCarts();

        if (currentCartCount >= 10) {
            redirectAttributes.addFlashAttribute("message", "Bạn đã đạt đến giới hạn 10 giỏ hàng. Vui lòng xóa bớt giỏ hàng để tạo mới.");
        } else {
            gioHangAdService.createCart();
            redirectAttributes.addFlashAttribute("message", "Giỏ hàng mới đã được tạo thành công!");
        }
        return "redirect:/staff";
    }

    @GetMapping("/staff")
    public String getAllCarts(Model model) {
        List<GioHangAdmin> gioHangAdmins = (List<GioHangAdmin>) gioHangAdService.findAll();
        model.addAttribute("carts", gioHangAdmins);
        return "staff/index";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<SanPhamChiTietDTO> searchSanPham(@RequestParam("tenSanPham") String tenSanPham) {
        return gioHangAdService.searchProductDetailsByName(tenSanPham);
    }
    @GetMapping("/searchAccount")
    @ResponseBody

    public List<Profile> searchProfiles(@RequestParam String name) {
        return profileService.findProfilesByName(name);
    }
    @PostMapping("/addProduct")
    public ResponseEntity<Void> addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) {
        try {
            sanPhamGioHangService.addProductToCart(cartId, productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{cartId}/products")
    @ResponseBody
    public List<SanPhamGioHang> getProductsInCart(@PathVariable Long cartId) {
        return sanPhamGioHangService.getProductsByCartId(cartId);
    }
    @GetMapping("/totalQuantity")
    @ResponseBody
    public Integer getTotalQuantityByCartId(@RequestParam Long gioHangId) {
        int totalQuantity = sanPhamGioHangService.getTotalQuantityInCart(gioHangId);
        return totalQuantity;
    }
    @DeleteMapping("/{id}")
    public String deleteGioHang(@PathVariable Long id) {
        gioHangAdService.deleteGioHang(id);
        return "staff/index";
    }
}