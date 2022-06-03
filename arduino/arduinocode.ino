#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "airpollutionmonit.firebaseio.com"
#define FIREBASE_AUTH "WXLYTB0vCrsE04ZqJhnFsAoh32IUCKJcQHVZMNmM"
#define WIFI_SSID "krishna"
#define WIFI_PASSWORD "krishnakrish"
void setup() {
  Serial.begin(9600);

  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

int n = 0;
void loop() {
  // set value
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(500);
  int n=analogRead(A0);
  // update value
  Firebase.setFloat("Airpollution", n);
  Firebase.pushFloat("Airpollutionlog", n);
  // handle error
  if (Firebase.failed()) {
      Serial.print("sending number failed");
      Serial.println(Firebase.error());  
      return;
  }
  delay(500);
  
  // get value 
  Serial.print("POllution level ");
  Serial.println(Firebase.getFloat("Airpollution"));
  Serial.println("PPM");
  delay(500);
}