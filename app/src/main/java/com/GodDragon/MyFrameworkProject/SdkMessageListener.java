package com.GodDragon.MyFrameworkProject;

import android.util.Log;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

public class SdkMessageListener implements IUiListener
{
    @Override
    public void onComplete(Object o) {
        if (o == null)
        {
            return;
        }

        JSONObject jsonObject = (JSONObject) o;

        if (jsonObject.length() == 0)
        {
            return;
        }

        Log.d(TencentQQ.TAG,"登录成功!");

        TencentQQ.LoginCallBack(jsonObject);
    }

    @Override
    public void onError(UiError uiError) {
        Log.d(TencentQQ.TAG,"登录失败!");
    }

    @Override
    public void onCancel() {
        Log.d(TencentQQ.TAG,"取消登录!");
    }

    @Override
    public void onWarning(int i) {
        Log.d(TencentQQ.TAG,"登录警告：( " + i + " )!");
    }
}