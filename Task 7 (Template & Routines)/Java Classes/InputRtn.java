package com.mbsc;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.records.stmbsctrainhager.StMbscTrainHagerRecord;
import com.temenos.t24.api.system.DataAccess;

public class InputRtn extends RecordLifecycle {

    @Override
    public TValidationResponse validateRecord(
            String application,
            String currentRecordId,
            TStructure currentRecord,
            TStructure unauthorisedRecord,
            TStructure liveRecord,
            TransactionContext transactionContext) {

        StMbscTrainHagerRecord rec =
                new StMbscTrainHagerRecord(currentRecord);

        String customerId =
                rec.getCustomerId().getValue();

        DataAccess da = new DataAccess(this);

        CustomerRecord cus =
                new CustomerRecord(
                        da.getRecord("CUSTOMER", customerId));

        String sector =
                cus.getSector().getValue();

        if (!sector.equalsIgnoreCase("1001")) {
            rec.getCustomerId().setError(
                    "Customer Should be indvidual");
        }

        return rec.getValidationResponse();
    }
}