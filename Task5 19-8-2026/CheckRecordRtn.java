package com.mbsc;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrain.StMbscTrainRecord;

/**
 * TODO: Document me!
 *
 * @author yousef
 *
 */
public class CheckRecordRtn extends RecordLifecycle {

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {

        StMbscTrainRecord TrainRecord = new StMbscTrainRecord(currentRecord);
        TrainRecord.setTenor(currentRecordId.substring(0, 3));
        TrainRecord.setFrequency(currentRecordId.substring(4, 6));
        if(currentRecordId.substring(9,11).equalsIgnoreCase("MX"))
            TrainRecord.setMixedType("YES");
        currentRecord.set(TrainRecord.toStructure());
    }

}