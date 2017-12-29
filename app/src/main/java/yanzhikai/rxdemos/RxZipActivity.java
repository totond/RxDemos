package yanzhikai.rxdemos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxZipActivity extends AppCompatActivity {
    public static final String TAG = "RxZipActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_zip);
//        zipTestSyn();
        zipTestNonSyn();
    }

    private void zipTestSyn(){
        Observable observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello");
                Log.d(TAG, "1 emit: hello");
                e.onNext("world");
                Log.d(TAG, "1 emit: world");
                e.onNext("!");
                Log.d(TAG, "1 emit: !");
                e.onComplete();
                Log.d(TAG, "1 emit: onComplete");
            }
        });

        Observable observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Log.d(TAG, "2 emit: 1");
                e.onNext(2);
                Log.d(TAG, "2 emit: 2");
                e.onNext(3);
                Log.d(TAG, "2 emit: 3");
                e.onNext(4);
                Log.d(TAG, "2 emit: 4");
                e.onNext(5);
                Log.d(TAG, "2 emit: 5");
                e.onComplete();
                Log.d(TAG, "2 emit: onComplete");
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<String,Integer,String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }

    private void zipTestNonSyn(){
        Observable observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "1 emit: hello");
                e.onNext("hello");
                Thread.sleep(1000);
                Log.d(TAG, "1 emit: world");
                e.onNext("world");
                Thread.sleep(1000);
                Log.d(TAG, "1 emit: !");
                e.onNext("!");
                Thread.sleep(1000);
                Log.d(TAG, "1 emit: onComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "2 emit: 1");
                e.onNext(1);
                Thread.sleep(1000);
                Log.d(TAG, "2 emit: 2");
                e.onNext(2);
                Thread.sleep(1000);
                Log.d(TAG, "2 emit: 3");
                e.onNext(3);
                Thread.sleep(1000);
                Log.d(TAG, "2 emit: 4");
                e.onNext(4);
                Thread.sleep(1000);
                Log.d(TAG, "2 emit: 5");
                e.onNext(5);
                Thread.sleep(1000);
                Log.d(TAG, "2 emit: onComplete");
                e.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<String,Integer,String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }
}
