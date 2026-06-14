package com.routines;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
/**
 * TODO: Document me!
 *
 * @author Norhan
 *
 */
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainnourhan.StMbscTrainNourhanRecord;
public class CheckRecordRoutine  extends  RecordLifecycle{

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
     String [] parts = currentRecordId.split("-");
     StMbscTrainNourhanRecord record = new StMbscTrainNourhanRecord(currentRecord);
     record.setTenor(parts[0]);
     record.setFrequency(parts[1]);
     String bb = currentRecordId.substring(currentRecordId.length() - 2);
     
     if (bb.equals("MX")) {
         record.setMixedType("YES");
     }
     currentRecord.set(record.toStructure());
    }
    

}
