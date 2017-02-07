package rct.jindaret.walaiporn.easydict;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 2/7/2017 AD.
 */

public class GetWordWhere extends AsyncTask<String, Void, String>{

    private Context context;
    private static final String urlPHP = "http://swiftcodingthai.com/jan/get_word_where.php";

    public GetWordWhere(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Word", strings[0])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("7febV1", "e doin ==> " + e.toString());
            return null;
        }


    }
}   // Main Class
