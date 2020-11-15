var sqlite3 = require("sqlite3").verbose();

var db = new sqlite3.Database("visitors.db");

db.run("CREATE TABLE Visitors (name TEXT, city TEXT, position TEXT)");

var stmt = db.prepare("INSERT INTO Visitors VALUES (?,?,?)");
stmt.run("Nikita", "Nizhniy Novgorod", "Student");
stmt.run("Anton", "Nizhniy Novgorod", "Teacher");
stmt.run("Viktorya", "Tatarstan", "Sinior");
stmt.run("Olya", "Kanoha", "Genin");
stmt.run("Dimitry", "Nizhniy Novgorod", "Student");
stmt.run("Vanya", "ReSHITiha", "Student");
stmt.run("Vadim", "Bor", "Student");
stmt.run("Sasha", "Nizhniy Novgorod", "Student");
stmt.run("Egor", "Nizhniy Novgorod", "Student");
stmt.run("Lena", "Sorov", "Student");
stmt.run("Chernov", "Nizhniy Novgorod", "Teacher");
stmt.run("Bagaev", "Nizhniy Novgorod", "Teacher");
stmt.run("Ulya", "Nizhniy Novgorod", "Student");
stmt.run("Kolchik", "Irak", "Ignorer");
stmt.run("Ilya", "Nizhniy Novgorod", "Student");
stmt.run("Kolya", "Nizhniy Novgorod", "Student");
stmt.run("Chubarova", "Nizhniy Novgorod", "Teacher");
stmt.run("Kirill", "Nizhniy Novgorod", "Student");
stmt.run("Vladik", "Nizhniy Novgorod", "Student");
stmt.finalize();

db.close();

console.log("created db: visitors.db")