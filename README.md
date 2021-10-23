# TinyTOTP

TinyTOTP is a small Java application for generating TOTPs (Time-Based One-Time Password) on Windows or Linux Desktop PC.
I wrote it for myself for the convenience of simply double click on the TinyTOTP jar file and have the generated 6 digit password code automatically copied to the clipboard, so that I can directly paste it in Cisco AnyConnect login's password field. It also works for the Microsoft Teams and Google accounts' 2 factor authorization.

The TinyTOTP.jar file is the result of the Maven build process that was run from NetBeans 12.5, although it is recommended, that you review the sources and build it yourself.
The program requires Java to run. It was built with version 1.8, but it also runs with 17.0.

After starting it, a small window opens with two tab pages. On the first tab page named "TOTP" you can see the word "Secret?" in place where the generated password will be, indicating that the secret code from the authentication provider needs to be inserted, which can be done on the second tab page named "Settings". 
The Base32 encoded "Secret" value is required to be typed or pasted, which is the default value, that can be copied from the standard QR code text representation's "secret" value. The 6 digit code starts getting generated every 30 seconds on the "TOTP" tab.

