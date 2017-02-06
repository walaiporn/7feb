package rct.jindaret.walaiporn.easydict;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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




        }   // if

    }   // clickSearch

}   // Main Class
