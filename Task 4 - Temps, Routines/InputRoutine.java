package com.masaref;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.customer.CustomerRecord;
import com.temenos.t24.api.records.stmbsctrainali.StMbscTrainAliRecord;
import com.temenos.t24.api.system.DataAccess;
/**
 * TODO: Document me!
 *
 * @author Ali Lotfy
 *
 */
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

        if (rec.getCustomerId() == null ||
            rec.getCustomerId().getValue() == null) {

            return rec.getValidationResponse();
        }

        String customerId =
                rec.getCustomerId().getValue();

        DataAccess da = new DataAccess(this);

        CustomerRecord customer =
                new CustomerRecord(
                        da.getRecord("CUSTOMER", customerId));

        if (customer.getSector() != null &&
            customer.getSector().getValue() != null) {

            String sector =
                    customer.getSector().getValue();

            if (!"1001".equalsIgnoreCase(sector)) {

                rec.getCustomerId().setError(
                        "Customer Should be individual");
            }
        }

        return rec.getValidationResponse();
    }
}
