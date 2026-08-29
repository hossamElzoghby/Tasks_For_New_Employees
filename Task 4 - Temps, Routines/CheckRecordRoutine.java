package com.masaref;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainali.StMbscTrainAliRecord;
/**
 * TODO: Document me!
 *
 * @author Ali Lotfy
 *
 */
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

        if (parts.length == 3) {

            String tenor = parts[0];
            String frequency = parts[1];
            String lastPart = parts[2];

            rec.setTenor(tenor);
            rec.setFrequency(frequency);

            if (lastPart.length() == 4) {

                String type = lastPart.substring(2);

                if ("MX".equalsIgnoreCase(type)) {
                    rec.setMixedType("YES");
                }
            }

            currentRecord.set(rec.toStructure());
        }
    }
}
