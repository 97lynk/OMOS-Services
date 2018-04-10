package com.kt3.accountservice.reponsitory;

import com.kt3.accountservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileReponsitory extends JpaRepository<Profile, Integer> {
}
