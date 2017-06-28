package com.application.chatsimple.controller;

import com.application.chatsimple.data.Filter;
import com.application.chatsimple.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void shouldDelegateSearchFiltersToUserService(){
        String gender = "Male";
        String city = "Berlin";
        userController.getUsers(gender, city);

        verify(userService).getUsers(any(Filter.class));
    }


}
