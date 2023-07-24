package com.github.AleksandrSpencer.mtb.repository;
import com.github.AleksandrSpencer.mtb.repository.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramUserRepository  extends JpaRepository<TelegramUser, String> {
    List<TelegramUser> findAllByActiveTrue();
}