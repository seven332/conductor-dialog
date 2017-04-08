# conductor-dialog

[![Release](https://jitpack.io/v/seven332/conductor-dialog.svg)](https://jitpack.io/#seven332/conductor-dialog)

Conductor-Dialog provides a dialog-like Controller.

## Usage

```
repositories {
    maven { url 'https://jitpack.io' }
}

compile 'com.github.seven332:conductor-dialog:{version}'
```

### Implementation

```java
public class CustomDialog extends DialogController {
  @Override
  protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
    return inflater.inflate(R.layout.dialog_custom, container, false);
  }
}
```

### Theme

DialogController uses these attributes:
* `android.R.attr.windowMinWidthMajor`
* `android.R.attr.windowMinWidthMinor`
* `android.R.attr.windowBackground`
* `android.R.attr.windowElevation`
* `android.R.attr.backgroundDimAmount`

DialogController uses the theme `android.R.attr.dialogTheme` in default. It also supports custom theme.

## Note

`v7 appcompat library` is used to be compatible with `Theme.AppCompat.XXX`.

Please check [android-dialog](https://github.com/seven332/android-dialog) if you need something like `AlertDialog`.

## License

```
Copyright 2017 Hippo Seven

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
