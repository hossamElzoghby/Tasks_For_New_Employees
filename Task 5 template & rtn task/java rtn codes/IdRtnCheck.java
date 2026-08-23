package com.mbsc;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;

/**
 * TODO: Document me!
 *
 * @author Steven Nagy
 *
 */
public class IdRtnCheck extends RecordLifecycle {

    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {
       if(!isValidId(currentRecordId)){
           throw new Error("INVALID ID FORMAT");
       }
       return currentRecordId;
    }
    
    private boolean isValidId(String id){
        if (id == null) {
            return false;
        }
        
        String[] partitionedId = id.split("-");
        
        if (partitionedId.length != 3) {
            return false;
        }

        String xxx  = partitionedId[0];
        String yy   = partitionedId[1];
        String BBbb = partitionedId[2];


        if (xxx.length() != 3 || yy.length() != 2 || BBbb.length() != 4) {
            return false;
        }

        String BB = BBbb.substring(0, 2);
        String bb = BBbb.substring(2);

        if (!isNumeric(xxx) || !isNumeric(yy) || !isNumeric(BB) || !isLetters(bb) ) {
            return false;
        }
       
        return Integer.parseInt(yy) <= Integer.parseInt(BB);
    }

    private boolean isNumeric(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean isLetters(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
