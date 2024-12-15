package com.accutrak.toolbox.services.user;

import com.accutrak.toolbox.adapters.repositories.otp.OtpRepoInt;
import com.accutrak.toolbox.adapters.repositories.user.UserRepoInt;
import com.accutrak.toolbox.domain.commands.Command;
import com.accutrak.toolbox.domain.commands.notifications.SendEmailCommand;
import com.accutrak.toolbox.domain.commands.user.*;
import com.accutrak.toolbox.domain.entities.Otp;
import com.accutrak.toolbox.domain.entities.User;
import com.accutrak.toolbox.services.Transformer;
import com.accutrak.toolbox.services.UnitOfWork;
import com.accutrak.toolbox.services.UnitOfWorkInt;
import com.accutrak.toolbox.services.notifications.NotificationHandler;
import com.accutrak.toolbox.services.notifications.NotificationTransformer;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserHandler {
    private final UserRepoInt userRepo;

    private final OtpRepoInt otpRepo;

    private UnitOfWork<User> userUOW;
    private UnitOfWork<Otp> otpUOW;

    private NotificationHandler notificationHandler;


    @Autowired
    public UserHandler(UserRepoInt userRepo, OtpRepoInt otpRepo, NotificationHandler notificationHandler) {
        this.userRepo = userRepo;
        this.otpRepo = otpRepo;
        this.userUOW = new UnitOfWork<User>(this.userRepo);
        this.otpUOW = new UnitOfWork<Otp>(this.otpRepo);
        this.notificationHandler = notificationHandler;

    }

    public enum OtpStatus {
        OTP_CORRECT(0),
        OTP_INCORRECT(1),
        OTP_EXPIRED(2);

        private final int value;

        OtpStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public Transformer sendOtp(Command command){
        String email = ((RequestOtpCommand) command).getEmail();
        String deviceId = ((RequestOtpCommand) command).getDeviceId();
        String message;

        Otp otp = otpRepo.getOtpByEmail(email);

        boolean otpSent;
        int randInt;
        long currentTimestamp = System.currentTimeMillis()/ 1000;
        long newTtl = ((RequestOtpCommand) command).getExpirationTimestamp();

        if(otp == null || otp.getExpiration() < currentTimestamp){ // no otp yet for email || otp expired
            randInt = ((RequestOtpCommand) command).generateOtp();
            otp = new Otp(email, deviceId, randInt, newTtl);
            otpUOW.registerRepoOperation(otp, UnitOfWorkInt.UnitActions.INSERT);
            otpUOW.commit();
            otpSent = sendOtpEmail(email, otp);
            message = "OTP email sent";

        }
        else { // otp still alive
            otpSent = true;
            message = "OTP email already sent";

        }

        if(!otpSent){

//            return transformer;
            UserTransformer transformer = new UserTransformer<>(false, null);
            transformer.setMessage("Failed to send OTP to provided email address. check if email is valid");
             return transformer;

        }

        UserTransformer successTransformer = new UserTransformer<>(true, null);
        successTransformer.setMessage(message);
        return successTransformer;
    }

    public Transformer updateUser(Command command){
        User user = ((UpdateUserCommand) command).getUser();


        User previousUserDetails = userRepo.getUserpByEmail(user.getEmail());

        if(previousUserDetails != null) {

            user.setId(previousUserDetails.getId());
            userUOW.registerRepoOperation(user, UnitOfWorkInt.UnitActions.INSERT);
            userUOW.commit();
            return new UserTransformer<>(true, user);

        }

        return new UserTransformer<>(false, null);
    }

    public Transformer migrateUser(Command command){
        User user = ((MigrateUserCommand) command).getUser();

        // check if user with current email already exist
        User previousUserDetails = userRepo.getUserpByEmail(user.getEmail());
        if(previousUserDetails != null) {

            // use the uuid already associated with user
            user.setId(previousUserDetails.getId());
            user.setUuid(previousUserDetails.getUuid());
        }

        // else create new unique uuid for user
        else{

            // ensure that uuid for user is unique
            int i = 0;
            while(i < 3){
                List<User> userList = userRepo.getUsersByStringField("uuid", user.getUuid());
                if(userList.size() > 0){
                    user.generateUuid();
                } else {
                    break;
                }
                i++;
            }
        }




        userUOW.registerRepoOperation(user, UnitOfWorkInt.UnitActions.INSERT);
        userUOW.commit();
        return new UserTransformer<>(true, user);

    }

    public Transformer addUser(Command command){

        User user = ((AddUserCommand) command).getUser();

        OtpStatus otpValid = validateOtp(user.getEmail() , user.getOtp());

        if(otpValid != OtpStatus.OTP_CORRECT ){
            UserTransformer<?> transformer = new UserTransformer(false, null);

            switch (otpValid){
                case OTP_EXPIRED -> transformer.setMessage("OTP expired");
                case OTP_INCORRECT -> transformer.setMessage("OTP incorrect");
            }
            return transformer;
        }

        User previousUserDetails = userRepo.getUserpByEmail(user.email);


        if(previousUserDetails != null) {

            user.setId(previousUserDetails.getId());
            user.setUuid(previousUserDetails.getUuid());
        }
        userUOW.registerRepoOperation(user, UnitOfWorkInt.UnitActions.INSERT);
        userUOW.commit();
        return new UserTransformer<>(true, user);


    }

    public Transformer getUuidByEmail(Command command){

        String email = ((GetUuidByEmailCommand) command).getEmail();

        List<String> uuidList = userRepo.getUuidListByEmail(email);

        return  new UserTransformer(true, uuidList);

    }

    public Transformer getTagConfigLevellByUuid(Command command){

        String uuid = ((GetTagConfigLevelByUuidCommand) command).getUuid();

        List<User> userList = userRepo.getUsersByStringField("uuid", uuid);

        if(userList.size() > 0){
            int tagConfigLevel = userList.get(0).getTagConfigLevel();
            return  new UserTransformer(true, tagConfigLevel);
        }
        return  new UserTransformer(true, null);


    }

    private Boolean sendOtpEmail(String email,Otp otp){


        if(otp.getNotificationSent()){

            return true;
        }

        String subject = "Accutrak Toolbox OTP";
        String body = "Please use the verification code below to sign in.\n " + String.valueOf(otp.getOtp()) + "\n\nIf you didn’t request this, you can ignore this email.\n\nThanks,\nThe Accutrak Team";

        SendEmailCommand command = new SendEmailCommand(email, subject, body);
        NotificationTransformer transformer = (NotificationTransformer) notificationHandler.sendEmail(command);


        if(transformer.getMeta().getSuccess()){
            otp.setNotificationSent(true);
            otpUOW.registerRepoOperation(otp, UnitOfWorkInt.UnitActions.INSERT);
            otpUOW.commit();
        }
        return transformer.getMeta().getSuccess();

    }


    private OtpStatus validateOtp(String email, int otp){
        Otp currentOtp = otpRepo.getOtpByEmail(email);
        long currentTimestamp = System.currentTimeMillis()/ 1000;

        if(currentOtp.getOtp() != otp){return OtpStatus.OTP_INCORRECT;}
        if(currentTimestamp > currentOtp.getExpiration()){
            return OtpStatus.OTP_EXPIRED;
        }
        return OtpStatus.OTP_CORRECT;

//        return(currentOtp.getOtp() == otp && currentTimestamp < currentOtp.getExpiration() );

    }





}
