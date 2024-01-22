# EKartaPacjenta
Realizacja e-kart gorączkowych dla odziału szpitalnego z czytaniem kodów kreskowych i znaczników NFC w celu identyfikacji pacjentów.

## Moduły
* `/server` zawiera serwer obsługujacy aplikację mobilną

* `/shared` kod współdzielony pomiędzy serwerem a aplikacją mobilną

* `/composeApp` aplikacja mobilna

## Uruchomienie
Projekt jest budowany za pomocą narzędzia gradle. 
W celu testowania aplikacji w sieci lokalnej należy ustawić stałą **DOMAIN** w pliku 
*composeApp/src/androidMain/kotlin/pl/pw/ekartapacjenta/logic/NetworkManager.kt* 
na odpowiadającej domenie lub adresie serwera. 
Aplikację mobilną zbudować używając skryptu gradlew, 
wykonując następującą komendę: *./gradlew composeApp:build*. 
Zbudowana aplikacja będzie w folderze bulld/output. 
Serwer można uruchomić za pomocą komendy *./gradlew serwer:run*.
