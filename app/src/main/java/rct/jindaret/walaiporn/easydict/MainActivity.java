package rct.jindaret.walaiporn.easydict;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

    }

    public void clickSearch(View view) {

        searchString = editText.getText().toString().trim();

        if (searchString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert(MainActivity.this, "มีช่องว่าง", "กรุณากรอกทุกช่อง");
            myAlert.myDialog();
        } else {
            // No Space

           // oldStateMent();

            newStateMent();





        }   // if

    }   // clickSearch

    private void newStateMent() {

        try {

            GetWordWhere getWordWhere = new GetWordWhere(MainActivity.this);
            getWordWhere.execute(searchString);
            String strJSoN = getWordWhere.get();
            Log.d("7febV1", "JSON result ==> " + strJSoN);
            Log.d("7febV1", "JSON result length ==> " + strJSoN.length());

            if (strJSoN.length() != 4) {

                JSONArray jsonArray = new JSONArray(strJSoN);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                String strResult = jsonObject.getString("Detail");
                Log.d("7febV1", "strResult ==> " + strResult);
                MyAlert myAlert = new MyAlert(MainActivity.this, searchString, "หมายถึง " + strResult);
                myAlert.myDialog();



            } else {

                MyAlert myAlert = new MyAlert(MainActivity.this, "ความหมาย", "ไม่พบคำที่คุณค้นหา");
                myAlert.myDialog();

            }

        } catch (Exception e) {
            Log.d("7febV1", "e newState ==> " + e.toString());
        }


    }   // newStateMent

    private void oldStateMent() {

        try {

            boolean b = true;
            String strResult = null;
            SynWord synWord = new SynWord(MainActivity.this);
            synWord.execute();
            String s = synWord.get();

            Log.d("5decV1", "JSON ==> " + s);


            JSONArray jsonArray = new JSONArray(s);

            Log.d("5decV2", "JSONlength ==> " + jsonArray.length());

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (searchString.equals(jsonObject.getString("Word"))) {
                    b = false;
                    strResult = jsonObject.getString("Detail");
                }

            }   // for

            if (b) {
                MyAlert myAlert = new MyAlert(MainActivity.this, "ความหมาย", "ไม่พบคำที่คุณค้นหา");
                myAlert.myDialog();
            } else {
                MyAlert myAlert = new MyAlert(MainActivity.this, searchString, "หมายถึง " + strResult);
                myAlert.myDialog();
            }

            synWord.cancel(true);



        } catch (Exception e) {
            Log.d("5devV1", "e search ==>" + e.toString());
        }


    }

}   // Main Class
