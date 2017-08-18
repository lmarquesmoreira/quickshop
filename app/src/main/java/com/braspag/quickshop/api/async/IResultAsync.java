package com.braspag.quickshop.api.async;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public interface IResultAsync<S> {
     void send(ResultAsyncModel<S> result);
}
