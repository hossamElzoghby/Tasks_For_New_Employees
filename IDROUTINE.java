package COM.TEST.F;

import com.temenos.t24.api.complex.eb.templatehook.TransactionContext;
import com.temenos.t24.api.hook.system.RecordLifecycle;

public class IDROUTINE extends RecordLifecycle {

    @Override
    public String checkId(String currentRecordId, TransactionContext transactionContext) {

        String id = currentRecordId;

        if (id != null && id.matches("\\d{3}-\\d{2}-\\d{2}[A-Za-z]+")) {

            String[] parts = id.split("-");
            int yy = Integer.parseInt(parts[1]);
            int bb = Integer.parseInt(parts[2].substring(0, 2));

            if (yy > bb) {
                throw new RuntimeException("INVALID ID FORMAT");
            }

        } else {
            throw new RuntimeException("INVALID ID FORMAT");
        }

        return currentRecordId;
    }
}