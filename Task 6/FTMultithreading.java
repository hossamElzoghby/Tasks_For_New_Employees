package com.mbsc.train;

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
 * @author nada
 *
 */
public class FTMultithreading extends ServiceLifecycle{

    @Override
    public List<String> getIds(ServiceData serviceData, List<String> controlList) {
        // TODO Auto-generated method stub
        
        List<String> recIds = null;
        DataAccess da = new DataAccess(this);
        
        recIds = da.selectRecords("BNK", "ACCOUNT", "", "WITH CURRENCY EQ EUR AND CATEGORY EQ 1001");
        
        return recIds;
      
    }

    @Override
    public void postUpdateRequest(String id, ServiceData serviceData, String controlItem,
            List<TransactionData> transactionData, List<TStructure> records) {

        FundsTransferRecord ftRec = new FundsTransferRecord();
        
        ftRec.setTransactionType("AC");
        ftRec.setDebitAcctNo(id);
        ftRec.setDebitCurrency("EUR");
        ftRec.setDebitAmount("10");
        ftRec.setCreditAcctNo("120456");
        
        TransactionData td = new TransactionData();
        td.setFunction("INPUT");    
        td.setNumberOfAuthoriser("0");
        td.setSourceId("BULK.OFS");
        td.setVersionId("FUNDS.TRANSFER,OFS");
        records.add(ftRec.toStructure());
        transactionData.add(td);
    }
    
    

}
