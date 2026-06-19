package COM;
import java.util.List;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.servicehook.ServiceData;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.hook.system.ServiceLifecycle;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.records.stmbscmultiitharwat.StMbscMultiiTharwatRecord;
import com.temenos.t24.api.system.DataAccess;
/**
 * TODO: Document me!
 *
 * @author Zbook G5
 *
 */
public class Multi extends ServiceLifecycle {
    
    String TransAct,DebAmount,CreditAcc,DebCurr;
    
    @Override
    public void postUpdateRequest(String id, ServiceData serviceData, String controlItem,
            List<TransactionData> transactionData, List<TStructure> records) {
       
        DataAccess da = new DataAccess(this);
        
        TStructure TBR = da.getRecord("ST.MBSC.MULTII.THARWAT", "SYSTEM");
        StMbscMultiiTharwatRecord stm = new StMbscMultiiTharwatRecord (TBR);
        FundsTransferRecord ftr = new FundsTransferRecord();
        
        TransAct =stm.getTransactionType().toString();
        DebAmount=stm.getDebitAmount().toString();
        DebCurr=stm.getDebitCurrency().toString();
        CreditAcc=stm.getCreditAccountNo().toString();
     
         ftr.setTransactionType(TransAct);
         ftr.setDebitAcctNo(id);
         ftr.setDebitAmount(DebAmount);
         ftr.setDebitCurrency(DebCurr);
         ftr.setCreditAcctNo(CreditAcc);
         
          TransactionData td = new TransactionData();
            td.setFunction("INPUT");
            td.setNumberOfAuthoriser("0");
            td.setSourceId("BULK.OFS");
            td.setVersionId("FUNDS.TRANSFER,");
            transactionData.add(td);
            records.add(ftr.toStructure());
     } 
     public List<String> getIds(ServiceData serviceData, List<String> controlList) {
         List<String> recIds = null;
         DataAccess da = new DataAccess(this);
         
         recIds = da.selectRecords("","ACCOUNT","","WITH CURRENCY EQ EUR AND CATEGORY EQ 1001");
         return recIds;
     }
    }

