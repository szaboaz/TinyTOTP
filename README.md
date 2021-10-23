# TinyTOTP

TinyTOTP is a small Java application for generating TOTPs (Time-Based One-Time Password) on a PC.

<img width="341" alt="TinyTOTP_TOTP_tab" src="https://user-images.githubusercontent.com/23156196/138569217-5880c9b1-cf21-43af-ab95-c12800337963.png">

## Motivation

I wrote it for myself for the convenience of simply double clicking on the TinyTOTP jar file and have the generated 6 digit password code automatically copied to the clipboard, so that I can directly paste it in Cisco AnyConnect login's password field. It also works for the  2 factor authentication of the Microsoft Teams account as well as the Google account, as it generates the same password that Microsoft Authenticator or Google Authenticator does.
The main motivation behind this program was that those authenticators are exclusively for mobile (Android or iOS), so I can't run them on Windows, unless in a virtual environment, which works, but takes up too much space and most importantly time to startup every time I need to log in. Running authenticators on a mobile device seems like a hassle as well, to keep it at hand, to start the app, and manually copy the digits over, it will take a minute every time, while a simple Copy/Paste takes just a second.

## Usage

The TinyTOTP.jar file that is available as a release, is the result of the Maven build process that was run from NetBeans 12.5, although it is recommended, that you review the sources and build it yourself.

The program requires Java to run. It was built with version 1.8, but it also runs with 17.0.

## Setup

After starting it, a small window opens with two tab pages. On the first tab page named "TOTP" you can see the word "Secret?" in place where the generated password will be, indicating that the secret code from the authentication provider needs to be inserted, which can be done on the second tab page named "Settings". 

<img width="341" alt="TinyTOTP_Settings_tab" src="https://user-images.githubusercontent.com/23156196/138569220-6bbb363e-2b23-4637-80c8-3351b2bb354e.png">

The Base32 encoded "Secret" value is required to be typed in or copied from the text representation of the standard QR code's "secret" value. The resulting 6 digit authentication code starts getting generated every 30 seconds on the "TOTP" tab.

The value of the Prefix textbox will appear added to the beginning of the generated code. This is required by the Cisco AnyConnect client I use, which needs a user identification code (a PIN code, essentially) to prepend the TOTP. This way I can simply paste the whole string in one step.

The number of digits is usually 6, but as it can be parameterized in the generation algorithm, I made it configurable as well, maybe it will be useful for someone. The same is with the HmacSHA1/HmacSHA256/HmacSHA512 combo box for selecting the crypto algorithm.

If the "Autoclose" checkbox is selected, the program will automatically close itself at the end of the countdown period (if at least 5 seconds have elapsed since start).
If the "Autocopy" checkbox is selected, the generated value will automatically gets copied to the clipboard, so there's no need to let's say select the value by double clicking and pressing Ctrl+C.

All these settings together with the window position are getting saved in a small configuration file named TinyTOTP.txt into the same directory where the TinyTOTP.jar file resides in.  If there's a need to handle more than one password, you can copy the TinyTOTP.jar into several different directories, and put shortcuts to them on the Desktop.

## Good to know
Accurate (synchronized) system clock is necessary for proper password generation.

## Note on security
This program makes no effort to protect or hide the secret code. It gets saved in plain text in the TinyTOTP.txt file with the other configuration elements.

## Credits
The password generating code is from: <a href=\"https://tools.ietf.org/html/rfc6238#page-9\">https://tools.ietf.org/html/rfc6238#page-9</a>
The program uses the Apache commons-codec-1.15.jar for Base32/Hex conversion, and it is included in the TinyTOTP.jar.
