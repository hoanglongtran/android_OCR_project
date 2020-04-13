package com.capstoneproject;

import com.facebook.react.ReactActivity;
import com.rnfs.RNFSPackage;  //react-native-fs

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "CapstoneProject";
  }

  //<tfjs-implementation>
  //react-native-fs
  @Override
  protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
      new MainReactPackage(),  
      new RNFSPackage()
    );
  }
  
  //</tfjs-implementation>
}
