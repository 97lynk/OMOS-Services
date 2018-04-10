package com.kt3.accountservice.rest;

import com.kt3.accountservice.model.Account;
import com.kt3.accountservice.servive.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    /**
     * lấy hết danh sách account
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts() {
        return accountService.selectAccounts();
    }

    /**
     * Thêm account mới - hiện thực chức năng đăng kí
     * @param account
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAccount(@RequestBody Account account) {
        accountService.insertAccount(account);
    }

    /**
     * Thay đổi 1 account cụ thể - hiện thực chức năng đổi mật khẩu
     *
     * @param account profile có id cũ, có các thuộc tính mới sẽ cập nhật
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeAccount(@PathVariable("id") int id, @RequestBody Account account) {
        account.setId(id);
        accountService.updateAccount(account);
    }

    /**
     * Xóa 1 account cụ thể
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }


}
