package com.mbsc.train;

import java.util.List;

import com.temenos.api.TStructure;
import com.temenos.api.exceptions.T24IOException;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbscconcnadaa.StMbscConcNadaaRecord;
import com.temenos.t24.api.records.stmbsctrainnadaa.StMbscTrainNadaaRecord;
import com.temenos.t24.api.tables.stmbscconcnadaa.StMbscConcNadaaTable;

/**
 * TODO: Document me!
 *
 * @author nada
 *
 */
public class AuthRtn extends RecordLifecycle{

    @Override
    public void postUpdateRequest(String application, String currentRecordId, TStructure currentRecord,
            List<TransactionData> transactionData, List<TStructure> currentRecords,
            TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        
        
        StMbscTrainNadaaRecord trainRec =new StMbscTrainNadaaRecord(currentRecord);
        
        if ("YES".equalsIgnoreCase(trainRec.getWriteFlag().getValue())){
            
            StMbscConcNadaaTable conctable =new StMbscConcNadaaTable(this);
            StMbscConcNadaaRecord concRec;
            
            try {
                concRec = conctable.read(currentRecordId);
            } catch (T24IOException e) {
                concRec = new StMbscConcNadaaRecord();
            }
            concRec=new StMbscConcNadaaRecord();
            
            concRec.setFlag("YES");
            
            try {
                conctable.write(currentRecordId,concRec);
            } catch (T24IOException e) {
                throw new RuntimeException("MBSC.CONC flag update failed", e);
            }
        }
   }
}
