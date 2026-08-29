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
    public TValidationResponse validateField(
            String application,
            String recordId,
            String fieldData,
            TStructure record) {

        StMbscTrainAliRecord rec =
                new StMbscTrainAliRecord(record);

        boolean floating =
                rec.getFloating() != null &&
                "YES".equalsIgnoreCase(
                        rec.getFloating().getValue());

        boolean accumlated =
                rec.getAccumlated() != null &&
                "YES".equalsIgnoreCase(
                        rec.getAccumlated().getValue());

        if (floating) {

            if (rec.getBasicKey() == null ||
                rec.getBasicKey().toString().trim().isEmpty()) {

                rec.getBasicKey().setError(
                        "BASIC.KEY, Should be mandatory");
            }
        }

        if (accumlated) {

            if (rec.getRate() == null ||
                rec.getRate().toString().trim().isEmpty()) {

                rec.getRate().setError(
                        "RATE, Should be mandatory");
            }

            if (rec.getIntRate() == null ||
                rec.getIntRate().toString().trim().isEmpty()) {

                rec.getIntRate().setError(
                        "INT.RATE, Should be mandatory");
            }
        }

        return rec.getValidationResponse();
    }
}
