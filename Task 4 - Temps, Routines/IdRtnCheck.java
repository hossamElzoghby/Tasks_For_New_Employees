package com.masaref;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
/**
 * TODO: Document me!
 *
 * @author Ali Lotfy
 *
 */
public class IdRtnCheck extends RecordLifecycle {

    @Override
    public String checkId(
            String currentRecordId,
            TransactionContext transactionContext) {

        if (!isValidId(currentRecordId)) {
            throw new Error("INVALID ID FORMAT");
        }

        return currentRecordId;
    }

    private boolean isValidId(String id) {

        if (id == null) {
            return false;
        }

        String[] parts = id.split("-");

        if (parts.length != 3) {
            return false;
        }

        String tenor = parts[0];
        String frequency = parts[1];
        String lastPart = parts[2];

        if (tenor.length() != 3 ||
            frequency.length() != 2 ||
            lastPart.length() != 4) {
            return false;
        }

        String category = lastPart.substring(0, 2);
        String type = lastPart.substring(2);

        if (!isNumeric(tenor) ||
            !isNumeric(frequency) ||
            !isNumeric(category) ||
            !isLetters(type)) {
            return false;
        }

        return Integer.parseInt(frequency) <=
               Integer.parseInt(category);
    }

    private boolean isNumeric(String value) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        for (char c : value.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    private boolean isLetters(String value) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        for (char c : value.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}
