https://phoenixnap.com/kb/install-cassandra-on-windows (Устанавливаем cassandra на windows, версии критичны с другими не работает)

Запускаем сервер бд. В папке с cassandra в папке bin вводим следующую команду: cassandra

Открыв PowerShell вводим команды(один раз):

cqlsh

CREATE KEYSPACE visitorkeyspace WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 1};

Открыв ещё одну командную строку. В папке с лабой вводим следующие команды:

npm install http

npm install cassandra-driver

node cassandra.js (один раз)

node laba5.js
