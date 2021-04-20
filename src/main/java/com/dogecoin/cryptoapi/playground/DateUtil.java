package com.dogecoin.cryptoapi.playground;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static void main(String[] args) {
        Date old = new Date("2021-04-20 00:40:00");
        Date timestamp = getFormattedTimestamp("2021-04-20 00:40:00");
        boolean result = isPast(old);
        System.out.println("Result: "+ result);

    }

    public static Date getFormattedTimestamp(String timestamp) {
        Date formattedTimestamp = null;
        if (timestamp == null) return formattedTimestamp;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formattedTimestamp = dateFormat.parse(timestamp);
            Date currentTStamp = new Date();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return formattedTimestamp;
    }

    public static boolean isPast(Date timestamp) {
        try {
            Date currentTStamp = new Date();
            System.out.println("Current timestamp: " + currentTStamp);
            if (timestamp.before(currentTStamp)) return true;
        } catch(Exception ex) {
            System.out.println(ex);
        }

        return  false;
    }

    public static Date addMinutes(String timestamp) {
        return new Date();
    }

/**
    public static String getAnalystStatus() {
        String accountId = "101";
        String userId = "42";
        String token = "xxxxx";

        String statusObject = someDBQuery(accountId, userId);
        String currentStatus = "active";
        String expiryTime = null;
        boolean isPast = false;

        if (statusObject) {
            currentStatus = statusObject.status;
            expiryTime = statusObject.expiryTime;

            if (expiryTime) {
                // means timestamp is set
                isPast = isPast(expiryTime);

            } else if(token != statusObject.token) {
                // its old session
                isPast = true;
            }
        }

        if(isPast) {
            // it means this is expired and update this statusObject
            statusObject.isActive = 0;
            // set expiry time only if it was not set
            if(!statusObject.expiryTime) statusObject.expiryTime = new Date();
            saveOrUpdate(statusObject);
            currentStatus = "active";
        }
        return currentStatus;
    }

    public static String createOrUpdateAnalystStatus(String status, String expiryTime) {
        String accountId = "101";
        String userId = "42";
        String token = "xxxxx";

        String oldStatusObject = someDBQuery(accountId, userId);

        if (oldStatusObject) {
            oldStatusObject.expiryTime = new Date();
            oldStatusObject.isActive = 0;
            saveOrUpdate(oldStatusObject);
        }

        String newStatusObject = null;
        newStatusObject.status = status;
        newStatusObject.expiryTime = expiryTime;
        newStatusObject.token = token;

        saveOrUpdate(newStatusObject);

        return "SUCCESS";
    }
**/
}
