package com.masaref;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.records.stmbsctrainali.StMbscTrainAliRecord;
import com.temenos.t24.api.system.DataAccess;

public class InputRoutine extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(
            String application,
            String currentRecordId,
            TStructure currentRecord,
            TStructure unauthorisedRecord,
            TStructure liveRecord,
            TransactionContext transactionContext) {

        StMbscTrainAliRecord rec =
                new StMbscTrainAliRecord(currentRecord);

        String customerId = rec.getCustomerId().getValue();

        DataAccess da = new DataAccess(this);

        CustomerRecord customer =
                new CustomerRecord(
                        da.getRecord("CUSTOMER", customerId));

        String sector =
                customer.getSector().getValue();

        if (!sector.equalsIgnoreCase("1001")) {
            rec.getCustomerId().setError(
                    "Customer Should be individual");
        }

        return rec.getValidationResponse();
    }
}