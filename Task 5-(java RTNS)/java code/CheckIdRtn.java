package com.mbsc.train;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;

/**
 * TODO: Document me!
 *
 * @author nada
 *
 */
public class CheckIdRtn extends RecordLifecycle {

    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        if(!isValid(currentRecordId)){
            throw new Error("Invalid record Id Format");
        }
        
        return currentRecordId;
    }
    
    private boolean isValid(String value){
        
        if (value == null) {
            return false;
        }
        if (!value.matches("\\d{3}-\\d{2}-\\d{2}[A-Za-z]{2}")){
            return false;
        }
        
        int yy=Integer.parseInt(value.substring(4,6));
        int bb=Integer.parseInt(value.substring(7,9));
        
        if(yy>bb){
            return false;
        }
        return true;
    }

}
