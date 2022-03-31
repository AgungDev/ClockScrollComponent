# ClockScrollComponent
Number Piker Custom

<img src="https://i.ibb.co/Wkf5XjH/ezgif-com-gif-maker-2.gif" alt="drawing" width="400"/>

## Step 1. Add the repository & dependency
Add it in your root build.gradle at the end of repositories:
```java
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

and dapedency
```java
dependencies {
        implementation 'com.github.AgungDev:ClockScrollComponent:1.0'
}
```

### Step 2. Add XML Layout and MainActivity

- activity_layout.xml

```xml
<fun5i.module.clockscroll.NumberPickerScroll
        app:text_size="6dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:text="2"
        android:id="@+id/np"/>
```

- MainActivity.java

```java
NumberPickerScroll np = findViewById(R.id.np);

np.setweight(-10, 15); //min, max
np.setSP(0); //start position

np.Action(new NumberPickerScroll.onClickBtnFunction() {
    @Override
    public void theAction(String mData, boolean btn) {
        Toast.makeText(getApplicationContext(), (btn)?"{up} "+mData:"{down} "+mData, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "theAction: "+np.getPosition());
    }
});
```
