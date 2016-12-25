package com.pands.dev.pands;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class CustomDrawerLayout extends DrawerLayout implements Drawer.OnDrawerItemClickListener {

    public final static int LAST_CHANCE_TO_BUY = 1;
    public final static int ACCESSORIES = 2;
    public final static int HANDBAGS = 3;
    public final static int WALLETS = 4;
    public final static int HATS = 5;
    public final static int SCARVES = 6;
    public final static int SUNGLASSES = 6;
    public final static int CLOTHES = 7;
    public final static int OUTERWEAR = 8;
    public final static int JEWELLRY = 9;
    public final static int BRACELET = 10;
    public final static int EARRINGS = 11;
    public final static int NECKLACE = 12;

    String EXTRA_FILTER = "EXTRA_FILTER";
    public Drawer mDrawer;

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
            .withIdentifier(ACCESSORIES)
            .withName("ACCESSORIES")
            .withTypeface(PlayFairDisplayItalic)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_1 = new SecondaryDrawerItem()
            .withIdentifier(HANDBAGS)
            .withName("Handbags")
            .withIcon(R.drawable.handbag_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedColor(Color.parseColor("#1a1a1a"))
            .withSelectedTextColor(Color.parseColor("#FF595f"));

    public SecondaryDrawerItem item2_2 = new SecondaryDrawerItem()
            .withIdentifier(WALLETS)
            .withName("Wallets")
            .withIcon(R.drawable.wallet_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_3 = new SecondaryDrawerItem()
            .withIdentifier(HATS)
            .withName("Hats")
            .withIcon(R.drawable.hats_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_4 = new SecondaryDrawerItem()
            .withIdentifier(SCARVES)
            .withName("Scarves")
            .withIcon(R.drawable.scarves_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item2_5 = new SecondaryDrawerItem()
            .withIdentifier(SUNGLASSES)
            .withName("Sunglasses")
            .withIcon(R.drawable.sunglasses_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public PrimaryDrawerItem item3 = new PrimaryDrawerItem()
            .withIdentifier(CLOTHES)
            .withName("CLOTHES")
            .withTypeface(PlayFairDisplayItalic)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item3_1 = new SecondaryDrawerItem()
            .withIdentifier(OUTERWEAR)
            .withName("Outerwear")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public PrimaryDrawerItem item4 = new PrimaryDrawerItem()
            .withIdentifier(JEWELLRY)
            .withName("JEWELLRY")
            .withTypeface(PlayFairDisplayItalic)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item4_1 = new SecondaryDrawerItem()
            .withIdentifier(BRACELET)
            .withName("Bracelet")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item4_2 = new SecondaryDrawerItem()
            .withIdentifier(EARRINGS)
            .withName("Earrings")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public SecondaryDrawerItem item4_3 = new SecondaryDrawerItem()
            .withIdentifier(NECKLACE)
            .withName("Necklace")
            .withIcon(R.drawable.cart_icon)
            .withTypeface(RalewayRegular)
            .withTextColor(Color.parseColor("#ffffff"))
            .withSelectedTextColor(Color.parseColor("#FF595f"))
            .withSelectedColor(Color.parseColor("#1a1a1a"));

    public CustomDrawerLayout(final Context context, Activity activity) {

        super(context);

        mDrawer = new DrawerBuilder().withActivity(activity)
                .withDrawerLayout(R.layout.material_drawer)
                .withFireOnInitialOnClick(true)
                .withSliderBackgroundColor(getResources().getColor(R.color.md_black_1000))
                .withOnDrawerItemClickListener(this)
                .addDrawerItems(
                        item5,
                        new DividerDrawerItem(),
                        item2, item2_1, item2_2, item2_3, item2_4, item2_5,
                        new DividerDrawerItem(),
                        item3, item3_1,
                        new DividerDrawerItem(),
                        item4, item4_1, item4_2, item4_3

                ).build();

        mDrawer.closeDrawer();

    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        switch(position) {

            case 2:
                Intent accessories = new Intent(getContext(), MainActivity.class);
                accessories.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Accessories", Toast.LENGTH_SHORT).show();
                accessories.putExtra(EXTRA_FILTER, "filter[category]=accessories");
                getContext().startActivity(accessories);
                break;

            case 3:
                Intent handbags = new Intent(getContext(), MainActivity.class);
                handbags.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Handbags", Toast.LENGTH_SHORT).show();
                handbags.putExtra(EXTRA_FILTER, "filter[category]=handbags");
                getContext().startActivity(handbags);
                break;

            case 4:
                Intent wallets = new Intent(getContext(), MainActivity.class);
                wallets.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
                wallets.putExtra(EXTRA_FILTER, "filter[category]=wallets");
                getContext().startActivity(wallets);
                break;

            case 5:
                Intent hats = new Intent(getContext(), MainActivity.class);
                hats.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Hats", Toast.LENGTH_SHORT).show();
                hats.putExtra(EXTRA_FILTER, "filter[category]=hats");
                getContext().startActivity(hats);
                break;

            case 6:
                Intent scarves = new Intent(getContext(), MainActivity.class);
                scarves.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Scarves", Toast.LENGTH_SHORT).show();
                scarves.putExtra(EXTRA_FILTER, "filter[category]=scarves");
                getContext().startActivity(scarves);
                break;

            case 7:
                Intent sunglasses = new Intent(getContext(), MainActivity.class);
                sunglasses.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Sunglasses", Toast.LENGTH_SHORT).show();
                sunglasses.putExtra(EXTRA_FILTER, "filter[category]=sunglasses");
                getContext().startActivity(sunglasses);
                break;

            case 8:
                Intent clothes = new Intent(getContext(), MainActivity.class);
                clothes.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Clothes", Toast.LENGTH_SHORT).show();
                clothes.putExtra(EXTRA_FILTER, "filter[category]=clothes");
                getContext().startActivity(clothes);
                break;

            case 9:
                Intent outerwear = new Intent(getContext(), MainActivity.class);
                outerwear.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Outerwear", Toast.LENGTH_SHORT).show();
                outerwear.putExtra(EXTRA_FILTER, "filter[category]=outerwear");
                getContext().startActivity(outerwear);
                break;

            case 10:
                Intent jewellry = new Intent(getContext(), MainActivity.class);
                jewellry.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Jewellry", Toast.LENGTH_SHORT).show();
                jewellry.putExtra(EXTRA_FILTER, "filter[category]=jewellry");
                getContext().startActivity(jewellry);
                break;

            case 11:
                Intent bracelet = new Intent(getContext(), MainActivity.class);
                bracelet.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Bracelets", Toast.LENGTH_SHORT).show();
                bracelet.putExtra(EXTRA_FILTER, "filter[category]=bracelet");
                getContext().startActivity(bracelet);
                break;

            case 12:
                Intent earrings = new Intent(getContext(), MainActivity.class);
                earrings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Earrings", Toast.LENGTH_SHORT).show();
                earrings.putExtra(EXTRA_FILTER, "filter[category]=earrings");
                getContext().startActivity(earrings);
                break;

            case 13:
                Intent necklace = new Intent(getContext(), MainActivity.class);
                necklace.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Necklace", Toast.LENGTH_SHORT).show();
                necklace.putExtra(EXTRA_FILTER, "filter[category]=earrings");
                getContext().startActivity(necklace);
                break;

            case 14:
                Intent last_chance_to_buy = new Intent(getContext(), MainActivity.class);
                last_chance_to_buy.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("position", ((String.valueOf(position))));
//                Toast.makeText(getContext(), "Loading Last Chance To Buy", Toast.LENGTH_SHORT).show();
                last_chance_to_buy.putExtra(EXTRA_FILTER, "filter[category]=last-chance-to-buy");
                getContext().startActivity(last_chance_to_buy);
                break;
        }

        return false;
    }
}
