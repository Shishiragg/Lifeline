package com.lifeline;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;



public class ServiceHandler {

    private SensorService mLocalService;
    private boolean isBound = false;

    private Context mContext;

    public ServiceHandler (Context context) {
        this.mContext = context;
    }

    // Function to handle a new Service Connection
    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(mContext,"sensor connected",Toast.LENGTH_LONG).show();
            SensorService.LocalBinder mLocalBinder = (SensorService.LocalBinder) iBinder;
            mLocalService = mLocalBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    void doBindService() {
        Toast.makeText(mContext,"sensor bined",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(mContext, SensorService.class);
        mContext.bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    void doUnbindService() {
        Toast.makeText(mContext,"sensor unbined",Toast.LENGTH_LONG).show();

        mContext.unbindService(myConnection);
    }
}
