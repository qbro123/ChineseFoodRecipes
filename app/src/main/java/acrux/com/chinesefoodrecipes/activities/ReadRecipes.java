package acrux.com.chinesefoodrecipes.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import acrux.com.chinesefoodrecipes.R;
import acrux.com.chinesefoodrecipes.items.Food;

/**
 * Created by Kelvin_Nguyen on 7/12/2016.
 */

public class ReadRecipes extends AppCompatActivity {
    private TextView name , ingredients , sauce , method;
    private ImageView img;
    private RelativeLayout btnFavorite , btnShare , btnSetting;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.read_activity);
        initView();
    }

    private void initView() {
        name = (TextView)findViewById(R.id.name);
        ingredients= (TextView)findViewById(R.id.ingredients);
        sauce= (TextView)findViewById(R.id.sauce);
        method= (TextView)findViewById(R.id.method);
        img= (ImageView)findViewById(R.id.img);

        Intent reciver = getIntent();
        name.setText(reciver.getStringExtra(Food.KEY_NAME));
        ingredients.setText(reciver.getStringExtra(Food.KEY_INGREDIENTS));
        sauce.setText(reciver.getStringExtra(Food.KEY_SAUCE));
        method.setText(reciver.getStringExtra(Food.KEY_METHOD));
        byte[] image = reciver.getByteArrayExtra(Food.KEY_IMAGE);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        img.setImageBitmap(bitmap);

    }
}
