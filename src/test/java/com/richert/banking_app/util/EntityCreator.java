package com.richert.banking_app.util;

import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.Manager;
import com.richert.banking_app.entity.enums.ClientStatus;
import com.richert.banking_app.entity.enums.ManagerStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityCreator {

    public static Client getClient(){
        return new Client(
                ClientStatus.PENDING,
                "8254547854875",
                "Aleksey",
                "Lavrov",
                "kentaskis.l@gmail.com",
                "Kamen, Einsteinstrasse 3",
                "380668882744",
                new Manager( "Aleksey", "Pobedonosec", ManagerStatus.PENDING)
        );
    }
}