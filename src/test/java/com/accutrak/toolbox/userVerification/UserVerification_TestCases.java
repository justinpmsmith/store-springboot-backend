package com.accutrak.toolbox.userVerification;

import com.accutrak.toolbox.domain.entities.Otp;
import com.accutrak.toolbox.domain.entities.User;

public class UserVerification_TestCases {
    public static String validDeviceId = "UnitTestDeviceId";
    public static String inValidDeviceId = "";
    public static String validEmail = "test@accutrak.co.za";
    public static String inValidEmail = "test@outlook.com";

    public static String name = "Test";

    public static String surname = "Testonsen";

    public static String site = "Doringkop";


    public static User validUser =  User.builder().
            deviceId(validDeviceId).
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

    public static Otp validOtp = Otp.builder().
            email("justin.smith@accutrak.co.za").
            deviceId("testDeviceId").
            build();

    public static Otp inValidOtp = Otp.builder().
            email("jazzy@jazzy.com").
            deviceId("testDeviceId").
            build();

}


