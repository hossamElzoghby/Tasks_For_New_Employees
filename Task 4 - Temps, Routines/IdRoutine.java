package com.masaref;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;

public class IdRoutine extends RecordLifecycle {

    @Override
    public String checkId(String currentRecordId,
            TransactionContext transactionContext) {

        // XXX-YY-BBbb
        // XXX = numbers
        // YY  = numbers
        // BB  = numbers
        // bb  = letters

        if (currentRecordId == null
                || !currentRecordId.matches("\\d{3}-\\d{2}-\\d{2}[A-Za-z]{2}")) {

            throw new Error("INVALID ID FORMAT");
        }

        int yy = Integer.parseInt(currentRecordId.substring(4, 6));
        int bb = Integer.parseInt(currentRecordId.substring(7, 9));

        if (yy > bb) {
            throw new Error("INVALID ID FORMAT");
        }

        return currentRecordId;
    }
}