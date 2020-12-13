var http = require("http");
var fs = require("fs");
var cassandra = require('cassandra-driver');

const client = new cassandra.Client({
    contactPoints: ['127.0.0.1'],
    localDataCenter: 'datacenter1',
    keyspace: 'visitorkeyspace'
});

//client.execute("CREATE INDEX ON visitors (city)");

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
            let query = "SELECT visitor_id, name, city, position FROM visitors";
            if (search) {
                query += " WHERE city = '" + search + "'";
            }
            client.execute(query,
                function (err, result) {
                    if (!err) {
                        if (result.rows.length > 0) {
                            result.rows.sort(function (a, d) {
                                if (a.visitor_id > d.visitor_id)
                                    return 1;

                                if (a.visitor_id < d.visitor_id)
                                    return -1;

                                return 0;
                            });
                            let visitors = [];
                            for (let row of result.rows) {
                                let visitor = {};
                                visitor.name = row.name;
                                visitor.city = row.city;
                                visitor.position = row.position;
                                visitors.push(visitor);
                            }
                            responce.write(JSON.stringify(visitors));
                            responce.end();
                        }
                        else {
                            console.log('No results');
                        }
                    } else {
                        console.log(err);
                    }
                });
        }
    }
    else {
        responce.statusCode = 404;
        responce.end("Not found");
    }

}).listen(3000);
console.log("Server running at http://localhost:3000/");

