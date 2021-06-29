
// Button is attached between digital pin 2 and the GND
int pushButton = 2;
int prevButtonState;
// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  // make the pushbutton's pin an input:
  pinMode(pushButton, INPUT_PULLUP);
}

// the loop routine runs over and over again forever:
void loop() {
  // read the input pin:
  int buttonState = digitalRead(pushButton);
  // print out the state of the button:
  if (buttonState != prevButtonState){
    Serial.print(buttonState);
    prevButtonState = buttonState;
  }
  delay(100);        // delay in between reads for stability
}
