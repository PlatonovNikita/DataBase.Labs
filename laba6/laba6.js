var http = require("http");
var fs = require("fs");
const MongoClient = require("mongodb").MongoClient;

const url = "mongodb://localhost:27017/";
const mongoClient = new MongoClient(url, { useNewUrlParser: true });

mongoClient.connect(function (err, client) {

    const db = client.db("visitorsdb");
    const collection = db.collection("visitors");

    http.createServer(function (request, responce) {
        if (request.url == "/" || request.url == "/index.html") {
            fs.readFile("index.html", function (error, data) {
                if (error) {
                    responce.statusCode = 500;
                    responce.end("ERROR!");
                }
                else {
                    responce.end(data);
                }
            });
        }
        else if (request.url.indexOf("/visitors") > -1) {
            let concut = request.url.indexOf("?search=");
            let concut2 = request.url.indexOf("&");
            if (concut > -1) {
                if (concut2 = -1) concut2 = undefined;
                let search = request.url.substring(concut + 8, concut2);
                console.log(search);
                responce.writeHead(200, { "Content-Type": "text/plain; charset=utf-8" });
                let visitorSearch = { position: search };
                if (!search) visitorSearch = undefined;
                collection.find(visitorSearch)
                    .toArray(function (err, rows) {
                        let visitors = [];
                        for (let row of rows) {
                            let visitor = {};
                            visitor.name = row.name;
                            visitor.city = row.city;
                            visitor.position = row.position;
                            visitors.push(visitor);
                        }
                        responce.write(JSON.stringify(visitors));
                        responce.end();
                    });
            }
        }
        else {
            responce.statusCode = 404;
            responce.end("Not found");
        }

    }).listen(3000);
    console.log("Server running at http://localhost:3000/");

});