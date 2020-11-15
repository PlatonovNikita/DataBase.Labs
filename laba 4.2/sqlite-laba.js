var http = require("http");
var fs = require("fs");
var sqlite3 = require("sqlite3");
var db = new sqlite3.Database("visitor.db");

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
            let search = request.url.substring(concut + 8, concut2).toLocaleLowerCase();
            console.log(search);
            responce.writeHead(200, { "Content-Type": "text/plain; charset=utf-8" });
            db.all("SELECT * FROM Visitors WHERE name LIKE '%" + search + "%'"
                + " OR city LIKE '%" + search + "%'"
                + " OR position LIKE '%" + search + "%'", function (err, rows) {
                    for (let row of rows) {
                        responce.write(
                            "<tr>\n" +
                            "<td>" + row.name + "</td>\n" +
                            "<td>" + row.city + "</td>\n" +
                            "<td>" + row.position + "</td>\n" +
                            "</tr>\n");
                        console.log("<tr>\n" +
                            "<td>" + row.name + "</td>\n" +
                            "<td>" + row.city + "</td>\n" +
                            "<td>" + row.position + "</td>\n" +
                            "</tr>\n");
                    }
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