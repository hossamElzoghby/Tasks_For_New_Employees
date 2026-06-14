package com.routines;
import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
/**
 * TODO: Document me!
 *
 * @author Norhan
 *
 */
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.records.stmbsctrainnourhan.StMbscTrainNourhanRecord;
import com.temenos.t24.api.system.DataAccess;
/**
 * TODO: Document me!
 *
 * @author Norhan
 *
 */
public class Validation extends RecordLifecycle  {

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        StMbscTrainNourhanRecord record = new StMbscTrainNourhanRecord(currentRecord);
        String fval= record.getFloating().toString();
        String accum= record.getAccumlated().toString();
        if (fval.equalsIgnoreCase("YES"))
        {
            if (record.getBasicKey().getValue().isEmpty()) {
                record.getBasicKey().setError("(BASIC.KEY), Should be mandatory");
            }
            
          
        }
        if (accum.equalsIgnoreCase("yes")) {
            if (record.getRate().getValue().isEmpty()) {
                record.getRate().setError("(RATE), Should be mandatory");
            }
            
            if (record.getIntRate().getValue().isEmpty()) {
                record.getIntRate().setError("(INT.RATE), Should be mandatory");
            }
        }
        DataAccess da = new DataAccess(this);
        String id= record.getCustomerId().getValue().toString();
        
        CustomerRecord c= new CustomerRecord(da.getRecord("CUSTOMER", id));
        
        if(! c.getSector().getValue().equalsIgnoreCase("1001"))
        {
            c.getSector().setError("Customer Should be indvidual");
            
        }
        
      
        
    
        return record.getValidationResponse();

    
        
    }
}
