package com.stsf.aurora.requestmodel;

public class GenericResponse<T> {
    private T response;

    public GenericResponse(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
