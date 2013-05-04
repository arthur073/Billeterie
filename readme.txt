Identifiants site web : Login - password

Admin :  root - root
Client : user - toto

Sources à modifier :

Dans src/main/webapp/META-INF/context.xml :

   *Changer PORT, BDNAME, USERNAME et PASSWORD

   => exemple de configuration valide sous Ensibm :

   <?xml version="1.0" encoding="UTF-8"?>
   <Context antiJARLocking="true" path="/Billeterie">
      <Resource 
         auth="Container" 
         driverClassName="com.mysql.jdbc.Driver" 
         maxActive="10" 
         maxIdle="3" 
         maxWait="10000" 
         name="jdbc/billeterie" 
         password="PASSWORD" 
         type="javax.sql.DataSource" 
         url="jdbc:mysql://localhost:PORT/BDNAME?profileSQL=true" 
         username="USERNAME"
         factory="org.apache.commons.dbcp.BasicDataSourceFactory"
      />
   </Context> 

Dans pom.xml :

   * Changer username et password pour le serveur tomcat

