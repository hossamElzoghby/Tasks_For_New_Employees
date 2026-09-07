package com.mbsc.train;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainnadaa.StMbscTrainNadaaRecord;

/**
 * TODO: Document me!
 *
 * @author nada
 *
 */
public class CheckRecRtn extends RecordLifecycle {

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        
        
        StMbscTrainNadaaRecord rec =new StMbscTrainNadaaRecord(currentRecord);
        
        String[] partition=currentRecordId.split("-");
        
        String xxx  = partition[0];
        String yy   = partition[1];
        String BBbb = partition[2];
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
