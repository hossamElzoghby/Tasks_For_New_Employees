package com.mbsc.train;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;

import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.records.stmbsctrainnadaa.StMbscTrainNadaaRecord;
import com.temenos.t24.api.system.DataAccess;

/**
 * TODO: Document me!
 *
 * @author nada
 *
 */
public class InputRtn extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        
        StMbscTrainNadaaRecord rec =new StMbscTrainNadaaRecord(currentRecord);
        
        String CustId=rec.getCustomerId().getValue();        
        DataAccess da =new DataAccess(this);
        CustomerRecord cus = new CustomerRecord(da.getRecord("CUSTOMER", CustId));
        
        String Sec =cus.getSector().getValue();
        if(!Sec.equals("1001")){
            rec.getCustomerId().setError("Sector Should Be 1001");
        }
        return rec.getValidationResponse();
    }

    
}
