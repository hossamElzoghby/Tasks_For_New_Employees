package com.mbsc;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainstv.StMbscTrainStvRecord;

/**
 * TODO: Document me!
 *
 * @author Steven Nagy
 *
 */
public class CheckRecordRtn extends RecordLifecycle {

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        
        StMbscTrainStvRecord rec = new StMbscTrainStvRecord(currentRecord);
        String[] partitionedId = currentRecordId.split("-");
        String xxx  = partitionedId[0];
        String yy   = partitionedId[1];
        String BBbb = partitionedId[2];
        String bb  = BBbb.substring(2);
        rec.setTenor(xxx);
        rec.setFrequency(yy);
        rec.setProdType(xxx+yy+BBbb);
        if (bb.equalsIgnoreCase("MX")) {
            rec.setMixedType("YES");
        }
        currentRecord.set(rec.toStructure());
    }
}