package com.zaid.completebulid;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class DownloadTask extends AsyncTask<String,Integer,Void> {

    public static  String TAG = DownloadTask.class.getSimpleName();

    ProgressBar mProgressBar;

    public DownloadTask(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * officeboy
     * this doinbackground method works in the background thread
     * @param
     * @return
     */
    @Override
    protected Void doInBackground(String... downloadUrl) {
        Log.i(TAG,downloadUrl[0]);
        for(int i=5; i<105;  i+=5){
            try {
                Thread.sleep(500);
                publishProgress(i); //1.office boy is writing on the whiteboard for me to consume
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override //2.
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
