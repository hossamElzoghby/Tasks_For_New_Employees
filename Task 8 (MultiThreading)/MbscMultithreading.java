package com.mbsc;

import java.util.List;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.servicehook.ServiceData;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.hook.system.ServiceLifecycle;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.system.DataAccess;

/**
 * TODO: Document me!
 *
 * @author Hager
 *
 */
public class MbscMultithreading extends ServiceLifecycle{

    @Override
    public List<String> getIds(ServiceData serviceData, List<String> controlList) {
        DataAccess da = new DataAccess(this);
        List<String> idsList = da.selectRecords("", "ACCOUNT", "", "WITH CATEGORY EQ 1001 AND CURRENCY EQ USD");
        return idsList;
    }

    @Override
    public void postUpdateRequest(String id, ServiceData serviceData, String controlItem,
            List<TransactionData> transactionData, List<TStructure> records) {
        
        FundsTransferRecord ftRec = new FundsTransferRecord();
        ftRec.setTransactionType("AC");
        ftRec.setDebitAcctNo(id);
        ftRec.setDebitCurrency("USD");
        ftRec.setDebitAmount("10");
        ftRec.setCreditAcctNo("100315");
        
        
        TransactionData td = new TransactionData();
        td.setFunction("INPUT");    
        td.setNumberOfAuthoriser("0");
        td.setSourceId("BULK.OFS");
        td.setVersionId("FUNDS.TRANSFER,BATCH2");
        transactionData.add(td);
        records.add(ftRec.toStructure());
    }
    
    
}
