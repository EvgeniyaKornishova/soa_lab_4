package ru.ifmo.soa.lab4.primary_back.data;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
public class SOAPException extends Exception {
    public SOAPException(String message) {
        super(message);
    }
}