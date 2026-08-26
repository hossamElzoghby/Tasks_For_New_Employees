package com.mbsc;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrain.StMbscTrainRecord;

/**
 * TODO: Document me!
 *
 * @author yousef
 *
 */
public class ValidationRtn extends RecordLifecycle{

    @Override
    public TValidationResponse validateField(String application, String recordId, String fieldData, TStructure record) {
        
        StMbscTrainRecord TrainRecord = new StMbscTrainRecord(record);
        
        if(TrainRecord.getFloating().getValue().equalsIgnoreCase("YES")){
            
            String basicKey = TrainRecord.getBasicKey().getValue();
            if (basicKey == null || basicKey.trim().isEmpty())
                TrainRecord.getBasicKey().setError(", Should be Mandatory!");
            
            String intSpread = TrainRecord.getIntSpread().getValue();
            if (intSpread == null || intSpread.trim().isEmpty())
                TrainRecord.getIntSpread().setError(", Should be Mandatory!");
            
        }
        if(TrainRecord.getAccumlated().getValue().equalsIgnoreCase("YES")){
         
            String rate = TrainRecord.getRate().getValue();
            if (rate == null || rate.trim().isEmpty())
                TrainRecord.getRate().setError(", Should be Mandatory!");
            
            String intRate = TrainRecord.getIntRate().getValue();
            if (intRate == null || intRate.trim().isEmpty())
                TrainRecord.getIntRate().setError(", Should be Mandatory!");
        }
        return TrainRecord.getValidationResponse();
        
    }
    
}
