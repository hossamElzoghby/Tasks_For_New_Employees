package Test;


import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainezzat.*;

/**
 * TODO: Document me!
 *
 * @author Mohamed Ezzat
 *
 */
public class VALIDATIONTWO extends RecordLifecycle {

    @Override
    public TValidationResponse validateField(String application, String recordId, String fieldData, TStructure record) {
        // TODO Auto-generated method stub
        
        StMbscTrainEzzatRecord stm = new StMbscTrainEzzatRecord(record);
        if (fieldData.equals("YES")) {
            if (stm.getRate().getValue().isEmpty()) {
                stm.getRate().setError("(RATE"
                        + "), Should be mandatory");
            }
        }
        
        if (fieldData.equals("YES")) {
            if (stm.getIntRate().getValue().isEmpty()) {
                stm.getIntRate().setError("(INT.RATE), Should be mandatory");
            }
        }
        
        
     
        return stm.getValidationResponse();
    }

}