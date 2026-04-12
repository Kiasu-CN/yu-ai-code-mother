package com.yupi.yuaicodemother.service;

public interface UserService {

    /**
     * 用户注册
     * 
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
