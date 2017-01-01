package com.pands.dev.pands.menubar;

        import android.content.Context;
        import android.content.Intent;
        import android.util.Log;
        import android.widget.Toast;

        import com.pands.dev.pands.MainActivity;
        import com.pands.dev.pands.ProductViewer;

public class PandSButton {

    public static String EXTRA_FILTER = "EXTRA_FILTER";
    public static String EXTRA_SECTION_NAME = "EXTRA_SECTION_NAME";

    public PandSButton(Context context) {

        Log.d("Header_menu", "PandSButton");

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_FILTER, "");
        intent.putExtra(EXTRA_SECTION_NAME, "Latest Products");
        context.startActivity(intent);

    }

}