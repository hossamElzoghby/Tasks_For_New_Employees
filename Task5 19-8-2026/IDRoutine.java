package com.mbsc;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;

/**
 * TODO: Document me!
 *
 * @author yousef
 *
 */
public class IDRoutine extends RecordLifecycle {

    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {

        // XXX-YY-BBbb
        if (currentRecordId == null || currentRecordId.length() != 11
                || currentRecordId.charAt(3) != '-'
                || currentRecordId.charAt(6) != '-') {
            System.out.println("INVALID!!!!!!!!!!");
            throw new Error( "INVALID ID FORMAT");
        }
        
        int[] digitPositions = { 0, 1, 2, 4, 5, 7, 8 };
        for (int i : digitPositions) {
            if (!Character.isDigit(currentRecordId.charAt(i))) {
                throw new Error( "INVALID ID FORMAT");
            }
        }
        
        if (!Character.isLetter(currentRecordId.charAt(9))
                || !Character.isLetter(currentRecordId.charAt(10))) {
            throw new Error( "INVALID ID FORMAT");
        }
        
        int YY = Integer.parseInt(currentRecordId.substring(4, 6));
        int BB = Integer.parseInt(currentRecordId.substring(7, 9));

        if (YY > BB) {
            throw new Error( "INVALID ID FORMAT");
        }

        return currentRecordId;
    }
}