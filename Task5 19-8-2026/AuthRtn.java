package com.mbsc;

import java.util.List;

import com.temenos.api.TStructure;
import com.temenos.api.exceptions.T24IOException;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbscconc.StMbscConcRecord;
import com.temenos.t24.api.records.stmbsctrain.StMbscTrainRecord;
import com.temenos.t24.api.tables.stmbscconc.StMbscConcTable;

/**
 * TODO: Document me!
 *
 * @author yousef
 *
 */
public class AuthRtn extends RecordLifecycle {

    @Override
    public void postUpdateRequest(String application, String currentRecordId, TStructure currentRecord,
            List<TransactionData> transactionData, List<TStructure> currentRecords,
            TransactionContext transactionContext) {
        StMbscTrainRecord TrainRecord = new StMbscTrainRecord(currentRecord);
        StMbscConcTable ConcTable = new StMbscConcTable(this);
        StMbscConcRecord ConcRecord = new StMbscConcRecord(currentRecord);
        
        if (TrainRecord.getWriteFlag().getValue().equalsIgnoreCase("YES"))
            ConcRecord.setFlag(TrainRecord.getWriteFlag());
        try {
            ConcTable.write(currentRecordId, ConcRecord);
        } catch (T24IOException e) {
            throw new Error("error saving flag in conc table");
        }
        
    }
    
}
