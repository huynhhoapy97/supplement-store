package com.huynhhoapy97.repositories.admin;

import com.huynhhoapy97.models.Account;

public interface AccountDAO {
    Account verifyUsername(String username);
    boolean updatePassword(Account account);
    Account getInformation(Account account);
}
