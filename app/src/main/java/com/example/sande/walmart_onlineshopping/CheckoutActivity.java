package com.example.sande.walmart_onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sande.walmart_onlineshopping.data.OrderData;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class CheckoutActivity extends AppCompatActivity {


    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;

    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";

    private static final int REQUEST_CODE_PAYMENT = 1;

    Button button_payPal;
    private static final String TAG = "MainActivity";

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btn = findViewById(R.id.btn_payPal);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);

                /*
                 * See getStuffToBuy(..) for examples of some available payment options.
                 */

                Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);

                // send the same configuration for restart resiliency
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

                startActivityForResult(intent, REQUEST_CODE_PAYMENT);
            }
        });

    }

    private PayPalPayment getThingToBuy(String paymentIntentSale) {
        return new PayPalPayment(new BigDecimal("1.00"), "USD", "tshirt", paymentIntentSale);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4));
                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));
                        /**
                         *  TODO: send 'confirm' (and possibly confirm.getPayment() to your server for verification
                         * or consent completion.
                         * See https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                         * for more details.
                         *
                         * For sample mobile backend interactions, see
                         * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
                         */
                        displayResultText("PaymentConfirmation info received from PayPal");


                    } catch (JSONException e) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        TAG,
                        "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }

        String userId = getIntent().getStringExtra("userId");
        String fName = getIntent().getStringExtra("fName");
        String email = getIntent().getStringExtra("email");
        String mobile = getIntent().getStringExtra("mobile");
        String apiKey = getIntent().getStringExtra("apiKey");
        Log.i("cartFrag", "User Id in checkout Activity " + userId + "  " + apiKey + " " + email + " " + mobile);
        Intent i = new Intent(CheckoutActivity.this,OrderSummary.class);
        i.putExtra("userId",userId);
        i.putExtra("fName",fName);
        i.putExtra("email",email);
        i.putExtra("mobile",mobile);
        i.putExtra("apiKey",apiKey);
        Log.i("cartFrag", "User Id in checkout Activity " + userId + "  " + apiKey + " " + email + "  " + mobile);
        startActivity(i);
    }


    private void displayResultText (String paymentConfirmation_info_received_from_payPal){

        Toast.makeText(this, "" + paymentConfirmation_info_received_from_payPal, Toast.LENGTH_SHORT).show();
    }




    @Override
    protected void onDestroy() {
        // Stop service when done

        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
