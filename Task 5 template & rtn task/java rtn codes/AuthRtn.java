package com.mbsc;


import com.temenos.api.TStructure;
import com.temenos.api.exceptions.T24IOException;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbscconcstev.StMbscConcStevRecord;
import com.temenos.t24.api.records.stmbsctrainstv.StMbscTrainStvRecord;
import com.temenos.t24.api.tables.stmbscconcstev.StMbscConcStevTable;

import java.util.List;

/**
 * TODO: Document me!
 *
 * @author Steven Nagy
 *
 */
public class AuthRtn extends RecordLifecycle {

    @Override
    public void postUpdateRequest(String application, String currentRecordId, TStructure currentRecord,
            List<TransactionData> transactionData, List<TStructure> currentRecords,
            TransactionContext transactionContext) {

        StMbscTrainStvRecord trainRec = new StMbscTrainStvRecord(currentRecord);

        if ("YES".equalsIgnoreCase(trainRec.getWriteFlag().getValue())) {

            StMbscConcStevTable concTable = new StMbscConcStevTable(this);

            StMbscConcStevRecord concRec;
            try {
                concRec = concTable.read(currentRecordId);
            } catch (T24IOException e) {
                concRec = new StMbscConcStevRecord();
            }

            concRec.setFlag("YES");

            try {
                concTable.write(currentRecordId, concRec);
            } catch (T24IOException e) {
                throw new RuntimeException("MBSC.CONC flag update failed", e);
            }
        }
    }
}