Android-ProgressSwitcher
========================

The library is used to switch between content, progress, empty or error views. The progress is displayed during initial data loading. If data is empty then empty view is displayed. The same goes for error view. The work was based on [Android-ProgressFragment](https://github.com/johnkil/Android-ProgressFragment) project, but with a more declarative setup and state persistence.

Compatibility
-------------

This library is compatible from API 4 (Android 1.6).

Usage
-----

The library consist of three main classes: ProgressWidget, ProgressSwitcher and ProgressFragment. All classes implement Switcher interface. The most important methods are:

For changing state:

```java
public void showContent()
```
```java
public void showProgress()
```
```java
public void showEmpty()
```
```java
public void showError()
```

For configure switcher:

```java
public void setEmptyText(int resId)
```
```java
public void setErrorText(int resId)
```
```java
public void setOnEmptyViewClickListener(OnClickListener onClickListener, int viewId)
```
```java
public void setOnErrorViewClickListener(OnClickListener onClickListener, int viewId)
```

* ProgressWidget

Declare widget in layout:
``` xml
<ru.vang.progressswitcher.ProgressWidget xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/progress_widget"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <include layout="@layout/view_content" />

</ru.vang.progressswitcher.ProgressWidget>
``` 

... and that's it. Now you can get widget in your fragment and do what yout want.

* ProgressSwitcher

One way of using ProgressSwitcher is setup it with content view.
Inflate your content view:

``` java
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.view_content, container, false);

        return mContentView;
    }
``` 

Setup switcher with fromContentView method:

``` java
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProgressSwitcher = ProgressSwitcher
                .fromContentView(getActivity(), mContentView);
    }
``` 

The second way is provide complete layout:

``` xml
<FrameLayout
      android:layout_width="match_parent"
	  android:layout_height="match_parent"
      android:id="@id/content_container">
     
      <include
      android:id="@id/progress_view"
      layout="@layout/progress_view" />
     
      <include
      android:id="@id/empty_view"
      layout="@layout/empty_view" />
     
      <include
      android:id="@id/error_view"
      layout="@layout/error_view" />
     
</FrameLayout>
``` 

And setup switcher with fromRootView method and add content view:

``` java
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProgressSwitcher = ProgressSwitcher
                .fromRootView(getActivity(), mContentView);
        mProgressSwitcher.addContentView(R.layout.view_content)
    }
``` 

* ProgressFragment

Extend ProgressFragment:

``` java
public class CustomProgressFragment extends ProgressFragment {
}
``` 

Setup content view:

``` java
@Override
public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
 
    setContentView(R.layout.view_content);
}
``` 

Developed By
------------
* Dmitry Zaitsev - <prehistoric2003@gmail.com>


License
-------

    Copyright 2014 Dmitry Zaitsev
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
