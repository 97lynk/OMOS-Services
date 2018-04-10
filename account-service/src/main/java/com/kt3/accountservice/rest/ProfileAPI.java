package com.kt3.accountservice.rest;

import com.kt3.accountservice.model.Profile;
import com.kt3.accountservice.servive.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author 97lynk
 */
@RestController
@RequestMapping("/profile")
public class ProfileAPI {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = Logger.getLogger(ProfileAPI.class.getName());


    /**
     * Lấy danh sách profiles
     *
     * @return Profie
     * @scope
     * @privilege
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Profile> getAllProfiles() {
        return accountService.selectProfiles();
    }

    /**
     * Lấy 1 profile theo username (chứng thực)
     *
     * @param auth
     * @return Profie
     * @scope
     * @privilege
     */
    @GetMapping("/owner")
    public Profile getOwnerProfile(OAuth2Authentication auth) {
        return accountService.selectProfileByUserName(auth.getName());
    }

    /**
     * Lấy profile theo id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable("id") int id) {
        return accountService.selectProfileById(id);
    }

    /**
     * Thêm 1 profile
     *
     * @param profile profile cần thêm
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewProfile(@RequestBody Profile profile) {
        accountService.insertProfile(profile);
    }

    /**
     * Thay đổi 1 profile cụ thể
     *
     * @param profile profile có id cũ, có các thuộc tính mới sẽ cập nhật
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeProfile(@PathVariable("id") int id, @RequestBody Profile profile) {
        profile.setId(id);
        accountService.updateProfile(profile);
    }


    /**
     * Xóa 1 profile cụ thể
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@PathVariable int id) {
        accountService.deleteProfile(id);
    }


    //@PreAuthorize("#oauth2.hasScope('CUST_INFO') and #oauth2.hasScope('READ')")
    //@PreAuthorize("#contact.name == authentication.name")
    //@PostFilter("filterObject.account.userName == authentication.name")
//    @PreAuthorize("#oauth2.hasScope('READ')")
//    @GetMapping("/user")
//    public OAuth2Authentication getExtraInfo(OAuth2Authentication auth) {
//        return auth;
//    }
}
