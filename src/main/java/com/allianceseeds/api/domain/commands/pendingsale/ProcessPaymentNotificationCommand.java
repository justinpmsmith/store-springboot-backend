package com.allianceseeds.api.domain.commands.pendingsale;

import com.allianceseeds.api.domain.commands.Command;
import lombok.Getter;

@Getter
public class ProcessPaymentNotificationCommand implements Command {
    private String m_payment_id; // matches our paymentId
    private String payment_status; // "COMPLETE", "FAILED", etc.

    private Double amount_gross;
    private String pf_payment_id;

    public ProcessPaymentNotificationCommand(String m_payment_id, String payment_status, Double amount_gross, String pf_payment_id) {
        this.m_payment_id = m_payment_id;
        this.payment_status = payment_status;
        this.amount_gross = amount_gross;
        this.pf_payment_id = pf_payment_id;
    }
}