package survey.backend.error;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class NoDataFoundError extends RuntimeException {

    public NoDataFoundError(String message) {
        super("No data found: " + message);
    }

    public static NoDataFoundError withId(String itemType, long id) {
        return new NoDataFoundError(itemWithIdMessage(itemType, id));
    }

    public static NoDataFoundError withIds(Map<String,Long> itemTypeIdMap) {
        return new NoDataFoundError(
                itemsWithIdMessage(itemTypeIdMap)
        );

    }

    public static NoDataFoundError withIds(
            Map<String,Long> itemTypeIdMap,
            Map<String,Collection<Long>> itemTypeIdsMap
    )
    {
        return new NoDataFoundError(
                itemsWithIdMessage(itemTypeIdMap)
                        + " or "
                        + itemsWithIdsMessage(itemTypeIdsMap));
    }

    public static NoDataFoundError noResults(String itemType, String message) {
        return new NoDataFoundError(
                itemType
                + " return 0 results"
                + " with "
                + message
        );
    }

    private static String itemWithIdMessage(String itemType, Long id) {
        return String.format("%s with id <%d>", itemType, id);
    }

    private static String itemWithIdsMessage(String itemType, Collection<Long> ids) {
        return String.format("%s with ids <%s>",
                itemType,
                ids.stream()
                        .map(id -> "" + id)
                        .collect(Collectors.joining(",")));
    }

    private static String itemsWithIdMessage(Map<String, Long> itemTypeIdMap) {
        return itemTypeIdMap.entrySet().stream()
                .map(itemTypeId -> itemWithIdMessage(
                        itemTypeId.getKey(), // item type
                        itemTypeId.getValue())) // id
                .collect(Collectors.joining(" or "));
    }

    private static String itemsWithIdsMessage(Map<String, Collection<Long>> itemTypeIdsMap) {
        return itemTypeIdsMap.entrySet().stream()
                .map(itemTypeId -> itemWithIdsMessage(
                        itemTypeId.getKey(), // item type
                        itemTypeId.getValue()) // ids
                )
                .collect(Collectors.joining(" or "));
    }
}
