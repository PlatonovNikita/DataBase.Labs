const neo4j = require('neo4j-driver');

var uri = "bolt://localhost";
var user = "neo4j";
var password = "neo4jj";

const driver = neo4j.driver(uri, neo4j.auth.basic(user, password));
const session = driver.session();

session.run("CREATE (a:Visitor { name: 'Nikita', city: 'Nizhniy Novgorod', position: 'Student'}), "
    + "(b:Visitor { name: 'Anton', city: 'Nizhniy Novgorod', position: 'Teacher' }), "
    + `(c:Visitor { name: 'Viktorya', city: 'Tatarstan', position: 'Sinior' }), `
    + "(e:Visitor { name: 'Olya', city: 'Kanoha', position: 'Genin' }), "
    + "(f:Visitor { name: 'Dimitry', city: 'Nizhniy Novgorod', position: 'Student' }), "
    + "(g:Visitor { name: 'Vanya', city: 'ReSHITiha', position: 'Student' }), "
    + "(h:Visitor { name: 'Vadim', city: 'Bor', position: 'Student' }), "
    + "(i:Visitor { name: 'Sasha', city: 'Nizhniy Novgorod', position: 'Student' }), "
    + "(j:Visitor { name: 'Egor', city: 'Nizhniy Novgorod', position: 'Student' }), "
    + "(k:Visitor { name: 'Lena', city: 'Sorov', position: 'Student' }), "
    + "(l:Visitor { name: 'Chernov', city: 'Nizhniy Novgorod', position: 'Teacher' }), "
    + "(m:Visitor { name: 'Bagaev', city: 'Nizhniy Novgorod', position: 'Teacher' }), "
    + "(n:Visitor { name: 'Ulya', city: 'Nizhniy Novgorod', position: 'Student' }), "
    + "(o:Visitor { name: 'Kolchik', city: 'Irak', position: 'Ignorer' }), "
    + "(p:Visitor { name: 'Ilya', city: 'Nizhniy Novgorod', position: 'Student' }), "
    + "(q:Visitor { name: 'Kolya', city: 'Nizhniy Novgorod', position: 'Student' }), "
    + "(r:Visitor { name: 'Chubarova', city: 'Nizhniy Novgorod', position: 'Teacher' }), "
    + "(s:Visitor { name: 'Kirill', city: 'Nizhniy Novgorod', position: 'Student' }), "
    + "(t:Visitor { name: 'Vladik', city: 'Nizhniy Novgorod', position: 'Student' });"
).then(result => { session.close(); driver.close(); });