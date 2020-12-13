var cassandra = require('cassandra-driver');

const client = new cassandra.Client({
    contactPoints: ['127.0.0.1'],
    localDataCenter: 'datacenter1',
    keyspace: 'visitorkeyspace'
});

client.execute('CREATE TABLE visitors ( visitor_id int PRIMARY KEY, name text, city text, position text );');

client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (1, 'Nikita', 'NN', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (2, 'Viktorya', 'Tatarstan', 'Sinior')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (3, 'Olya', 'Kanoha', 'Genin')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (4, 'Dimitry', 'NN', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (5, 'Vanya', 'ReSHITiha', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (6, 'Sasha', 'NN', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (7, 'Egor', 'NN', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (8, 'Lena', 'Sorov', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (9, 'Chernov', 'NN', 'Teacher')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (10, 'Bagaev', 'NN', 'Teacher')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (11, 'Chubarova', 'NN', 'Teacher')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (12, 'Ulya', 'NN', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (13, 'Kolchik', 'Irak', 'Ignorer')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (14, 'Kirill', 'NN', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (15, 'Vladik', 'NN', 'Student')");
client.execute("INSERT INTO visitors (visitor_id, name, city, position) VALUES (16, 'Anton', 'NN', 'Teacher')");

