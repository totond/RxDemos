package yanzhikai.rxdemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_base,btn_schedulers,btn_maps,btn_zips,btn_emit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_base = (Button) findViewById(R.id.btn_base);
        btn_base.setOnClickListener(this);
        btn_schedulers = (Button) findViewById(R.id.btn_schedulers);
        btn_schedulers.setOnClickListener(this);
        btn_maps = (Button) findViewById(R.id.btn_maps);
        btn_maps.setOnClickListener(this);
        btn_zips = (Button) findViewById(R.id.btn_zips);
        btn_zips.setOnClickListener(this);
        btn_emit = (Button) findViewById(R.id.btn_emit);
        btn_emit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_base:
                startActivity(new Intent(MainActivity.this,RxBaseUseActivity.class));
                break;
            case R.id.btn_schedulers:
                startActivity(new Intent(MainActivity.this,RxSchedulersActivity.class));
                break;

            case R.id.btn_maps:
                startActivity(new Intent(MainActivity.this,RxMapsActivity.class));
                break;
            case R.id.btn_zips:
                startActivity(new Intent(MainActivity.this,RxZipActivity.class));
                break;
            case R.id.btn_emit:
                startActivity(new Intent(MainActivity.this,RxEmitActivity.class));
                break;
        }
    }
}
