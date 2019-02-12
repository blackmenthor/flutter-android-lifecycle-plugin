# flutter_android_lifecycle

A new Flutter plugin to enable flutter developer get android activity lifecycle notifications from dart.

## Getting Started

currently, this package supports three lifecycle notifications:

    - OnStart
    - OnResume
    - OnPause

To use this package, all you have to do is

- add flutter_android_lifecycle to your pubspec.yaml
- change your MainActivity.java or MainActivity.kt to extend FlutterAndroidLifecycleActivity
- Then you can start listening on notification lifecycle with these codes inside initState of your flutter application

for OnStart
```
    FlutterAndroidLifecycle.listenToOnStartStream().listen((_) {
          print("ONSTART CALLED");
        });
```

for OnResume
```
    FlutterAndroidLifecycle.listenToOnResumeStream().listen((_) {
          print("ONRESUME CALLED");
        });
```

for OnPause
```
    FlutterAndroidLifecycle.listenToOnPauseStream().listen((_) {
          print("ONPAUSE CALLED");
        });
```

# Version
- 0.0.1
Initial Upload
- 0.0.1+2
Add swift version on podspec

# Contributor
- Angga Dwi Arifandi (angga.dwi@oval.id)