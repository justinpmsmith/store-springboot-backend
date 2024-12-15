package com.accutrak.toolbox.domain.commands.software;

import com.accutrak.toolbox.domain.entities.Software;

public class AddSoftwareCommand implements SoftwareCommand{
    Software software;

    public AddSoftwareCommand(Software software) {
        software.site = software.site.toUpperCase();
        this.software = software;
    }

    public Software getSoftware() {
        return software;
    }
}
