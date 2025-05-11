package com.allianceseeds.api.presentation;

import com.allianceseeds.api.domain.commands.pendingsale.CreatePendingSaleCommand;
import com.allianceseeds.api.domain.commands.pendingsale.ProcessPaymentNotificationCommand;
import com.allianceseeds.api.presentation.resources.PendingSaleResource;
import com.allianceseeds.api.services.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PendingSaleController {

    private final PendingSaleResource pendingSaleResource;

    @Autowired
    public PendingSaleController(PendingSaleResource pendingSaleResource) {
        this.pendingSaleResource = pendingSaleResource;
    }

    @PostMapping("/client/createPendingSale")
    public Transformer createPendingSale(@RequestBody CreatePendingSaleCommand command) {
        return pendingSaleResource.createPendingSale(command);
    }

    @GetMapping("/admin/getAllPendingSales")
    public Transformer getAllPendingSales() {
        return pendingSaleResource.getAllPendingSales();
    }

    @PostMapping("/client/paymentNotify")
    public Transformer handlePaymentNotification(
            @RequestParam("m_payment_id") String m_payment_id,
            @RequestParam("payment_status") String payment_status,
            @RequestParam("amount_gross") Double amount_gross,
            @RequestParam("pf_payment_id") String pf_payment_id) {

        ProcessPaymentNotificationCommand command = new ProcessPaymentNotificationCommand(
                m_payment_id, payment_status, amount_gross, pf_payment_id);

        return pendingSaleResource.processPaymentNotification(command);
    }
}