package com.allianceseeds.api.services.user;

import com.allianceseeds.api.adapters.repositories.user.UserRepo;
import com.allianceseeds.api.domain.commands.Command;
import com.allianceseeds.api.domain.commands.user.AddUserCommand;
import com.allianceseeds.api.domain.commands.user.AuthenticateUserCommand;
import com.allianceseeds.api.domain.commands.user.DeleteUserByNameCommand;
import com.allianceseeds.api.domain.entities.User;
import com.allianceseeds.api.services.Transformer;
import com.allianceseeds.api.services.UnitOfWork;
import com.allianceseeds.api.services.UnitOfWorkInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHandler {

    private final UserRepo userRepo;
    private final UnitOfWork<User> userUOW;

    @Autowired
    public UserHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.userUOW = new UnitOfWork<>(this.userRepo);
    }

    public Transformer addUser(Command command) {
        User user = ((AddUserCommand) command).getUser();

        List<User> userList = userRepo.getUserByStringField("name", user.getName());

        if (!userList.isEmpty()) {
            UserTransformer transformer = new UserTransformer<>(false, null);
            transformer.setMessage("There is another user with this name");
            return transformer;
        }
        userUOW.registerRepoOperation(user, UnitOfWorkInt.UnitActions.INSERT);
        userUOW.commit();

        return new UserTransformer<>(true, null);
    }

    public Transformer deleteUserByName(Command command) {
        String name = ((DeleteUserByNameCommand) command).getName();

        List<User> userList = userRepo.getUserByStringField("name", name);

        if (!userList.isEmpty()) {
            userUOW.registerBulkOperation(userList, UnitOfWorkInt.UnitActions.DELETE);
            userUOW.commit();
        }
        return new UserTransformer<>(true, null);

    }

    public Transformer authenticate(Command command) {
        String name = ((AuthenticateUserCommand) command).getName();
        String passwordHash = ((AuthenticateUserCommand) command).getPasswordHash();

        List<User> users = userRepo.getUserByStringField("name", name);

        if (users.isEmpty() || !users.get(0).getPassword().equals(passwordHash)) {
            return new UserTransformer<>(false, null, "Invalid username or password");
        }

        // Perform HTTP request to retrieve access token (use dummy token until access token retrieval in place)
        String accessToken = "dummyToken";
        return new UserTransformer<>(true, accessToken);
    }
}