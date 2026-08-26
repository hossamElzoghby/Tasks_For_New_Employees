package com.mbsc.train;

import java.util.List;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.aclockedevents.AcLockedEventsRecord;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;

/**
 * TODO: Document me!
 *
 * @author nada
 *
 */
public class OFSTask extends RecordLifecycle {

    @Override
    public void postUpdateRequest(String application, String currentRecordId, TStructure currentRecord,
            List<TransactionData> transactionData, List<TStructure> currentRecords,
            TransactionContext transactionContext) {
        
        FundsTransferRecord ftRec = new FundsTransferRecord(currentRecord);
        String debitAcc = ftRec.getDebitAcctNo().getValue().toString();
        String debitAmmount = ftRec.getDebitAmount().getValue().toString();
        
        AcLockedEventsRecord acLock = new AcLockedEventsRecord();
        
        acLock.setAccountNumber(debitAcc);
        acLock.setLockedAmount(debitAmmount);
        
        TransactionData td = new TransactionData();
        td.setFunction("INPUT");    
        td.setNumberOfAuthoriser("0");
        td.setSourceId("BULK.OFS");
        td.setVersionId("AC.LOCKED.EVENTS,MBSC");
        transactionData.add(td);
        currentRecords.add(acLock.toStructure());
    }
}
