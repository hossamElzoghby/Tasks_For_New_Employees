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
public class VALIDATIONROUTEN  extends RecordLifecycle {

    @Override
    public TValidationResponse validateField(String application, String recordId, String fieldData, TStructure record) {
        // TODO Auto-generated method stub
        
        StMbscTrainEzzatRecord stm  = new  StMbscTrainEzzatRecord(record);
        
     
        if (fieldData.equals("YES")) {
            if (stm.getBasicKey().getValue().isEmpty()) {
                stm.getBasicKey().setError("(BASIC.KEY), Should be mandatory");
            }
        }
        
        if (fieldData.equals("YES")) {
            if (stm.getIntSpread().getValue().isEmpty()) {
                stm.getIntSpread().setError("(INT.Spread), Should be mandatory");
            }
        }
        
       
        
     
        return stm.getValidationResponse();
    }

}
