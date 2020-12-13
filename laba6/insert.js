const MongoClient = require("mongodb").MongoClient;

const url = "mongodb://localhost:27017/";
const mongoClient = new MongoClient(url, { useNewUrlParser: true });

mongoClient.connect(function (err, client) {

    const db = client.db("visitorsdb");
    const collection = db.collection("visitors");
    let users = [];

    users.push({ name: "Nikita", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Anton", city: "Nizhniy Novgorod", position: "Teacher" });
    users.push({ name: "Viktorya", city: "Tatarstan", position: "Sinior" });
    users.push({ name: "Olya", city: "Kanoha", position: "Genin" });
    users.push({ name: "Dimitry", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Vanya", city: "ReSHITiha", position: "Student" });
    users.push({ name: "Vadim", city: "Bor", position: "Student" });
    users.push({ name: "Sasha", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Egor", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Lena", city: "Sorov", position: "Student" });
    users.push({ name: "Chernov", city: "Nizhniy Novgorod", position: "Teacher" });
    users.push({ name: "Bagaev", city: "Nizhniy Novgorod", position: "Teacher" });
    users.push({ name: "Ulya", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Kolchik", city: "Irak", position: "Ignorer" });
    users.push({ name: "Ilya", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Kolya", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Chubarova", city: "Nizhniy Novgorod", position: "Teacher" });
    users.push({ name: "Kirill", city: "Nizhniy Novgorod", position: "Student" });
    users.push({ name: "Vladik", city: "Nizhniy Novgorod", position: "Student" });

    collection.insertMany(users, function (err, result) {

        if (err) {
            return console.log(err);
        }
        console.log(result.ops);
        client.close();
    });
});

