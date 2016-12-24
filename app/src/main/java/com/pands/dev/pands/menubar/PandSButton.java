package com.pands.dev.pands.menubar;

        import android.content.Context;
        import android.content.Intent;
        import android.util.Log;
        import android.widget.Toast;

        import com.pands.dev.pands.MainActivity;
        import com.pands.dev.pands.ProductViewer;

public class PandSButton {

    public static String EXTRA_FILTER = "EXTRA_FILTER";

    public PandSButton(Context context) {

        Log.d("Header_menu", "PandSButton");

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_FILTER, "");
        context.startActivity(intent);

    }

}