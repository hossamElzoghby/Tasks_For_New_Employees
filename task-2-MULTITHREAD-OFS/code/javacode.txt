package Sservices;

import java.util.List;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.servicehook.ServiceData;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.hook.system.ServiceLifecycle;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.records.sttranfertest.StTranfertestRecord;


import com.temenos.t24.api.system.DataAccess;

/**
 * @author Norhan
 */
public class FTService extends ServiceLifecycle {
    
    @Override
    public List<String> getIds(ServiceData serviceData, List<String> controlList) {
        List<String> recIds = null;
        DataAccess da = new DataAccess(this);
        
        recIds = da.selectRecords("BNK", "ACCOUNT", "", "WITH CURRENCY EQ EUR AND CATEGORY EQ 1001");
        
        return recIds;
    }

    @Override
    public void postUpdateRequest(String id, ServiceData serviceData, String controlItem,
            List<TransactionData> transactionData, List<TStructure> records) {
        
        DataAccess ds = new DataAccess(this);
        
        StTranfertestRecord st = new StTranfertestRecord(ds.getRecord("ST.TRANFERTEST", "1212"));
        
        FundsTransferRecord ftRec = new FundsTransferRecord();
        
        ftRec.setTransactionType(st.getTransactionType().getValue().toString());
        ftRec.setDebitAcctNo(st.getDebitId().getValue().toString());
        ftRec.setCreditAcctNo(id); 
        ftRec.setDebitCurrency(st.getDibetCurancy().getValue().toString());
        ftRec.setCreditCurrency("EUR");
        ftRec.setCreditAmount(st.getAmount().getValue());
        
        TransactionData td = new TransactionData();
        td.setFunction("INPUT");
        td.setNumberOfAuthoriser("0");
        td.setSourceId("BULK.OFS");
        td.setVersionId("FUNDS.TRANSFER,");
        
        transactionData.add(td);
        records.add(ftRec.toStructure());
    }
}