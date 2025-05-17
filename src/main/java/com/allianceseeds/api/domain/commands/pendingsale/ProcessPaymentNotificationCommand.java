package com.allianceseeds.api.domain.commands.pendingsale;

import com.allianceseeds.api.domain.commands.Command;
import lombok.Getter;

@Getter
public class ProcessPaymentNotificationCommand implements Command {
    private String m_payment_id; // matches our paymentId
    private String payment_status; // "COMPLETE", "FAILED", etc.
    private Double amount_gross;
    private String pf_payment_id;

    public ProcessPaymentNotificationCommand(String[] m_payment_id, String[] payment_status,
                                             String[] amount_gross, String[] pf_payment_id) {
        // Extract first value from each array or use null if array is empty
        this.m_payment_id = (m_payment_id != null && m_payment_id.length > 0) ? m_payment_id[0] : null;
        this.payment_status = (payment_status != null && payment_status.length > 0) ? payment_status[0] : null;
        this.pf_payment_id = (pf_payment_id != null && pf_payment_id.length > 0) ? pf_payment_id[0] : null;

        // Parse amount_gross with error handling
        this.amount_gross = null;
        if (amount_gross != null && amount_gross.length > 0 && amount_gross[0] != null && !amount_gross[0].isEmpty()) {
            try {
                this.amount_gross = Double.parseDouble(amount_gross[0]);
            } catch (NumberFormatException e) {
                // Log error but continue with null amount_gross
                System.out.println("Error parsing amount_gross: " + e.getMessage());
            }
        }
    }
}