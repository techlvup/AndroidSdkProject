package com.GodDragon.MyFrameworkProject;

import android.util.Log;
import com.unity3d.player.UnityPlayer;
import org.json.JSONException;
import org.json.JSONObject;

public class GameHelper {
    private static MainActivity m_activity = null;
    private static String m_platformObject = "SdkObject";//unity用于通信的对象
    private static String m_methodName = "UnityReceiveAndroidMessage";//unity用于通信的函数名
    public static String TAG = "GameHelper";

    public static void Init(MainActivity activity)
    {
        m_activity = activity;
    }

    //发送Android消息到Unity
    public static void SendAndroidMessageToUnity(int iMsgId, int iParam1, int iParam2, int iParam3, String strParam1, String strParam2, String strParam3)
    {
        String jsonString = GetJsonStr(iMsgId, iParam1, iParam2, iParam3, strParam1, strParam2, strParam3);
        UnityPlayer.UnitySendMessage(m_platformObject,m_methodName,jsonString);
    }

    //Android接收Unity消息
    public static void AndroidReceiveUnityMessage(int iMsgId, int iParam1, int iParam2, int iParam3, String strParam1, String strParam2, String strParam3)
    {
        Log.d(TAG,"AndroidReceiveUnityMessage：iMsgId:" + iMsgId + " iParam1:" + iParam1 + " iParam2:" + iParam2 + " iParam3:" + iParam3 + " strParam1:" + strParam1 + " strParam2:" + strParam2 + " strParam3:" + strParam3);

        if (m_activity == null)
        {
            Log.e(TAG,"m_activity is null");
        }
    }

    //通过Json对象构建字符串
    public static String GetJsonStr(int iMsgId, int iParam1, int iParam2, int iParam3, String strParam1, String strParam2, String strParam3)
    {
        try
        {
            JSONObject object = new JSONObject();
            object.put("iMsgId",iMsgId);
            object.put("iParam1",iParam1);
            object.put("iParam2",iParam2);
            object.put("iParam3",iParam3);
            object.put("strParam1",strParam1);
            object.put("strParam2",strParam2);
            object.put("strParam3",strParam3);
            return object.toString();
        }
        catch (JSONException e)
        {
            Log.e(TAG,"Json Error" + e.toString());
            return "";
        }
    }
}
