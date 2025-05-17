package com.allianceseeds.api.presentation;

import com.allianceseeds.api.domain.commands.pendingsale.CreatePendingSaleCommand;
import com.allianceseeds.api.domain.commands.pendingsale.ProcessPaymentNotificationCommand;
import com.allianceseeds.api.presentation.resources.PendingSaleResource;
import com.allianceseeds.api.services.Transformer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

//    @PostMapping("/client/paymentNotify")
//    public Transformer handlePaymentNotification(
//            @RequestParam("m_payment_id") String m_payment_id,
//            @RequestParam("payment_status") String payment_status,
//            @RequestParam("amount_gross") Double amount_gross,
//            @RequestParam("pf_payment_id") String pf_payment_id) {
//        System.out.println("received notification ");
//        System.out.println(m_payment_id);
//
//        ProcessPaymentNotificationCommand command = new ProcessPaymentNotificationCommand(
//                m_payment_id, payment_status, amount_gross, pf_payment_id);
//
//        return pendingSaleResource.processPaymentNotification(command);
//    }

    @PostMapping("/client/paymentNotify")
    public Transformer handlePaymentNotification(
            @RequestParam(value = "m_payment_id", required = false) String[] m_payment_id,
            @RequestParam(value = "payment_status", required = false) String[] payment_status,
            @RequestParam(value = "amount_gross", required = false) String[] amount_gross,
            @RequestParam(value = "pf_payment_id", required = false) String[] pf_payment_id) {

        System.out.println("===== PAYMENT NOTIFICATION RECEIVED =====");

        // Log received parameters
        System.out.println("m_payment_id: " + Arrays.toString(m_payment_id));
        System.out.println("payment_status: " + Arrays.toString(payment_status));
        System.out.println("amount_gross: " + Arrays.toString(amount_gross));
        System.out.println("pf_payment_id: " + Arrays.toString(pf_payment_id));

        System.out.println("==========================================");

        ProcessPaymentNotificationCommand command = new ProcessPaymentNotificationCommand(
                m_payment_id, payment_status, amount_gross, pf_payment_id);

        return pendingSaleResource.processPaymentNotification(command);
    }
}