package com.ingrid.quotes.util;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class RXManager {

    private CompositeDisposable disposables = new CompositeDisposable();

    public void dispose() {
        disposables.dispose();
    }

    public void onIO(Action action) {
        Disposable disposable = Completable
                .fromAction(action)
                .subscribeOn(Schedulers.io())
                .subscribe();
        disposables.add(disposable);
    }

}
