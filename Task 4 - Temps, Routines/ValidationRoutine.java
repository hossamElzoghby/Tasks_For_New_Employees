package com.masaref;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainali.StMbscTrainAliRecord;


/**
 * TODO: Document me!
 *
 * @author Ali Lotfy
 *
 */
public class ValidationRoutine extends RecordLifecycle {

    @Override
    public TValidationResponse validateField(String application, String recordId, String fieldData, TStructure record) {

        StMbscTrainAliRecord rec = new StMbscTrainAliRecord(record);

        boolean floating   = "YES".equalsIgnoreCase(rec.getFloating().toString());
        boolean accumlated = "YES".equalsIgnoreCase(rec.getAccumlated().toString());

        if (floating && rec.getBasicKey().toString().isEmpty()) {
            rec.getBasicKey().setError("BASIC.KEY, Should be mandatory");
        }
        if (floating && rec.getIntRate().toString().isEmpty()) {
            rec.getIntRate().setError("INT.SPREAD, Should be mandatory");
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