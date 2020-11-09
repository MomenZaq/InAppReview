# InAppReview

The Google Play In-App Review API lets you prompt users to submit Play Store ratings and reviews without the inconvenience of leaving your app or game.

Generally, the in-app review flow (see figure 1) can be triggered at any time throughout the user journey of your app. During the flow, the user has the ability to rate your app using the 1 to 5 star system and to add an optional comment. Once submitted, the review is sent to the Play Store and eventually displayed.


![Image of flexible_flow](https://developer.android.com/images/google/play/in-app-review/iar-flow.jpg)


## How to use this class?
1- add this line to your gradle 

```java
implementation 'com.google.android.play:core:1.8.0'
```
2- copy the class to your project

3- call this method from your activity


for in-app review
```java
inAppReviewHelper.displayInAppReview()
```
if you have "rate app" button
```java
inAppReviewHelper.reviewAppOnGooglePlay()
```



## When will the dialog display?
  1- the app is already on google play

  2- and the  user account does not currently have a review for the app

  3- the in-app review quota has not already hit




## How to test it?
You can test it using [test in-app review](https://developer.android.com/guide/playcore/in-app-review/test).


For more details [Support in-app review](https://developer.android.com/guide/playcore/in-app-review).
