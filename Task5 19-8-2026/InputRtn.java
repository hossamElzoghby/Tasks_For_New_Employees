package com.mbsc;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.system.DataAccess;
import com.temenos.t24.api.records.stmbsctrain.StMbscTrainRecord;

/**
 * TODO: Document me!
 *
 * @author yousef
 *
 */
public class InputRtn extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        StMbscTrainRecord TrainRecord = new StMbscTrainRecord(currentRecord);
        DataAccess da = new DataAccess(this);
        CustomerRecord cus = new CustomerRecord(da.getRecord("CUSTOMER", TrainRecord.getCustomerId().getValue()));
        String Sector = cus.getSector().getValue();
        if(!"1001".equals(Sector))
            throw new Error(" should be an Individual!");
        return TrainRecord.getValidationResponse();
        
    }
    
}
