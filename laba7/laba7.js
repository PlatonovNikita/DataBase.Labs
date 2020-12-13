const neo4j = require('neo4j-driver');

var uri = "bolt://localhost";
var user = "neo4j";
var password = "neo4jj";

const driver = neo4j.driver(uri, neo4j.auth.basic(user, password));
const session = driver.session();

var http = require("http");
var fs = require("fs");

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
            let query = "MATCH (v:Visitor)"
            if (search) query += " WHERE v.position = '" + search + "'";
            query += " RETURN v;"
            session.run(query)
                .then(result => {
                    let visitors = [];
                    for (let record of result.records) {
                        let visitor = {};
                        visitor.name = record.get(0).properties.name;
                        visitor.city = record.get(0).properties.city;
                        visitor.position = record.get(0).properties.position;
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