package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(1,"admin","2754425@gmail.com","admin", Role.ROLE_ADMIN,Role.ROLE_ADMIN)
            ,new User(2,"user1","sales@bityard.ru","user1",Role.ROLE_USER)
            ,new User(3,"user1","wellus@yandex.ru","user2",Role.ROLE_USER)
            );
}
