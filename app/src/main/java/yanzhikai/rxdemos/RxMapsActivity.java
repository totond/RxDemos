package yanzhikai.rxdemos;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxMapsActivity extends RxBaseActivity {
    public static final String TAG = "RxMapsActivity";
    private int mInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_map);
        flapMapTest();
    }

    private void mapTest(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello");
                e.onNext("world");
                e.onNext("！");
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return mInt++;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) throws Exception {
                Log.d(TAG, "accept: " + i);
            }
        });
    }

    private void flapMapTest(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello");
                e.onNext("world");
                e.onNext("！");
            }
        }).flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(String s) throws Exception {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < 3; i++){
                    list.add(i);
                }
                return Observable.fromIterable(list).delay(15, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
            }
        });
    }
}
