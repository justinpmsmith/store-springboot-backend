package com.accutrak.toolbox.user;

import com.accutrak.toolbox.domain.entities.User;

public class User_TestCases {

    public static String validDeviceId = "UnitTestDeviceId";
    public static String inValidDeviceId = "";
    public static String validEmail = "test@accutrak.co.za";
    public static String inValidEmail = "test@ouk.com";

    public static String name = "Test123";

    public static String surname = "Testonsen";

    public static String site = "Doringkop";


    public static User validUser = User.builder().
            deviceId(validDeviceId).
            email(validEmail).
            name(name).
            surname(surname).
            site(site).
            build();

    public static User userInvalidDeviceId = User.builder().
            deviceId(inValidDeviceId).
            name(name).
            surname(surname).
            site(site).
            build();

}
