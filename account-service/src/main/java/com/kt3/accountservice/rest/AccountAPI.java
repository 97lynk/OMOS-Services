package com.kt3.accountservice.rest;

import com.kt3.accountservice.model.Account;
import com.kt3.accountservice.servive.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author 97lynk
 */
@RestController
@RequestMapping("/account")
@PreAuthorize("#oauth2.hasAnyScope('READ')")
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = Logger.getLogger(AccountAPI.class.getName());

    AbstractMap.SimpleEntry successMessage = new AbstractMap.SimpleEntry<>("message", "success");
    /**
     * lấy hết danh sách account
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.selectAccounts());
    }

    /**
     * Thêm account mới - hiện thực chức năng đăng kí
     *
     * @param account
     */
    @PostMapping
    public ResponseEntity<?> addNewAccount(@RequestBody Account account) {
        accountService.insertAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    /**
     * Thay đổi 1 account cụ thể - hiện thực chức năng đổi mật khẩu
     *
     * @param account profile có id cũ, có các thuộc tính mới sẽ cập nhật
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> changeAccount(@PathVariable("id") int id, @RequestBody Account account) {
        account.setId(id);
        accountService.updateAccount(account);
        return ResponseEntity.ok(successMessage);
    }

    /**
     * Xóa 1 account cụ thể
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(successMessage);

    }

}
