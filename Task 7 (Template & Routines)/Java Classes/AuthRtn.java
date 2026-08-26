package com.mbsc;

import java.util.List;


import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainhager.StMbscTrainHagerRecord;
import com.temenos.t24.api.records.stmbscconchager.StMbscConcHagerRecord;

public class AuthRtn extends RecordLifecycle {

    @Override
    public void postUpdateRequest(String application, String currentRecordId, TStructure currentRecord, List<TransactionData> transactionData, List<TStructure> currentRecords,
            TransactionContext transactionContext) {

        StMbscTrainHagerRecord rec = new StMbscTrainHagerRecord(currentRecord);

        if ("Yes".equalsIgnoreCase(rec.getWriteFlag().getValue())) {

            StMbscConcHagerRecord conc =
                    new StMbscConcHagerRecord();

            conc.setFlag("Yes");

            TransactionData td = new TransactionData();

            td.setFunction("INPUT");
            td.setNumberOfAuthoriser("0");
            td.setSourceId("BULK.OFS");
            td.setVersionId("MBSC.CONC.HAGER,INPUT");

            transactionData.add(td);
            currentRecords.add(conc.toStructure());
        }
    }
}