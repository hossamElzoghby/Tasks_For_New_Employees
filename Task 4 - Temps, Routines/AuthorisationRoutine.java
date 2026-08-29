package com.masaref;

import java.util.List;

import com.temenos.api.TStructure;
import com.temenos.api.exceptions.T24IOException;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainali.StMbscTrainAliRecord;
import com.temenos.t24.api.records.stmbscconcali.StMbscConcAliRecord;
import com.temenos.t24.api.tables.stmbscconcali.StMbscConcAliTable;
/**
 * TODO: Document me!
 *
 * @author Ali Lotfy
 *
 */
public class AuthorisationRoutine extends RecordLifecycle {

    @Override
    public void postUpdateRequest(
            String application,
            String currentRecordId,
            TStructure currentRecord,
            List<TransactionData> transactionData,
            List<TStructure> currentRecords,
            TransactionContext transactionContext) {

        StMbscTrainAliRecord trainRec =
                new StMbscTrainAliRecord(currentRecord);

        if (trainRec.getWriteFlag() == null ||
            trainRec.getWriteFlag().getValue() == null) {
            return;
        }

        if (!"YES".equalsIgnoreCase(
                trainRec.getWriteFlag().getValue())) {
            return;
        }

        StMbscConcAliTable concTable =
                new StMbscConcAliTable(this);

        StMbscConcAliRecord concRec;

        try {
            concRec = concTable.read(currentRecordId);
        } catch (T24IOException e) {
            concRec = new StMbscConcAliRecord();
        }

        concRec.setFlag("YES");

        try {
            concTable.write(currentRecordId, concRec);
        } catch (T24IOException e) {
            throw new RuntimeException(
                    "MBSC.CONC flag update failed", e);
        }
    }
}
