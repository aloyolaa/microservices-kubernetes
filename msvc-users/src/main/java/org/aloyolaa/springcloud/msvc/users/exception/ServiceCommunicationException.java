package org.aloyolaa.springcloud.msvc.users.exception;

public class ServiceCommunicationException extends RuntimeException {
    public ServiceCommunicationException(String service) {
        super("Error communicating with " + service.toUpperCase() + " service. Please try again later.");
    }
}
