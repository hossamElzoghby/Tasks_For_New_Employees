package com.mbsc;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainhager.StMbscTrainHagerRecord;

/**
 * TODO: Document me!
 *
 * @author Hager
 *
 */
public class ValidationRtn extends RecordLifecycle {

    @Override
    public TValidationResponse validateField(String application, String recordId, String fieldData, TStructure record) {
       
        StMbscTrainHagerRecord rec =
                new StMbscTrainHagerRecord(record);

      
        // Floating = Yes
        if ("Yes".equalsIgnoreCase(rec.getFloating().getValue())) {

            if (rec.getBasicKey().getValue() == null ||
                rec.getBasicKey().getValue().trim().isEmpty()) {

                rec.getBasicKey().setError(
                        "BASIC.KEY SHOULD BE MANDATORY"
                );
            }


            if (rec.getIntSpread().getValue() == null ||
                rec.getIntSpread().getValue().trim().isEmpty()) {

                rec.getIntSpread().setError(
                        "INT.SPREAD SHOULD BE MANDATORY"
                );
            }
        }


       // Accumlated = Yes
        if ("YES".equalsIgnoreCase(rec.getAccumlated().getValue())) {

            if (rec.getRate().getValue() == null ||
                rec.getRate().getValue().trim().isEmpty()) {

                rec.getRate().setError(
                        "RATE SHOULD BE MANDATORY"
                );
            }

            if (rec.getIntRate().getValue() == null ||
                rec.getIntRate().getValue().trim().isEmpty()) {

                rec.getIntRate().setError(
                        "INT.RATE SHOULD BE MANDATORY"
                );
            }
        }

        return rec.getValidationResponse();
    
    }
    
}
