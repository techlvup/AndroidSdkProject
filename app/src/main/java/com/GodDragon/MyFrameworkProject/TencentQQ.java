package com.GodDragon.MyFrameworkProject;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;
import org.json.JSONObject;

public class TencentQQ {
    public static String TAG;
    private static Activity m_mainActivity;
    private static String APP_ID;
    private static SdkMessageListener m_callBack;
    private static Tencent m_tencent;

    public static void Init(Activity activity)
    {
        TAG = "TencentQQ";
        m_mainActivity = activity;
        APP_ID = "102093746";
        m_callBack = new SdkMessageListener();
        m_tencent = Tencent.createInstance(APP_ID,activity.getApplicationContext());
    }

    public static void Login()
    {
        Log.d(TAG,"TencentQQ Login");
        m_tencent.login(m_mainActivity,"all",m_callBack);
    }

    public static void LogOut()
    {
        Log.d(TAG,"TencentQQ LogOut");
        m_tencent.logout(m_mainActivity);
    }

    public static void LoginCallBack(JSONObject jsonObject)
    {
        Log.d(TAG,"TencentQQ LoginCallBack");

        try
        {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);

            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId))
            {
                m_tencent.setAccessToken(token,expires);
                m_tencent.setOpenId(openId);
            }

            GameHelper.SendAndroidMessageToUnity(1,0,0,0, token, expires, openId);
        }
        catch (Exception e)
        {
            Log.e(TAG,"TencentQQ LoginCallBack Error" + e.toString());
        }
    }
}
