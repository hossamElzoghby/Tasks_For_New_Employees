package com.mbsc;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;

import com.temenos.t24.api.hook.system.RecordLifecycle;

/**

 * @author Hagar
 *
 */
public class IdRtn extends RecordLifecycle {


    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {

        if (!isValid(currentRecordId)) {
            throw new Error ("INVALID ID FORMAT");
        }

        return currentRecordId; 
    }

    
    private boolean isValid(String id) {

        if (id == null) {
            return false;
        }

        String[] parts = id.split("-");

        if (parts.length != 3) {
            return false;
        }

        String xxx = parts[0];   
        String yy = parts[1];    
        String BBbb = parts[2];   

        
        if (xxx.length() != 3 || yy.length() != 2 || BBbb.length() != 4) {
            return false;
        }

       
        String BB = BBbb.substring(0, 2);
        String bb = BBbb.substring(2, 4);

        if (!isAllDigits(xxx)) {
            return false;
        }

        if (!isAllDigits(yy)) {
            return false;
        }

        if (!isAllDigits(BB)) {
            return false;
        }

        if (!isAllLetters(bb)) {
            return false;
        }

   
        int yyValue = Integer.parseInt(yy);
        int bbValue = Integer.parseInt(BB);

        if (yyValue > bbValue) {
            return false;
        }

        return true;
    }


    private boolean isAllDigits(String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean isAllLetters(String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            boolean isUpper = (c >= 'A' && c <= 'Z');
            boolean isLower = (c >= 'a' && c <= 'z');
            if (!isUpper && !isLower) {
                return false;
            }
        }
        return true;
    }
}