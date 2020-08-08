package acrux.com.chinesefoodrecipes.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

import acrux.com.chinesefoodrecipes.R;
import acrux.com.chinesefoodrecipes.activities.ReadRecipes;
import acrux.com.chinesefoodrecipes.items.Food;

/**
 * Created by Kelvin_Nguyen on 7/4/2016.
 */
public class AdapterList extends RecyclerView.Adapter<AdapterList.MyHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private int typeStories;
    private List<Food> arr;
    public AdapterList(List<Food> arr, Context mContext) {
        this.mContext = mContext;
        this.arr= arr;
    }



    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_food, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        final Food food = arr.get(position);
        holder.nameFood.setText(food.getName());
        holder.calo.setText(food.getNutrition()+" Calo");
        final byte[] img = food.getImage();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(img);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        holder.image.setImageBitmap(bitmap);
        holder.items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent read = new Intent(mContext, ReadRecipes.class);
                read.putExtra(Food.KEY_NAME, food.getName());
                read.putExtra(Food.KEY_INGREDIENTS, food.getIngredients());
                read.putExtra(Food.KEY_SAUCE , food.getSauce());
                read.putExtra(Food.KEY_METHOD , food.getMethod());
                read.putExtra(Food.KEY_IMAGE , img);
                mContext.startActivity(read);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nameFood , calo;
        LinearLayout items;
        public MyHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.img);
            nameFood = (TextView)itemView.findViewById(R.id.nameFood);
            calo = (TextView)itemView.findViewById(R.id.calo);
            items =(LinearLayout)itemView.findViewById(R.id.item);
        }
    }
}


