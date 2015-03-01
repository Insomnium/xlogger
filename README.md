# xlogger

### build & deploy
1. Install postgresql
2. Install and setup tomcat
    -  Add following resource link into ```context.xml``` at your tomcat
   
    ```
    <ResourceLink name="jdbc/xlogger" global="jdbc/xlogger" auth="Container" type="javax.sql.DataSource" />
    ``` 
    - Describe _xlogger_ resource in ```server.xml``` file:
    ```
    <Resource name="jdbc/xlogger"
              auth="Container"
              type="javax.sql.DataSource"
              driverClassName="org.postgresql.Driver"
              url="jdbc:postgresql://127.0.0.1:5432/xlogger"
              username="insomnium"
              password="insomnium"
              maxActive="10"
              maxIdle="-1"
              maxWait="-1"
              removeAbandoned="true"
              removeAbandonedTimeout="600"
              logAbandoned="true"/>
    ```

    - Just as a reminder - it would be useful to add this setting into ```tomcat-users.xml``` file:

    ```
    <user username="tomcat" password="tomcat" roles="manager-gui,admin,admin-gui"/>
    ```
3. Actualize ```sql/liquibase.config``` properties file

4. Execue ```scripts/prepareServer.sh``` to create database schema

    OR

    produce following steps:
    - Execute ```create-db.sh``` scipt from 'scripts' folder
    - Execute ```mvn liquibase:update``` to update database
5. Build application with ```mvn package``` and copy *war* file into 'webapps' Tomcat directory
6. Run tomcat and get it worked :)

### Debugging with jetty

Following command will run ready out-of-box jetty with 8123 debug port. You'll get working application immediately at 8085 port.
```sh
./start_debug.sh -pd