package org.community.server.api.common;

public class ApiSuccessMessage {
    // User Message
    public final static String SUCCESS_ADD_USER = "user registration has been completed successfully.";
    public final static String SUCCESS_REMOVE_USER = "user withdrawal has been completed successfully.";
    public final static String SUCCESS_FIND_USER_ONE = "single User find query has been completed successfully.";
    // End of User Message

    // InoutData Message
    public final static String SUCCESS_ADD_INBOUND_SCANNER_DATA = "saving inbound scanner data processing has been completed successfully.";
    public final static String SUCCESS_ADD_INBOUND_WMS_DATA = "saving inbound wms data processing has been completed successfully.";
    public final static String SUCCESS_ADD_OUTBOUND_SCANNER_DATA = "saving outbound scanner data processing has been completed successfully.";
    public final static String SUCCESS_ADD_OUTBOUND_WMS_DATA = "saving outbound wms data processing has been completed successfully.";
    public final static String SUCCESS_FIND_CLASSIFIED_LEADTIME_PERIOD = "finding data includes classified leadtime period successfully.";
    public final static String SUCCESS_FIND_LEADTIME_PERIOD = "finding data includes leadtime period successfully.";
    public final static String SUCCESS_FIND_WM_LOCATIONS = "warehouse location information inquired successfully.";
    public final static String SUCCESS_FIND_UPLOAD_HISTORY = "finding upload histories successfully.";
    public final static String SUCCESS_FIND_ALL_YEAR_MONTH = "finding all upload year-month successfully.";
    // End of InoutData Message

    // Aggregated Message
    public final static String SUCCESS_FIND_DATA = "finding aggregated inout data successfully.";
    // End of Aggregated Message

    // Device Message
    public final static String SUCCESS_REGISTER_DEVICE = "device registration has done successfully.";
    public final static String SUCCESS_CHECKING_REGISTER_DEVICE = "device has been registered before.";
    public final static String SUCCESS_CHECKING_INBOUND_DEVICE_IS_ACTIVE = "checking inbound scanner status is active successfully.";
    public final static String SUCCESS_CHECKING_OUTBOUND_DEVICE_IS_ACTIVE = "checking outbound scanner status is active successfully.";
    // End of Device Message
}
