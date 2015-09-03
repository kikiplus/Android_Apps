package com.kiki.android.Logic.Managers.push;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceID;

import com.kiki.android.Utils.ContextUtils;
import com.kiki.android.Utils.SharedPreferenceUtils;

/***
 * @author grape girl
 * @version 1.0
 * @Class Name : SafetyUnRegistrationService
 * @Description : GCM 서버로부터 토큰 해지 요청 서비스
 * @since 2015. 6. 8.
 */
public class SafetyUnRegistrationService extends IntentService {

    private static final String TAG = SafetyUnRegistrationService.class.getSimpleName();


    public SafetyUnRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            synchronized (TAG) {
                InstanceID instanceID = InstanceID.getInstance(this);
                InstanceID.getInstance(this).deleteInstanceID();
                Log.d(ContextUtils.LOG, "@@ SafetyUnRegistrationService token 해지 ");
                SharedPreferenceUtils.write(this, ContextUtils.KEY_PROJECT_ID, null);
            }
        } catch (Exception e) {
            Log.d(ContextUtils.LOG, "SafetyUnRegistrationService 해지 Exception");
        }
    }

    @Override
    public void onDestroy() {
//		super.onDestroy();
        Log.d(ContextUtils.LOG, "SafetyUnRegistrationService onDestroy");
    }
}
