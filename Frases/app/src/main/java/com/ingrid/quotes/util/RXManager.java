package com.ingrid.quotes.util;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RXManager {

    private CompositeDisposable disposables = new CompositeDisposable();

    public void dispose() {
        disposables.dispose();
    }

    public void onIO(Runnable runnable) {
        Disposable disposable = Observable.fromCallable(() -> {
            runnable.run();
            return false;
        }).subscribeOn(Schedulers.io()).subscribe();
        disposables.add(disposable);
    }

}
