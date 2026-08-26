package com.masaref;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainali.StMbscTrainAliRecord;

public class CheckRecordRoutine extends RecordLifecycle {

    @Override
    public void defaultFieldValues(
            String application,
            String currentRecordId,
            TStructure currentRecord,
            TStructure unauthorisedRecord,
            TStructure liveRecord,
            TransactionContext transactionContext) {

        StMbscTrainAliRecord rec =
                new StMbscTrainAliRecord(currentRecord);

        String[] parts = currentRecordId.split("-");

        if (parts.length != 3) {
            return;
        }

        String first = parts[0];
        String second = parts[1];
        String third = parts[2];

        String letters = third.substring(2);

        rec.setTenor(first);
        rec.setFrequency(second);

        if ("MX".equalsIgnoreCase(letters)) {
            rec.setMixedType("YES");
        }

        currentRecord.set(rec.toStructure());
    }
}