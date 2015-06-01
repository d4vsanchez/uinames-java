package name.davidsanchez.uiname;

import java.util.List;
import java.util.Map;

/**
 * @author David Sanchez
 * @version 1.0.0
 * @since 2015-05-31
 */
interface UIRequestResponse {
    void receiveData(List<Map<String, Object>> rawValues, UINameResponse uiNameResponse);
}
