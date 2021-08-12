package ir.psoft.psoftalertbox.Ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import android.widget.FrameLayout;

import ir.psoft.psoftalertbox.PsoftAlertBox;

/**
 * Created by pouyadark on 12/16/18.
 */

public class AlertBoxFrameLayout extends FrameLayout {
    private PsoftAlertBox alertBox;
    private AlertBoxActivity activity;

    public AlertBoxFrameLayout( Context context) {
        super(context);
    }

    public void setAlertBox(PsoftAlertBox alertBox) {
        this.alertBox = alertBox;
    }
    public void finish(){
        this.activity.finish();
    }
    public void setActivity(AlertBoxActivity activity) {
        this.activity = activity;
    }
    public void showMsg(String msg, Boolean finishafter, Drawable drawable,Runnable runafter){
        this.activity.showMsg(msg,finishafter,drawable,runafter);

    }
    public void showMsg(String msg, Boolean finishafter, Drawable drawable){
        this.activity.showMsg(msg,finishafter,drawable,null);
    }
    public void showMsg(String msg,Boolean finishafter){
        this.activity.showMsg(msg,finishafter);
    }
    public void showProgress(){
        this.activity.showProgress();
    }
    public void stopProgress(){
        this.activity.stopProgress();
    }

}
