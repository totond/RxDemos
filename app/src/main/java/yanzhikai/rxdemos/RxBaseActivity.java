package yanzhikai.rxdemos;

import android.support.v7.app.AppCompatActivity;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

/**
 * author : yany
 * e-mail : yanzhikai_yjk@qq.com
 * time   : 2017/12/14
 * desc   :
 */

public class RxBaseActivity extends AppCompatActivity {
    protected Observable<String> mObservable;
    protected Observer<String> mObserver;
    protected Subscriber<String> mSubscriber;
    protected Consumer mConsumer;

}
