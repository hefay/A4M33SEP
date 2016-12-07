# UserChange &ndash; instalační příručka

## Požadavky
 * Gradle
 * Java 8
 * MySQL

## Configurace
V souboru `src/main/resources/application.properties` se provádní základní
konfigurace aplikace.

 * `customer-database.wsdl`  
 URL wsdl pro databázi klientů
 * `spring.datasource.username`  
 Uživatelské jméno pro připojení k databázi
 * `spring.datasource.password`  
 Heslo pro připojení k databázi
 * `spring.datasource.url`  
 URL databáze

## Sestavení a spuštění
Aplikaci sestavíte a spustíte příkazem `gradle bootRun`

## Poznámky k verzi
Tato verze není z časových důvodů kompletní!

Není dostupné zobrazení požadavků, jejich přenos na hlavní server
a správa uživatelů systému.
