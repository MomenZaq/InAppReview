/* 

Google Play In-App Review API
The Google Play In-App Review API lets you prompt users to submit 
Play Store ratings and reviews without the inconvenience of leaving your app or game.
For more details: https://developer.android.com/guide/playcore/in-app-review

*/

|


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.Task;





public class InAppReviewHelper {
Activity activity;
    public InAppReviewHelper(Activity activity ) {
      this.activity=activity;
    }

    
    // call it to display in-app review, you should not have a call-to-action option (such as a button) to trigger the API,
// as a user might have already hit their quota and the flow won’t be shown. In this case call reviewAppOnGooglePlay method
    public void displayInAppReview() {
   
            ReviewManager manager = ReviewManagerFactory.create(activity);
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    ReviewInfo reviewInfo = task.getResult();

                    Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                    flow.addOnCompleteListener(task2 -> {
                        // The flow has finished. The API does not indicate whether the user
                        // reviewed or not, or even whether the review dialog was shown. Thus, no
                        // matter the result, we continue our app flow.
                    });

                } else {
                    // There was some problem, continue regardless of the result.
                }
            });

    
    }

// call this method to redirect user to the application's page on google play 
    public void reviewAppOnGooglePlay() {

        try {
            Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                myAppLinkToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(activity, " unable to find market app", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //e.toString();
        }


    }

}
