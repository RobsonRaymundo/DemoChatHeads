package com.droid.ray.demochatheads;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


public class DroidHeadService extends Service {
    private WindowManager windowManager;
    private ImageView chatHead;

    private int initialX;
    private int initialY;
    private float initialTouchX;
    private float initialTouchY;
    private int orientationEvent;
    private Context context;
    private View.OnTouchListener onTouchListener;
    private Intent mIntentService;

    public enum EnumStateButton {
        CLOSE,
        VIEW
    }

    private EnumStateButton StateButton;

    WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT);

    private void TimeSleep(Integer seg) {
        try {
            Thread.sleep(seg);
        } catch (Exception ex) {
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Not used
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mIntentService = intent;
        TimeSleep(1000);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        InicializarVariavel();
        InicializarAcao();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //call widget update methods/services/broadcasts

    }

    @Override
    public void onDestroy() {
        if (chatHead != null) windowManager.removeView(chatHead);
        Vibrar(100);
        super.onDestroy();
    }

    private void Vibrar(int valor) {
        try {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(valor);
        } catch (Exception ex) {
        }
    }

    private void InicializarVariavel() {
        context = getBaseContext();

        windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        onTouchListener = new TouchListener();

        chatHead = new ImageView(context);
        chatHead.setImageResource(R.mipmap.viewrec);
        StateButton = EnumStateButton.VIEW;
        params.gravity = Gravity.CENTER;
        windowManager.addView(chatHead, params);
    }

    private void InicializarAcao() {
        chatHead.setOnTouchListener(onTouchListener);
    }


    public class TouchListener implements View.OnTouchListener {

        private GestureDetector gestureDetector = new GestureDetector(DroidHeadService.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (StateButton == EnumStateButton.VIEW) {
                    chatHead.setImageResource(R.mipmap.closerec);
                    StateButton = EnumStateButton.CLOSE;
                }
                else
                {
                    chatHead.setImageResource(R.mipmap.viewrec);
                    StateButton = EnumStateButton.VIEW;
                }
                return super.onDoubleTap(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (StateButton == EnumStateButton.VIEW) {

                }
                else
                {
                    context.stopService(mIntentService);
                    TimeSleep(1000);

                }
                return super.onSingleTapConfirmed(e);
            }
        });

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialX = params.x;
                    initialY = params.y;
                    initialTouchX = event.getRawX();
                    initialTouchY = event.getRawY();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    Integer totalMoveX = (int) (event.getRawX() - initialTouchX);
                    params.x = initialX + totalMoveX;
                    Integer totalMoveY = (int) (event.getRawY() - initialTouchY);
                    params.y = initialY + totalMoveY;
                    windowManager.updateViewLayout(chatHead, params);
                    return true;
            }

            return true;
        }

    }





}





