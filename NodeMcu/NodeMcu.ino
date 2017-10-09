#include <ESP8266WiFi.h>
#include <ArduinoJson.h>
//#include <time.h>

#define wifi  D5
#define site  D6

#define ocak1 D1
#define ocak2 D2
#define ocak3 D3
#define ocak4 D4

const char* ssid     = "ITU-NET Misafir";
const char* password = "";

const char* host     = "espakilliev.site88.net"; // Your domain
String path          = "/tou_robotik2/test.json";
//String path          = "/tou_robotik/test.json";

//************************************************************************
int statu = 0;

int ocakAyar1 = 0;
int ocakAyar2 = 0;
int ocakAyar3 = 0;
int ocakAyar4 = 0;

bool wifiDurum = LOW;
bool siteDurum = LOW;
long saat;

long eskiZaman = 0;
long yeniZaman = 0;

long startTime = 0;
long finishTime = 0;

//************************************************************************

void setup() {

  Serial.begin(115200);

  pinMode(ocak1, OUTPUT);
  pinMode(ocak1, HIGH);
  pinMode(ocak2, OUTPUT);
  pinMode(ocak2, HIGH);
  pinMode(ocak3, OUTPUT);
  pinMode(ocak3, HIGH);
  pinMode(ocak4, OUTPUT);
  pinMode(ocak4, HIGH);
  pinMode(wifi, OUTPUT);
  pinMode(wifi, HIGH);
  pinMode(site, OUTPUT);
  pinMode(site, HIGH);

  //  if (Serial.find("OK")) {
//  Serial.println("AT+CWMODE=1");
//  delay(2000);

  String baglantiKomutu = String("AT+CWJAP=\"") +
                          ssid + "\",\"" +
                          password + "\"";
  WiFi.begin(ssid, password);  // wifi bağlanma şekli

//  Serial.println(baglantiKomutu);

//  Serial.print("Connecting to ");
//  Serial.println(ssid);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    wifiDurum = LOW;
//    Serial.print(".");
  } wifiDurum = HIGH;
  //  }
}
void loop() {
  /*
    bool wifiDurum = LOW;
    bool siteDurum = LOW;
  */

    yeniZaman = millis();

  if (WiFi.status() == WL_CONNECTED) {
    wifiDurum = HIGH;
  } else {
    wifiDurum = LOW;
  }
//    Serial.println(yeniZaman - eskiZaman);
  if (yeniZaman - eskiZaman > 1000 * 30) {
    sistemKapat();
  }

  digitalWrite(wifi, wifiDurum);
  digitalWrite(site, siteDurum);

//  Serial.println(host);

  WiFiClient client;

  const int httpPort = 80;
  if (!client.connect(host, httpPort)) {
//    yeniZaman = millis();
//    Serial.println("connection failed");
    siteDurum = LOW;
    return;
  } siteDurum = HIGH; 
  yeniZaman = millis();

  client.print(String("GET ") + path + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "Connection: keep-alive\r\n\r\n");
  delay(500);

  String section = "header";
//  Serial.println("\n bura \n");
  while (client.available()) {
    String line = client.readStringUntil('\r');
    Serial.print(line);
    // we’ll parse the HTML body here
    if (section == "header") { // headers..
//      Serial.print(".");
      if (line == "\n") { // skips the empty space at the beginning
        section = "json";
      }
    }
    else if (section == "json") { // print the good stuff
      section = "ignore";
      String result = line.substring(1);

      // Parse JSON
      int size = result.length() + 1;
      char json[size];
      result.toCharArray(json, size);
      StaticJsonBuffer<800> jsonBuffer;
      JsonObject& json_parsed = jsonBuffer.parseObject(json);

      if (!json_parsed.success())
      {
//        Serial.println("parseObject() failed");
        return;
      }
      /*
        int statu = 0;

        int saat = 0;
        int dakika = 0;

        int ocakAyar1 = 0;
        int ocakAyar2 = 0;
        int ocakAyar3 = 0;
        int ocakAyar4 = 0;

        String startTime = 0;
        String finishTime = 0;
      */

      statu = json_parsed["status"];

      saat = json_parsed["clock"];
//      saat = time(0);
      ocakAyar1 = json_parsed["ocak"][0];
      ocakAyar2 = json_parsed["ocak"][1];
      ocakAyar3 = json_parsed["ocak"][2];
      ocakAyar4 = json_parsed["ocak"][3];

      startTime = json_parsed["unix"]["start"];
      finishTime = json_parsed["unix"]["stop"];

    }
    //*******************************************************************
   /* Serial.println("\n\n\n---------------------------------------------------------------");
    Serial.print("Statu: ");
    Serial.println(statu);

    Serial.print("saat: ");
    Serial.print(saat);

    Serial.print("\nOcak-1: ");
    Serial.println(ocakAyar1);

    Serial.print("Ocak-2: ");
    Serial.println(ocakAyar2);

    Serial.print("Ocak-3: ");
    Serial.println(ocakAyar3);

    Serial.print("Ocak-4: ");
    Serial.println(ocakAyar4);

    Serial.print("Start: ");
    Serial.println(startTime);

    Serial.print("Finish: ");
    Serial.println(finishTime);
    Serial.println("---------------------------------------------------------------");
*/
//    delay(500);

    if (startTime < saat && saat < finishTime) {
      sistemCalistir();
    } else {
      sistemKapat();
    }

    //*******************************************************************
  }
//  Serial.print("closing connection. \n");
  eskiZaman = yeniZaman;
}

void sistemCalistir() {
  analogWrite(ocak1, ocakAyar(ocakAyar1));
  analogWrite(ocak2, ocakAyar(ocakAyar2));
  analogWrite(ocak3, ocakAyar(ocakAyar3));
  analogWrite(ocak4, ocakAyar(ocakAyar4));
}

void sistemKapat() {
  analogWrite(ocak1, 0);
  analogWrite(ocak2, 0);
  analogWrite(ocak3, 0);
  analogWrite(ocak4, 0);
}

int ocakAyar(int ocak) {
  switch (ocak) {
    case 0:
      return 0;
      break;
    case 1:
      return 30;
      break;
    case 2:
      return 60;
      break;
    case 3:
      return 90;
      break;
    case 4:
      return 120;
      break;
    case 5:
      return 150;
      break;
    case 6:
      return 170;
      break;
    case 7:
      return 190;
      break;
    case 8:
      return 210;
      break;
    case 9:
      return 250;
      break;
  }

}


