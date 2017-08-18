package com.braspag.quickshop.api.async;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class ResultAsyncModel<S> {

    private Exception exception;
    private S Model;

    public ResultAsyncModel(){

    }

    public ResultAsyncModel(S model){
        this.Model = model;
    }

    public S getModel() {
        return Model;
    }

    public ResultAsyncModel<S> setModel(S model) {
        Model = model;
        return this;
    }

    public Exception getException() {
        return exception;
    }

    public ResultAsyncModel<S> setException(Exception exception) {
        this.exception = exception;
        return this;
    }
}
