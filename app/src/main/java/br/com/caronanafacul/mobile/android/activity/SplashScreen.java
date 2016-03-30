package br.com.caronanafacul.mobile.android.activity;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MotionEvent;

        import br.com.caronanafacul.mobile.android.R;

public class SplashScreen extends Activity {

    private Thread tSplachScreen;
    private boolean bSplachScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        tSplachScreen = new Thread(){

            public void run() {
                try{
                    synchronized (this) {
                        wait(5000);
                        bSplachScreen = true;
                    }
                }catch(InterruptedException ex){

                }

                if(bSplachScreen){
                    finish();

                    Intent i = new Intent();
                    i.setClass(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }
            };
        };

        tSplachScreen.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        tSplachScreen.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            synchronized (tSplachScreen) {
                bSplachScreen = true;

                tSplachScreen.notifyAll();
            }
        }

        return true;
    }

}
