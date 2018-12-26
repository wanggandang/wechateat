package com.itcast.manager.controller;

import com.itcast.manager.base.BaseResult;
import com.itcast.manager.entity.User;
import com.itcast.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("user")
   /* @PostMapping
    @PutMapping
    @PatchMapping
    @DeleteMapping*/
   public BaseResult getUser(){

        try {
            List<User> users = userService.get();
            int i =3/0;
            return new BaseResult().addList(users,"yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            return  new BaseResult().setErrmsg("查询异常");
        }


    }
}
