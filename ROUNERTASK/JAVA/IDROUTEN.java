package Test;


import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;


public class IDROUTEN extends RecordLifecycle {
    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {
        // TODO Auto-generated method stub
         String regex = "^\\d{3}-\\d{2}-\\d{2}[a-zA-Z]{2}$";
        
        if (!currentRecordId.matches(regex)) {
            throw new Error("INVALID ID FORMAT"); 
        }
        String[] parts = currentRecordId.split("-");
        int yy = Integer.parseInt(parts[1]);
        int bb = Integer.parseInt(parts[2].substring(0, 2));

        if (yy > bb) {
            throw new Error("INVALID ID FORMAT");
        }
        
        return currentRecordId;
    }

}
