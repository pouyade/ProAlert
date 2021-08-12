package ir.psoft.psoftalertbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import ir.psoft.psoftalertbox.Ui.AlertBoxActivity;
import ir.psoft.psoftalertbox.Ui.AlertBoxFrameLayout;

/**
 * Created by pouyadark on 12/16/18.
 */

public class PsoftAlertBox {
    private int primaryColor;
    private Bundle bundle;
    private AlertBoxFrameLayout customView;
    private boolean isCustom=false;
    private Intent intent;
    private Context context;
    public int titlecolor =0xff000000;
    public PsoftAlertBox(Context context,int titlecolor) {
        this.context=context;
        this.titlecolor=titlecolor;
        intent=new Intent(context, AlertBoxActivity.class);
        bundle=new Bundle();
    }
    public PsoftAlertBox setTitle(String title){
        bundle.putString("title",title);
        return this;
    }
    public PsoftAlertBox setCustomView(AlertBoxFrameLayout view){
        isCustom=true;
        view.setAlertBox(this);
        this.customView=view;
        return this;
    }
    public void show(){
        AlertBoxActivity.setAlertBox(this);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if(isCustom){
            AlertBoxActivity.setCustomView(customView);
        }
    }
}
