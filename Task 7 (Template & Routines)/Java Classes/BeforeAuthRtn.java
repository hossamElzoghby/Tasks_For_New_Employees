package com.mbsc;

import java.util.List;

import com.temenos.api.TBoolean;
import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainhager.StMbscTrainHagerRecord;

/**
 * TODO: Document me!
 *
 * @author Hager
 *
 */
public class BeforeAuthRtn extends RecordLifecycle{

    @Override
    public void updateCoreRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, List<String> versionNames, TBoolean isZeroAuth,
            List<String> currentRecordIds, List<TStructure> currentRecords, TransactionContext transactionContext) {
        StMbscTrainHagerRecord rec =
                new StMbscTrainHagerRecord(currentRecord);

        if ("YES".equalsIgnoreCase(rec.getWriteFlag().getValue())
                && rec.getCustomerId().getValue().isEmpty()) {

            rec.getCustomerId().setError(
                    "Customer ID Should be Mandatory");
        }

    
    }
    

}
