package com.kt3.addressservice.rest;

import com.kt3.addressservice.model.Address;
import com.kt3.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/address")
@PreAuthorize("#oauth2.hasScope('READ')")

public class AddressAPI {

    @Autowired
    private AddressService addressService;

    private AbstractMap.SimpleEntry successMessage = new AbstractMap.SimpleEntry<>("message", "success");

    private static final Logger logger = Logger.getLogger(AddressAPI.class.getName());

    /**
     * Lấy danh sách địa chỉ
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.selectAddresses());
    }

    /**
     * Lấy địa chỉ theo auth
     *
     * @param auth
     * @return
     */
    @GetMapping("/owner")
    public ResponseEntity<List<Address>> getOwnerAddresses(OAuth2Authentication auth) {
        return ResponseEntity.ok(addressService.selectAddressesByUsername(auth.getName()));
    }

    /**
     * Cập nhập thông tin một address
     * @param id
     * @param address
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> addNewAddress(@PathVariable("id") int id, @RequestBody Address address) {
        address.setId(id);
        addressService.updateAddress(address);
        return ResponseEntity.ok(successMessage);
    }

    /**
     * Thêm một địa chỉ mới
     * @param address
     * @return
     */
    @PostMapping
    public ResponseEntity<?> changeAddress(@RequestBody Address address, OAuth2Authentication auth) {
        address.setAccount_id(addressService.selectAccountByAuth(auth).getId());
        addressService.insertAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    /**
     * Xóa một địa chỉ
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") int id) {
        addressService.deleleAddress(id);
        return ResponseEntity.ok(successMessage);
    }
}
