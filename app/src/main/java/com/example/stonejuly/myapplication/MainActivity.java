package com.example.stonejuly.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.bluetel.server.ICalcAIDL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ICalcAIDL mICalcAIDL;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: >>>");
            mICalcAIDL = ICalcAIDL.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: >>>");
            mICalcAIDL = null;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calc(View view) throws RemoteException {
        if (mICalcAIDL != null) {
            int result = mICalcAIDL.add(1, 2);
            Toast.makeText(this, "result = " + result, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "服务被异常杀死，请重启服务", Toast.LENGTH_SHORT).show();
        }
    }

    public void bind(View view) {
        Intent intent = new Intent();
        intent.setAction("cn.bluetel.server.calc");
        intent.setPackage("cn.bluetel.server");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        unbindService(mConnection);
    }

}
