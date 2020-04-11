package com.cs5520.quickerorder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "Main";

    // TODO: welcome screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gotoMainService(View view) {

        Intent i = new Intent(this, MainService.class);
        startActivity(i);
    }

    static HashMap<Integer, Dishes> menu;

    static {
        menu = new HashMap<>();
        menu.put(1, new Dishes(1, "Shredded Potato", R.drawable.shredded_potato, 7.99));
        menu.put(2, new Dishes(2, "Shanghai Smoked Fish", R.drawable.shanghai_smoked_fish, 8.99));
        menu.put(3, new Dishes(3, "Shanghai Style Appetizer", R.drawable.shanghai_style_appetizer, 7.99));
        menu.put(4, new Dishes(4, "Sweet&Sour Short Ribs", R.drawable.sweet_and_sour_short_ribs, 10.99));
        menu.put(5, new Dishes(5, "Crispy Chicken with Bone", R.drawable.crispy_chichen_with_bone, 14.99));
        menu.put(6, new Dishes(6, "Fried Pork in Scoop", R.drawable.fried_pork_in_scoop, 17.99));
        menu.put(7, new Dishes(7, "Pork Belly with Sauerkraut", R.drawable.pork_belly_with_sauerkraut, 15.99));
        menu.put(8, new Dishes(8, "Potato and Bean with Rib", R.drawable.potato_and_bean_with_pork_rib, 14.99));
        menu.put(9, new Dishes(9, "Rib with Vermincelli", R.drawable.rib_with_vermincelli, 15.99));
        menu.put(10, new Dishes(10, "Stewed Chicken with Mushroom", R.drawable.stewed_chicken_with_mushroom, 16.99));
        menu.put(11, new Dishes(11, "Corn with Salted Egg Yolk", R.drawable.sweet_corn_with_salted_egg_yolk, 12.99));
        menu.put(12, new Dishes(12, "Sweet Riceball with Osmanthus", R.drawable.sweet_fermented_riceball_with_osmanthus, 13.99));
        menu.put(13, new Dishes(13, "Caramelized Sweet Potato", R.drawable.caramelized_sweet_potato, 14.99));
        menu.put(14, new Dishes(14, "Braised Pork Meatball", R.drawable.braised_pork_meatball, 14.99));
        menu.put(15,  new Dishes(15, "Yangcheng Lake Crab", R.drawable.yangchenghu_crab, 30.99));
        menu.put(12, new Dishes(12, "Sweet Riceball with Osmanthus", R.drawable.sweet_fermented_riceball_with_osmanthus, 13.99));
        menu.put(13, new Dishes(13, "Caramelized Sweet Potato", R.drawable.caramelized_sweet_potato, 14.99));
        menu.put(14, new Dishes(14, "Braised Pork Meatball", R.drawable.braised_pork_meatball, 14.99));
        menu.put(15,  new Dishes(15, "Yangcheng Lake Crab", R.drawable.yangchenghu_crab, 30.99));
        //menu.put(11, new Dishes(11, "Corn with Salted Egg Yolk", R.drawable.sweet_corn_with_salted_egg_yolk, 12.99));
        //menu.put(12, new Dishes(12, "Sweet Riceball with Osmanthus", R.drawable.sweet_fermented_riceball_with_osmanthus, 13.99));
        //menu.put(13, new Dishes(13, "Caramelized Sweet Potato", R.drawable.caramelized_sweet_potato, 14.99));
        //menu.put(14, new Dishes(14, "Braised Pork Meatball", R.drawable.braised_pork_meatball, 14.99));
        //menu.put(15,  new Dishes(15, "Yangcheng Lake Crab", R.drawable.yangchenghu_crab, 30.99));
    }


}