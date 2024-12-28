package com.huynhhoapy97.services.admin;

import com.huynhhoapy97.models.Account;
import com.huynhhoapy97.repositories.admin.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AccountService {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    HttpSession session;

    public Account verifyUserName(Account account) {
        String userName = account.getUserName();
        account = accountDAO.verifyUsername(userName);

        return account;
    }

    public boolean updatePassword(Account account) {
        Account accountAfterEncryptPassword = proceedEncryptPassword(account);

        return accountDAO.updatePassword(accountAfterEncryptPassword);
    }

    public boolean checkPasswordValidity(Account account) {
        String password = account.getPassword();
        String confirmPassword = account.getConfirmPassword();

        if (password == null || password.isEmpty()) {
            return false;
        }
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            return false;
        }

        return password.equals(confirmPassword);
    }

    public boolean confirmPassword(Account account) {
        Account accountDatabase = accountDAO.getInformation(account);

        return BCrypt.checkpw(account.getPassword(), accountDatabase.getPassword());
    }

    private Account proceedEncryptPassword(Account account) {
        String currentPassword = account.getPassword();
        String hashPassword = BCrypt.hashpw(currentPassword, BCrypt.gensalt());

        Account accountAfterEncryptPassword = account.clone();
        accountAfterEncryptPassword.setPassword(hashPassword);

        return accountAfterEncryptPassword;
    }

    public void saveLoginSession(Account account) {
        session.setAttribute("account_id", account.getId());
        session.setMaxInactiveInterval(60*60);
    }

    public void removeLoginSession() {
        session.removeAttribute("account_id");
    }

    public boolean checkLoginSessionExisting() {
        return session.getAttribute("account_id") != null;
    }

    public Account getLoginInformation() {
        int id = (int) session.getAttribute("account_id");

        Account account = new Account();
        account.setId(id);
        return accountDAO.getInformation(account);
    }
}
