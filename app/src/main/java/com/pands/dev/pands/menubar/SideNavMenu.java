package com.pands.dev.pands.menubar;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;

import com.mikepenz.materialdrawer.Drawer;
import com.pands.dev.pands.LoginActivity;
import com.pands.dev.pands.MainActivity;
import com.pands.dev.pands.ProductViewer;
import com.pands.dev.pands.sideMenu.SideDrawerFragment;
import cimi.com.easeinterpolator.EaseExponentialOutInterpolator;

public class SideNavMenu {

    public static int closeMenu = (SideDrawerFragment.rlSideMenuContainer.getMeasuredWidth() - SideDrawerFragment.rlSideMenuContainer.getMeasuredWidth() - SideDrawerFragment.rlSideMenuContainer.getMeasuredWidth());
    public static int closeMenu2 = SideDrawerFragment.rlSideMenuContainer.getMeasuredWidth() - SideDrawerFragment.rlSideMenuContainer.getMeasuredWidth();
    public static int openMenu = SideDrawerFragment.rlSideMenuContainer.getMeasuredWidth();

    public SideNavMenu(Context context) {

        Log.d("Header_menu", "SideNavMenu");

        if (MenuFunctions.flMenuBarContainer.getX() != 0) {

            sideMenuClose();

        } else {

           sideMenuOpen();

        }

    }


    /**
     *
     */
    public static void sideMenuClose() {

        Log.d("SideMenu", "Close");

        SideDrawerFragment.rlSideMenuContainer.setX(0);
        animateMenu(SideDrawerFragment.rlSideMenuContainer, 0, closeMenu);

        if (MainActivity.rvProducts != null) {
            MainActivity.rvProducts.setX(0);
            animateMenu(MainActivity.rvProducts, openMenu, 0);
        }

        if (ProductViewer.svProductContainer != null) {
            ProductViewer.svProductContainer.setX(0);
            animateMenu(ProductViewer.svProductContainer, openMenu, 0);
        }

        MenuFunctions.flMenuBarContainer.setX(0);
        animateMenu(MenuFunctions.flMenuBarContainer, openMenu, 0);

    }



    /**
     *
     */
    public static void sideMenuOpen() {

        Log.d("SideMenu", "Open");

        SideDrawerFragment.rlSideMenuContainer.setX(closeMenu2);

        animateMenu(SideDrawerFragment.rlSideMenuContainer, closeMenu, 0);
        if (MainActivity.rvProducts != null) {
            MainActivity.rvProducts.setX(0);
            animateMenu(MainActivity.rvProducts, 0, openMenu);
        }
        if (ProductViewer.svProductContainer != null) {
            ProductViewer.svProductContainer.setX(0);
            animateMenu(ProductViewer.svProductContainer, 0, openMenu);
        }
        MenuFunctions.flMenuBarContainer.setX(0);
        animateMenu(MenuFunctions.flMenuBarContainer, 0, openMenu);
    }



    /**
     *
     * @param view
     * @param startPos
     * @param startPos
     */
    public static void animateMenu (final View view, final int startPos, final int endPos) {

        Interpolator interpolator = new EaseExponentialOutInterpolator();
        Animation animation = new TranslateAnimation(startPos, endPos, 0, 0);
        animation.setDuration(500);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        animation.setInterpolator(interpolator);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animation.setFillEnabled(false);
                animation.setFillAfter(false);
                view.setX(endPos);
                view.clearAnimation();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animation);
    }
}