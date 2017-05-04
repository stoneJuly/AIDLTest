package cn.bluetel.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class CalcService extends Service {
    private static final String TAG = "CalcService";

    private final ICalcAIDL.Stub mBinder = new ICalcAIDL.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x + y;
        }

        @Override
        public int min(int x, int y) throws RemoteException {
            return x - y;
        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: >>>");
        return super.onUnbind(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: >>>");
        return mBinder;
    }
}
