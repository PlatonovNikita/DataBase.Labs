package com.company;

import java.sql.*;

public class Main {

    public static final String DB_URL = "jdbc:h2:/c:/JavaPrj/ConectToDB/db/VisitorDb";
    public static final String DB_Driver = "org.h2.Driver";

    public static void main(String[] args) {
        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(DB_URL);
            System.out.println("Соединение с СУБД выполнено.");
            Statement st = connection.createStatement();

            try { //Пробует создать новую таблицу
                st.execute("CREATE TABLE visitors(" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "city VARCHAR(255) NOT NULL," +
                        "position VARCHAR(255) NOT NULL" +
                        ");");
            } catch (SQLException e){}

            if(!st.executeQuery("SELECT * from visitors").next()){ //Если нет даже одно элемнта - заполняем таблицу
                st.execute("INSERT into visitors values (1, 'Nikita', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (2, 'Viktorya', 'Tatarstan', 'Sinior');");
                st.execute("INSERT into visitors values (3, 'Anton', 'Nizhniy Novgorod', 'Teacher');");
                st.execute("INSERT into visitors values (4, 'Olya', 'Kanoha', 'Genin');");
                st.execute("INSERT into visitors values (5, 'Dimitry', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (6, 'Vanya', 'ReSHITiha', 'Student');");
                st.execute("INSERT into visitors values (7, 'Vadim', 'Bor', 'Student');");
                st.execute("INSERT into visitors values (8, 'Sasha', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (9, 'Egor', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (10, 'Lena', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (11, 'Chernov', 'Nizhniy Novgorod', 'Teacher');");
                st.execute("INSERT into visitors values (12, 'Bagaev', 'Nizhniy Novgorod', 'Teacher');");
                st.execute("INSERT into visitors values (13, 'Ulya', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (14, 'Kolchik', 'Irak', 'Ignorer');");
                st.execute("INSERT into visitors values (15, 'Ilya', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (16, 'Kolya', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (17, 'Chubarova', 'Nizhniy Novgorod', 'Teacher');");
                st.execute("INSERT into visitors values (18, 'Dimitry', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (19, 'Kirill', 'Nizhniy Novgorod', 'Student');");
                st.execute("INSERT into visitors values (20, 'Vladik', 'Nizhniy Novgorod', 'Student');");
            }

            ResultSet rs = st.executeQuery("SELECT * from visitors WHERE position = 'Teacher '");

            while (rs.next()){
                System.out.println(
                        "name:" + rs.getString("name") + "\n   city:" + rs.getString("city")
                                + " position:" + rs.getString("position")
                );
            }

            connection.close();
            System.out.println("Отключение от СУБД выполнено.");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        }

    }
}
