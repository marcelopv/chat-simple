package com.application.chatsimple.controller;

import com.application.chatsimple.data.Filter;
import com.application.chatsimple.data.User;
import com.application.chatsimple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public List<User> getUsers(@RequestParam(required = false) String gender, @RequestParam(required = false) String city) {
        Filter filters = new Filter(null, city, gender, null);
        return userService.getUsers(filters);
    }
}
