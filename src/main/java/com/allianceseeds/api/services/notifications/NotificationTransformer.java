package com.allianceseeds.api.services.notifications;

import com.allianceseeds.api.services.BaseTransformer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationTransformer extends BaseTransformer {
    public NotificationTransformer(Boolean success) {
        super();
        setSuccess(success);

    }
}
