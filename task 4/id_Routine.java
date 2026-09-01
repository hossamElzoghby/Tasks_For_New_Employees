package com.mbsc;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;

/**
 * TODO: Document me!
 *
 * @author fareeda
 *
 */
public class idRoutine extends RecordLifecycle {

    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {
        
     // check that id has 11 characters + separators are in correct position
        if (currentRecordId == null || currentRecordId.length() != 11
                || currentRecordId.charAt(3) != '-'
                || currentRecordId.charAt(6) != '-') {
            throw new Error("INVALID ID FORMAT");
        }

        // separate id into XXX, YY, BB, and bb
        String xxx = currentRecordId.substring(0, 3);
        String yy = currentRecordId.substring(4, 6);
        String bb = currentRecordId.substring(7, 9);
        String letters = currentRecordId.substring(9, 11);

        // XXX, YY and BB contain numbers only
        if (!areDigits(xxx) || !areDigits(yy) || !areDigits(bb)) {
            throw new Error("INVALID ID FORMAT");
        }

        // bb contains letters only
        if (!areLetters(letters)) {
            throw new Error("INVALID ID FORMAT");
        }

        // YY is not greater than BB
        if (Integer.parseInt(yy) > Integer.parseInt(bb)) {
            throw new Error("INVALID ID FORMAT");
        }

        // success
        return currentRecordId;
    }

    // checks whether every character in the given value is a number
    private boolean areDigits(String value) {

        for (char c : value.toCharArray()) {

            // if one character is not a digit, the value is invalid
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // all characters are digits
        return true;
    }

    // checks whether every character in the given value is an english letter
    private boolean areLetters(String value) {

        for (char c : value.toCharArray()) {

            // Check for uppercase or lowercase English letters
            if (!((c >= 'A' && c <= 'Z') ||
                  (c >= 'a' && c <= 'z'))) {
                return false;
            }
        }

        // all characters are letters
        return true;
    } 
    }
