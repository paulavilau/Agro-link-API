package com.myproject.agrolink.config;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInitializer {
  public static void initialize() throws IOException {
    // FileInputStream serviceAccount = new FileInputStream(
    //     "./src/main/java/com/myproject/agrolink/config/serviceAccountKey.json");

    String firebaseJson = System.getenv("FIREBASE_CREDENTIALS");
    ByteArrayInputStream serviceAccount = new ByteArrayInputStream(firebaseJson.getBytes());

    // InputStream serviceAccount = FirebaseInitializer.class
    //     .getClassLoader()
    //     .getResourceAsStream("serviceAccountKey.json");

    FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();

    FirebaseApp.initializeApp(options);
  }
}