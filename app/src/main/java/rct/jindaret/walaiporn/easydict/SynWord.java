package rct.jindaret.walaiporn.easydict;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 12/5/2016 AD.
 */

public class SynWord extends AsyncTask<Void, Void, String>{

    private Context context;
    private static final String urlJSON = "http://swiftcodingthai.com/jan/get_word.php";

    public SynWord(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlJSON).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("5decV1", "e ==> " + e.toString());
            return null;
        }


    }
}   // Main Class
