package ir.psoft.psoftalertbox.Ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.LineScaleIndicator;

import ir.psoft.psoftalertbox.PsoftAlertBox;
import ir.psoft.psoftalertbox.R;
import ir.psoft.psoftlayoutlib.helper.AndroidUtilities;
import ir.psoft.psoftlayoutlib.helper.LayoutHelper;
import ir.psoft.psoftlayoutlib.helper.ScreenHelper;

/**
 * Created by pouyadark on 12/16/18.
 */

public class AlertBoxActivity extends Activity {
    private static AlertBoxFrameLayout customView;
    private Intent intent;
    private Bundle bundle;
    private TextView txtTitle;
    private ImageView closeicon;
    private static AlertBoxActivity instance;
    private CardView mainCardview;
    private FrameLayout frameLayout;
    private static FrameLayout mainFrameLayout;
    private static PsoftAlertBox alertBox;

    private FrameLayout overlayout;
    private TextView txtResult;
    private AVLoadingIndicatorView progressbar;
    private ImageView imgerroricon;


    public static AlertBoxActivity getInstance() {
        return instance;
    }

    public static void setAlertBox(PsoftAlertBox alertBox) {
        AlertBoxActivity.alertBox = alertBox;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        this.setFinishOnTouchOutside(false);
        intent = getIntent();
        bundle = intent.getExtras();

        frameLayout = new FrameLayout(this);
        int width = (int) (ScreenHelper.getScreenWidth(this) * 0.9);
        mainCardview = new CardView(this);
        mainCardview.setUseCompatPadding(true);
        mainCardview.setCardBackgroundColor(0xffffffff);
        mainCardview.setRadius(AndroidUtilities.dp4);

        txtTitle = new TextView(this);
        txtTitle.setGravity(Gravity.CENTER);
        txtTitle.setPadding(AndroidUtilities.dp5, AndroidUtilities.dp10, AndroidUtilities.dp5, AndroidUtilities.dp10);
        txtTitle.setTextColor(0xffffffff);
        txtTitle.setBackgroundColor(alertBox.titlecolor);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        mainCardview.addView(txtTitle, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.TOP));

        closeicon = new ImageView(this);
        closeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        closeicon.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
        mainCardview.addView(closeicon, LayoutHelper.createFrame(30, 30, Gravity.TOP | Gravity.LEFT, 10, 10, 0, 0));
        //main

        mainFrameLayout = new FrameLayout(this);
        if (customView != null) {
            mainFrameLayout.addView(customView);
            customView.setActivity(this);
        }
        mainCardview.addView(mainFrameLayout, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.TOP, 0, 48, 0, 0));

        //
        mainCardview.addView(adderrorbox(),LayoutHelper.createScroll(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP));
        frameLayout.addView(mainCardview, LayoutHelper.createFrame(width, LayoutHelper.WRAP_CONTENT));
        frameLayout.setLayoutParams(LayoutHelper.createFrame(width, LayoutHelper.WRAP_CONTENT));
        setContentView(frameLayout);
        setData(bundle);

    }
    public void setTypeface(Typeface typeface){
        txtTitle.setTypeface(typeface);
        txtResult.setTypeface(typeface);


    }

    private View adderrorbox() {
    overlayout = new FrameLayout(this);
    overlayout.setVisibility(View.GONE);
    overlayout.setBackgroundColor(0x991c1c1c);
    overlayout.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    });

    imgerroricon=new ImageView(this);
    imgerroricon.setScaleType(ImageView.ScaleType.FIT_XY);


    progressbar=new AVLoadingIndicatorView(this);
    progressbar.setIndicatorColor(0xffffffff);
    progressbar.setIndicator(new LineScaleIndicator());

    txtResult=new TextView(this);
    txtResult.setTextColor(0xffffffff);
    txtResult.setVisibility(View.GONE);
    txtResult.setGravity(Gravity.CENTER);
    txtResult.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);

    overlayout.addView(imgerroricon, LayoutHelper.createFrame(60,60, Gravity.CENTER,0,0,0,10));
    overlayout.addView(txtResult, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER,0,50,0,0));
    overlayout.addView(progressbar,LayoutHelper.createFrame(60,60,Gravity.CENTER));
    return overlayout;
    }

    public void setData(Bundle data) {
        txtTitle.setText(data.getString("title",""));

    }

    public static void setCustomView(AlertBoxFrameLayout customView) {
       AlertBoxActivity.customView=customView;
    }
    public void showProgress(){
        overlayout.setVisibility(View.VISIBLE);
        imgerroricon.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);
        txtResult.setVisibility(View.GONE);
    }
    public void stopProgress(){
        overlayout.setVisibility(View.GONE);
        imgerroricon.setVisibility(View.GONE);
        progressbar.setVisibility(View.GONE);
        txtResult.setVisibility(View.VISIBLE);
    }
    public void showMsg(String msg, final boolean finishafter, final Drawable icon, final Runnable eventsafter){
        if(icon!=null){
            imgerroricon.setVisibility(View.VISIBLE);
            imgerroricon.setImageDrawable(icon);
        }
        overlayout.setVisibility(View.VISIBLE);
        txtResult.setText(msg);
        progressbar.setVisibility(View.GONE);
        txtResult.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        overlayout.setVisibility(View.GONE);
                        txtResult.setVisibility(View.GONE);
                        imgerroricon.setVisibility(View.GONE);
                        progressbar.setVisibility(View.VISIBLE);
                        if(finishafter)finish();
                        if(eventsafter!=null){
                            runOnUiThread(eventsafter);
                        }

                    }
                });
            }
        }, 4000);
    }
    public void showMsg(String msg, final boolean finishafter){
        showMsg(msg,finishafter,null,null);
    }
    public void showMsg(String msg){
        showMsg(msg,false);
    }
}
