package COM.TEST.F;

import com.temenos.api.TStructure;
import com.temenos.api.TValidationResponse;
import com.temenos.t24.api.hook.system.RecordLifecycle;
import com.temenos.t24.api.records.stmbsctrainemployee.StMbscTrainEmployeeRecord;


public class ValidationRoutine extends RecordLifecycle {

    @Override
    public TValidationResponse validateField(String application, String recordId, String fieldData, TStructure record) {

        StMbscTrainEmployeeRecord sf = new StMbscTrainEmployeeRecord(record);

        if ("YES".equalsIgnoreCase(sf.getFloating().getValue().toString())) {

            if (sf.getBasicKey().getValue() == null || sf.getBasicKey().getValue().toString().isEmpty()) {
                sf.getBasicKey().setError("BASIC.KEY, Should be mandatory");
            }

            if (sf.getIntSpread().getValue() == null || sf.getIntSpread().getValue().toString().isEmpty()) {
                sf.getIntSpread().setError("INT.SPREAD, Should be mandatory");
            }
        }

        if ("YES".equalsIgnoreCase(sf.getAccumlated().getValue().toString())) {

            if (sf.getRate().getValue() == null || sf.getRate().getValue().toString().isEmpty()) {
                sf.getRate().setError("RATE, Should be mandatory");
            }

            if (sf.getIntRate().getValue() == null || sf.getIntRate().getValue().toString().isEmpty()) {
                sf.getIntRate().setError("INT.RATE, Should be mandatory");
            }
        }

        return sf.getValidationResponse();
    }
}