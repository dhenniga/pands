package com.pands.dev.pands;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

/**
 * Created by davidhennigan on 19/12/2016.
 */

public class CustomDrawerLayout extends DrawerLayout {
    public CustomDrawerLayout(Context context) {
        super(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    //if you want to update the items at a later time it is recommended to keep it in a variable
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withIcon(R.drawable.pands_logo);

    PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("ACCESSORIES");
    SecondaryDrawerItem item2_1 = new SecondaryDrawerItem().withIdentifier(3).withName("Handbags").withIcon(R.drawable.cart_icon);
    SecondaryDrawerItem item2_2 = new SecondaryDrawerItem().withIdentifier(4).withName("Wallets").withIcon(R.drawable.cart_icon);
    SecondaryDrawerItem item2_3 = new SecondaryDrawerItem().withIdentifier(5).withName("Hats").withIcon(R.drawable.cart_icon);
    SecondaryDrawerItem item2_4 = new SecondaryDrawerItem().withIdentifier(6).withName("Scarves").withIcon(R.drawable.cart_icon);
    SecondaryDrawerItem item2_5 = new SecondaryDrawerItem().withIdentifier(7).withName("Sunglasses").withIcon(R.drawable.cart_icon);

    PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(8).withName("CLOTHES");
    SecondaryDrawerItem item3_1 = new SecondaryDrawerItem().withIdentifier(9).withName("Outerwear").withIcon(R.drawable.cart_icon);

    PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(10).withName("JEWELLERY");
    SecondaryDrawerItem item4_1 = new SecondaryDrawerItem().withIdentifier(11).withName("Bracelet").withIcon(R.drawable.cart_icon);
    SecondaryDrawerItem item4_2 = new SecondaryDrawerItem().withIdentifier(12).withName("Earrings").withIcon(R.drawable.cart_icon);
    SecondaryDrawerItem item4_3 = new SecondaryDrawerItem().withIdentifier(13).withName("Necklace").withIcon(R.drawable.cart_icon);



//        Drawer result = new DrawerBuilder()
//                .withActivity(this)
//                .withDrawerLayout(R.layout.material_drawer)
//
//
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
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        return true;
//                        // do something with the clicked item :D
//                    }
//                })
//                .build();

}