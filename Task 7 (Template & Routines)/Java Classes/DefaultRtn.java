package com.mbsc;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainhager.StMbscTrainHagerRecord;

/**
 * TODO: Document me!
 *
 * @author Hagooor
 *
 */
public class DefaultRtn extends RecordLifecycle{

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        StMbscTrainHagerRecord rec =
                new StMbscTrainHagerRecord(currentRecord);

        if (rec.getCurrency().getValue().isEmpty()) {
            rec.getCurrency().setValue("EGP");
        }

        if (rec.getCategory().getValue().isEmpty()) {
            rec.getCategory().setValue("1001");
        }

        if (rec.getFixed().getValue().isEmpty()) {
            rec.getFixed().setValue("NO");
        }

        if (rec.getAccumlated().getValue().isEmpty()) {
            rec.getAccumlated().setValue("NO");
        }

        if (rec.getFloating().getValue().isEmpty()) {
            rec.getFloating().setValue("NO");
        }

        if (rec.getMixedType().getValue().isEmpty()) {
            rec.getMixedType().setValue("NO");
        }
    }
    
}
