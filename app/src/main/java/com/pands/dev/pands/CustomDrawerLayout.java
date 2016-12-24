package com.pands.dev.pands;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class CustomDrawerLayout extends DrawerLayout {

    String EXTRA_FILTER = "EXTRA_FILTER";
    Drawer result;

    Typeface RalewayRegular = Typeface.createFromAsset(getContext().getAssets(), "Raleway-Regular.otf");
    Typeface PlayFairDisplayItalic = Typeface.createFromAsset(getContext().getAssets(), "PlayfairDisplay-Regular.otf");


    public PrimaryDrawerItem item5 = new PrimaryDrawerItem()
            .withIdentifier(14)
            .withName("Last Chance to Buy")
            .withIcon(R.drawable.pands_logo_white)
            .withTypeface(PlayFairDisplayItalic)
            .withTextColor(Color.parseColor("#FF595f"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));


    public PrimaryDrawerItem item1 = new PrimaryDrawerItem()
            .withIdentifier(1)
            .withIcon(R.drawable.pands_logo_white);

    public PrimaryDrawerItem item2 = new PrimaryDrawerItem()
            .withIdentifier(2)
            .withName("ACCESSORIES")
            .withTypeface(PlayFairDisplayItalic)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_1 = new SecondaryDrawerItem()
            .withIdentifier(3)
            .withName("Handbags")
            .withIcon(R.drawable.handbag_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedColor(Color.parseColor("#1a1a1a"))
            .withSelectedTextColor(Color.parseColor("#FF595f"));

    public SecondaryDrawerItem item2_2 = new SecondaryDrawerItem()
            .withIdentifier(4)
            .withName("Wallets")
            .withIcon(R.drawable.wallet_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_3 = new SecondaryDrawerItem()
            .withIdentifier(5)
            .withName("Hats")
            .withIcon(R.drawable.hats_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_4 = new SecondaryDrawerItem()
            .withIdentifier(6)
            .withName("Scarves")
            .withIcon(R.drawable.scarves_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_5 = new SecondaryDrawerItem()
            .withIdentifier(7)
            .withName("Sunglasses")
            .withIcon(R.drawable.sunglasses_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public PrimaryDrawerItem item3 = new PrimaryDrawerItem()
            .withIdentifier(8)
            .withName("CLOTHES")
            .withTypeface(PlayFairDisplayItalic)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item3_1 = new SecondaryDrawerItem()
            .withIdentifier(9)
            .withName("Outerwear")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public PrimaryDrawerItem item4 = new PrimaryDrawerItem()
            .withIdentifier(10)
            .withName("JEWELLRY")
            .withTypeface(PlayFairDisplayItalic)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item4_1 = new SecondaryDrawerItem()
            .withIdentifier(11)
            .withName("Bracelet")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item4_2 = new SecondaryDrawerItem()
            .withIdentifier(12)
            .withName("Earrings")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item4_3 = new SecondaryDrawerItem()
            .withIdentifier(13)
            .withName("Necklace")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#777777"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public CustomDrawerLayout(final Context context) {

        super(context);

//        result = new DrawerBuilder().withActivity(this)
//                .withDrawerLayout(R.layout.material_drawer)
//                .addDrawerItems(
//                        item1,
//                        new DividerDrawerItem(),
//                        item2, item2_1, item2_2, item2_3, item2_4, item2_5,
//                        new DividerDrawerItem(),
//                        item3, item3_1,
//                        new DividerDrawerItem(),
//                        item4, item4_1, item4_2, item4_3
//
//                )
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
////                        Toast.makeText(getApplicationContext(), "position" + ((String.valueOf(position))), Toast.LENGTH_SHORT).show();
//
//                        switch(position) {
//
//                            case 2:
//                                Intent accessories = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Accessories", Toast.LENGTH_SHORT).show();
//                                accessories.putExtra(EXTRA_FILTER, "filter[category]=accessories");
//                                context.startActivity(accessories);
//                                break;
//
//                            case 3:
//                                Intent handbags = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Handbags", Toast.LENGTH_SHORT).show();
//                                handbags.putExtra(EXTRA_FILTER, "filter[category]=handbags");
//                                context.startActivity(handbags);
//                                break;
//
//                            case 4:
//                                Intent wallets = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                wallets.putExtra(EXTRA_FILTER, "filter[category]=wallets");
//                                context.startActivity(wallets);
//                                break;
//
//                            case 5:
//                                Intent hats = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Hats", Toast.LENGTH_SHORT).show();
//                                hats.putExtra(EXTRA_FILTER, "filter[category]=hats");
//                                context.startActivity(hats);
//                                break;
//
//                            case 6:
//                                Intent scarves = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Scarves", Toast.LENGTH_SHORT).show();
//                                scarves.putExtra(EXTRA_FILTER, "filter[category]=scarves");
//                                context.startActivity(scarves);
//                                break;
//
//                            case 7:
//                                Intent sunglasses = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Sunglasses", Toast.LENGTH_SHORT).show();
//                                sunglasses.putExtra(EXTRA_FILTER, "filter[category]=sunglasses");
//                                context.startActivity(sunglasses);
//                                break;
//
//                            case 8:
//                                Intent clothes = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Clothes", Toast.LENGTH_SHORT).show();
//                                clothes.putExtra(EXTRA_FILTER, "filter[category]=clothes");
//                                context.startActivity(clothes);
//                                break;
//
//                            case 9:
//                                Intent outerwear = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Outerwear", Toast.LENGTH_SHORT).show();
//                                outerwear.putExtra(EXTRA_FILTER, "filter[category]=outerwear");
//                                context.startActivity(outerwear);
//                                break;
//
//                            case 10:
//                                Intent jewellry = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Jewellry", Toast.LENGTH_SHORT).show();
//                                jewellry.putExtra(EXTRA_FILTER, "filter[category]=jewellry");
//                                context.startActivity(jewellry);
//                                break;
//
//                            case 11:
//                                Intent bracelet = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Bracelets", Toast.LENGTH_SHORT).show();
//                                bracelet.putExtra(EXTRA_FILTER, "filter[category]=bracelet");
//                                context.startActivity(bracelet);
//                                break;
//
//                            case 12:
//                                Intent earrings = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Earrings", Toast.LENGTH_SHORT).show();
//                                earrings.putExtra(EXTRA_FILTER, "filter[category]=earrings");
//                                context.startActivity(earrings);
//                                break;
//
//                            case 13:
//                                Intent necklace = new Intent(context, MainActivity.class);
//                                Log.d("position", ((String.valueOf(position))));
//                                Toast.makeText(context, "Loading Necklace", Toast.LENGTH_SHORT).show();
//                                necklace.putExtra(EXTRA_FILTER, "filter[category]=earrings");
//                                context.startActivity(necklace);
//                                break;
//                        }
//
//                        return false;
//
//                    }
//
//                })
//
//
//                .build();
//
//        result.closeDrawer();

    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



}
