package COM.OFS;

import java.util.List;

import com.temenos.api.TStructure;

import com.temenos.t24.api.complex.eb.servicehook.ServiceData;
import com.temenos.t24.api.complex.eb.servicehook.TransactionData;
import com.temenos.t24.api.hook.system.ServiceLifecycle;
import com.temenos.t24.api.records.fundstransfer.FundsTransferRecord;
import com.temenos.t24.api.records.stmbsctofs.StMbscTOfsRecord;
import com.temenos.t24.api.system.DataAccess;

/**
 * TODO: Document me!
 *
 * @author HP
 *
 */
public class OFS extends ServiceLifecycle{
    @Override
    public void postUpdateRequest(String id, ServiceData serviceData, String controlItem,
            List<TransactionData> transactionData, List<TStructure> records) {
        // TODO Auto-generated method stub
        String TRANSACTION,DEBITAMOUNT,DEBITCURRENCY,CREDITACCOUNT;
        DataAccess data = new DataAccess(this);
        
        TStructure tableRecord = data.getRecord("ST.MBSC.T.OFS", "SYSTEM");
        
        StMbscTOfsRecord ft = new StMbscTOfsRecord(tableRecord);
        
        TRANSACTION=ft.getTransactionType().getValue().toString();
        DEBITAMOUNT=ft.getDebitAmount().getValue().toString();
        DEBITCURRENCY=ft.getDebitCurrency().getValue().toString();
        CREDITACCOUNT=ft.getCreditAccount().getValue().toString();
        
        FundsTransferRecord to = new FundsTransferRecord();
        to.setTransactionType(TRANSACTION);
        to.setDebitAcctNo(id);
        to.setDebitAmount(DEBITAMOUNT);
        to.setDebitCurrency(DEBITCURRENCY);
        to.setCreditAcctNo(CREDITACCOUNT);
        
        
        
        ////////////////////////////////////////////////////////
        
        TransactionData td=new TransactionData();
        td.setFunction("INPUT");
        td.setNumberOfAuthoriser("0");
        td.setSourceId("BULK.OFS");
        td.setVersionId("FUNDS.TRANSFER,");
        
        transactionData.add(td);
        records.add(to.toStructure());
    }
    public List<String> getIds(ServiceData serviceData, List<String> controlList) {
        // TODO Auto-generated method stub
        List<String> recids = null;
        DataAccess da = new DataAccess(this);
        recids = da.selectRecords("BNK", "ACCOUNT", "", "WITH CURRENCY EQ USD AND CATEGORY EQ 1001");
        return recids;
    }
}
