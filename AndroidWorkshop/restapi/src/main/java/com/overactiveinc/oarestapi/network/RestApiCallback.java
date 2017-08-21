package com.overactiveinc.oarestapi.network;

/**
 * Created by slaport on 11/22/16.
 */

public interface RestApiCallback<T> {
    /**
     * This method must be implemented inline in any Activity, Fragment or etc to handle
     * networking success callback
     *
     * @param object
     */
    @SuppressWarnings("unused")
    void onSuccess(T object);

    /**
     * This method must be implemented inline in any Activity, Fragment or etc to handle
     * networking error callback. Such error are supposed to be handled errors
     */
    @SuppressWarnings("unused")
    void onError(String message
    );
}