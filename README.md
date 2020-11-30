# Homebuttonhandler
This project will demonstrate how to catch home button.

## Table of Contents

0. [Backgroud of this project](#Backgroud-of-this-project)
1. [API](#API)
2. [How to](#How-to)

Backgroud of this project
--------
Hi everyone,

I suppose if you found this project you need to catch home button.

Many and many friends and colleagues asked me:
>How we can catch home button and don't show home screen?

My answer was always the same: 
>You should not do like that due android security policy but if you need it's very easy.

Let's start.

API
--------
What need to know it's CAR_MODE. It was added in API level 8. 

You can read some description by this [link!](https://developer.android.com/reference/android/app/UiModeManager#ENABLE_CAR_MODE_GO_CAR_HOME)

You need to enable CAR_MODE.

When CAR_MODE is enabled you will receive intent with category "android.intent.category.CAR_DOCK"

```xml
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.CAR_DOCK" />
            </intent-filter>
```

instead of "android.intent.category.HOME"
```xml

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.HOME" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
```

How to
--------

* Add to Android manifest intent filter for activity which will response of handle it.

```xml
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.CAR_MODE" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.CAR_DOCK" />
            </intent-filter>
```
* In code you need to enable CAR_MODE:

```kotlin
mUiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
mUiModeManager.enableCarMode(UiModeManager.ENABLE_CAR_MODE_GO_CAR_HOME)
```

* Also don't forget to disable CAR_MODE:
```kotlin
uiModeManager.disableCarMode(UiModeManager.DISABLE_CAR_MODE_GO_HOME);
```
>That's all.
