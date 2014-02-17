package com.example.MyLibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    private ArrayList<String> scannedIsbns;
    static final String SCANNED_ISBNS = "scannedIsbns";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannedIsbns = new ArrayList<String>();
        setContentView(R.layout.main);
        findViewById(R.id.btnScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            {
                callBarcodeScanner();
            }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putStringArrayList(SCANNED_ISBNS, scannedIsbns);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        scannedIsbns = savedInstanceState.getStringArrayList(SCANNED_ISBNS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        IntentResult scanResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, intent);
        if (scanResult != null) {
            // Scan next barcode if the user does not cancel the previous
            // scanning action
            if (scanResult.getContents() != null) {
                scannedIsbns.add(scanResult.getContents());
                TextView txtScanResult = (TextView) findViewById(
                        R.id.txtScanResult);
                txtScanResult.setText(scanResult.toString());

                callBarcodeScanner();
            } else {
                if (!scannedIsbns.isEmpty()) {
                    findViewById(R.id.btnAdd).setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView txtScanResult = (TextView) findViewById(R.id.txtScanResult);
        txtScanResult.setText(scannedIsbns.toString());
    }

    private void callBarcodeScanner() {
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.addExtra("PROMPT_MESSAGE", "Scan the ISBN barcode, or press Back button to stop scanning");
        integrator.addExtra("RESULT_DISPLAY_DURATION_MS", "1L");
        integrator.initiateScan(Collections.singleton("EAN_13"));
    }
}
