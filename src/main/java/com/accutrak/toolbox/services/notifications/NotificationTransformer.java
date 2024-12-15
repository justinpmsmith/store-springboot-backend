package com.accutrak.toolbox.services.notifications;

import com.accutrak.toolbox.services.BaseTransformer;
import com.accutrak.toolbox.services.Transformer;
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
