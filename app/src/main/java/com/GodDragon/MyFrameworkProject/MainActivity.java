package com.GodDragon.MyFrameworkProject;

import android.os.Bundle;
import com.unity3d.player.*;

public class MainActivity extends UnityPlayerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameHelper.Init(this);
        TencentQQ.Init(this);
    }
}