package com.routines;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;

/**
 * TODO: Document me!
 *
 * @author Norhan
 *
 */



import com.temenos.t24.api.hook.system.RecordLifecycle;

public class TrainIdRoutine extends RecordLifecycle  {

    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
   String regex = "^\\d{3}-\\d{2}-\\d{2}[a-zA-Z]{2}$";
        
        if (!currentRecordId.matches(regex)) {
            throw new Error("INVALID ID FORMAT"); 
        }
        
        
        
        return currentRecordId; 
    }


}
