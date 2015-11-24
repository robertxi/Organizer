package com.robert.Organizer.service;

import com.robert.Organizer.model.User;

/**
 * Created by RobertXi on 11/24/15.
 */
public class UserService {

    public static void register(User user){
        OrganizerDAO.registerUser(user);
    }

    public static boolean checkUsername(String username){
        return OrganizerDAO.checkUsername(username);
    }
}
