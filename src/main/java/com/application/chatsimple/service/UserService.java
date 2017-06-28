package com.application.chatsimple.service;

import com.application.chatsimple.data.Filter;
import com.application.chatsimple.data.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {

    private List<User> users;

    public UserService(){
        users = new ArrayList<>();
        users.add(new User("John", 25, "Berlin", "Male", "Single"));
        users.add(new User("Luiza", 25, "Berlin", "Female", "Single"));
        users.add(new User("Rebbeca", 30, "Hamburg", "Female", "Single"));
    }

    public List<User> getUsers(Filter filters) {

        Optional<Filter> filtersOptional = Optional.ofNullable(filters);

        if(filtersOptional.isPresent()){
            Filter filter = filtersOptional.get();
            String city = filter.getCity();
            String gender = filter.getGender();

            Stream<User> stream = this.users.stream();
            if(StringUtils.isNotBlank(city)) {
                stream = stream.filter(user -> user.getCity().equals(city));
            }
            if(StringUtils.isNotBlank(gender)) {
                stream = stream.filter(user -> user.getGender().equals(gender));
            }
            return stream.collect(Collectors.toList());
        }

        return users;
    }
}
