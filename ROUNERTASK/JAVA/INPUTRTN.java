package Test;


import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.records.stmbsctrainezzat.*;
import com.temenos.t24.api.system.DataAccess;

/**
 * TODO: Document me!
 *
 * @author Mohamed Ezzat
 *
 */
public class INPUTRTN extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        StMbscTrainEzzatRecord record = new  StMbscTrainEzzatRecord(currentRecord);
        DataAccess da = new DataAccess(this);
        String id= record.getCustomerId().getValue().toString();
        
        CustomerRecord c= new CustomerRecord(da.getRecord("CUSTOMER", id));
        
        if(!c.getSector().getValue().equalsIgnoreCase("1001"))
        {
            record.getCustomerId().setError("Customer Should be indvidual");
            
        }
        
      
        
    
        return record.getValidationResponse();

    
    }
}