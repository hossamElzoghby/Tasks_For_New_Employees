package com.mbsc.train;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainnadaa.StMbscTrainNadaaRecord;

/**
 * TODO: Document me!
 *
 * @author nada
 *
 */
public class ValidateRecRtn extends RecordLifecycle{

    @Override
    public TValidationResponse validateField(String application, String recordId, String fieldData, TStructure record) {
        // TODO Auto-generated method stub
        
        StMbscTrainNadaaRecord rec =new StMbscTrainNadaaRecord(record);
        
        boolean floating   = "YES".equalsIgnoreCase(rec.getFloating().toString());
        boolean accumlated = "YES".equalsIgnoreCase(rec.getAccumlated().toString());
        
        if (floating && rec.getBasicKey().toString().isEmpty()) {
            rec.getBasicKey().setError("BASIC.KEY, Should be mandatory");
        }
        if (floating && rec.getIntSpread().toString().isEmpty()) {
            rec.getIntSpread().setError("INT.SPREAD, Should be mandatory");
        }
        if (accumlated && rec.getRate().toString().isEmpty()) {
            rec.getRate().setError("RATE, Should be mandatory");
        }
        if (accumlated && rec.getIntRate().toString().isEmpty()) {
            rec.getIntRate().setError("INT.RATE, Should be mandatory");
        }
        
        
        return rec.getValidationResponse();
    }
   
}
