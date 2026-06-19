package Test;


import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainezzat.*;

/**
 * TODO: Document me!
 *
 * @author Mohamed Ezzat
 *
 */

public class CHECHRTN extends RecordLifecycle {

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
        
        String [] Parts = currentRecordId.split("-");
        StMbscTrainEzzatRecord sms = new  StMbscTrainEzzatRecord(currentRecord);
        sms.setTenor(Parts[0]);
        sms.setFrequency(Parts[1]);
        
        String BB = currentRecordId.substring(currentRecordId.length()- 2);
        
        if (BB.equals("MX")) {
            sms.setMixedType("YES");
        }
        currentRecord.set(sms.toStructure());
       
    }

}
