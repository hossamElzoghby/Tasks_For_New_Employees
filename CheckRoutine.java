package COM.TEST.F;

import com.temenos.api.TStructure;
import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainemployee.StMbscTrainEmployeeRecord;

public class CheckRoutine extends RecordLifecycle {

    @Override
    public void defaultFieldValues(String application, String currentRecordId, TStructure currentRecord,
            TStructure unauthorisedRecord, TStructure liveRecord, TransactionContext transactionContext) {

        StMbscTrainEmployeeRecord st = new StMbscTrainEmployeeRecord(currentRecord);

        String id = currentRecordId; // format XXX-YY-BBbb

        if (id != null && id.matches("\\d{3}-\\d{2}-\\d{2}[A-Za-z]+")) {

            String[] parts = id.split("-");
            String xxx = parts[0];
            String yy = parts[1];
            String bbbb = parts[2];

            
            st.getTenor().setValue(xxx);

            
            st.getFrequency().setValue(yy);

            
            @SuppressWarnings("unused")
            String prodType = xxx + yy + bbbb;
           

            
            String bb = bbbb.substring(0, 2);
            if (bb.equalsIgnoreCase("MX")) {
                st.getMixedType().setValue("YES");
            }
        }

        currentRecord.set(st.toStructure());
    }
}