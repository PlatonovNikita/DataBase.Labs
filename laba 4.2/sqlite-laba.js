var http = require("http");
var fs = require("fs");
var sqlite3 = require("sqlite3");
var db = new sqlite3.Database("visitors.db");

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
            responce.writeHead(200, { "Content-Type": "text/plain; charset=utf-8" });
            if (concut2 = -1) concut2 = undefined;
            let search = request.url.substring(concut + 8, concut2);
            let callback = function (err, rows) {
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
            };
            if (search) {
                console.log(search);
                const stmt = db.prepare("SELECT * FROM Visitors WHERE name = @search"
                    + " OR city = @search"
                    + " OR position = @search");
                stmt.all({ '@search': search }, callback);
            }
            else {
                db.all("SELECT * FROM Visitors", callback);
            }
        }
    }
    else {
        responce.statusCode = 404;
        responce.end("Not found");
    }

}).listen(3000);
console.log("Server running at http://localhost:3000/");