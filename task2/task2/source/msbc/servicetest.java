package task.msbc;

import com.temenos.t24.api.hook.system.ServiceLifecycle;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.records.ststmbscparam.StStMbscParamRecord;
import com.temenos.t24.api.system.DataAccess;
import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.servicehook.ServiceData;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import java.util.List;

/**
 * TODO: Document me!
 *
 * @author YAHYA2
 *
 */

public class servicetest extends ServiceLifecycle{
  
    public void postUpdateRequest(String id, ServiceData serviceData, String controlItem,
            List<TransactionData> transactionData, List<TStructure> records){
        DataAccess da = new DataAccess(this);
    
        TStructure tableRecord = da.getRecord("ST.ST.MBSC.PARAM", "YAHYA");
        StStMbscParamRecord rec = new StStMbscParamRecord(tableRecord);
    
    
       String Transtype = rec.getTransactionType().getValue();
       String DebAmount = rec.getTotalDebitAmount().getValue();
       String DebCurrency = rec.getDebitCcy().getValue();
       String CreditAcc = rec.getCreditAccount().getValue();
    
       FundsTransferRecord ftRec = new FundsTransferRecord();
       ftRec.setTransactionType(Transtype);
       ftRec.setDebitAcctNo(id);               
       ftRec.setDebitAmount(DebAmount);
       ftRec.setDebitCurrency(DebCurrency);
       ftRec.setCreditAcctNo(CreditAcc);
    
    
        TransactionData td = new TransactionData();
        td.setFunction("INPUT");
        td.setNumberOfAuthoriser("0");
        td.setSourceId("BULK.OFS");
        td.setVersionId("FUNDS.TRANSFER,");
        
        
        transactionData.add(td);
        records.add(rec.toStructure());
    
   
    
    
    } 
    
    
    
    
    
    public List<String> getIds(ServiceData serviceData, List<String> controlList) {
        List<String> recIds = null;
        DataAccess da = new DataAccess(this);
       
        
        recIds = da.selectRecords("BNK", "ACCOUNT", "", "WITH CURRENCY EQ USD AND CATEGORY EQ 1001");
        
        return recIds;
    }
}
