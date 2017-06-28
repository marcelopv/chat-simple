package com.application.chatsimple.service;

import com.application.chatsimple.data.Filter;
import com.application.chatsimple.data.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserServiceTest {
    public static final String BERLIN_CITY = "Berlin";
    public static final String GENDER_MALE = "Male";
    public static final String GENDER_FEMALE = "Female";
    public static final String MARITAL_STATUS_SINGLE = "Single";

    //test that get fake users - OK
    //test that get all users; - OK
    //test that get filtered age/gender/city/maritalStatus

    private UserService userService;

    @Before
    public void init(){
        this.userService = new UserService();
    }

    @Test
    public void shouldGetFakeUsers(){
        Filter filters = new Filter();
        List<User> users = userService.getUsers(filters);

        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    public void shouldFilterUsers(){
        Filter filters = new Filter(20, BERLIN_CITY, GENDER_FEMALE, MARITAL_STATUS_SINGLE);
        List<User> users = userService.getUsers(filters);

        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getCity()).isEqualTo(BERLIN_CITY);
        assertThat(users.get(0).getGender()).isEqualTo(GENDER_FEMALE);
    }

}