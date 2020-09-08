package com.praksa.ucenik.jatolog.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.praksa.ucenik.jatolog.R;
import com.praksa.ucenik.jatolog.thread.SinhronizacijaThread;

public class ProgressDialogAsycTask extends AsyncTask<Void, Void, Void> {
    private ProgressDialog dialog;

    public ProgressDialogAsycTask(Activity activity) {
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage(dialog.getContext().getResources().getString(R.string.SinhrinizacijaUToku));
        dialog.show();
    }

    protected Void doInBackground(Void... args) {
        SinhronizacijaThread st = new SinhronizacijaThread(dialog.getContext());
        st.setForce(true);
        st.start();
        try {
            st.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
