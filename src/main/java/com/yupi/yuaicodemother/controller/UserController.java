package com.yupi.yuaicodemother.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yupi.yuaicodemother.common.BaseResponse;
import com.yupi.yuaicodemother.common.ResultUtils;
import com.yupi.yuaicodemother.exception.ErrorCode;
import com.yupi.yuaicodemother.exception.ThrowUtils;
import com.yupi.yuaicodemother.model.dto.user.UserRegisterRequest;
import com.yupi.yuaicodemother.service.UserService;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;


    @PostMapping("register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassWord = userRegisterRequest.getUserPassword();
        String checkPassWord = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, userPassWord, checkPassWord);
        return ResultUtils.success(result);
    }
}
