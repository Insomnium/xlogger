#!/bin/bash

[ "$(whoami)" != "root" ] && exec sudo -- "$0" "$@"

db_name=xlogger
root_user=insomnium
root_user_pwd=insomnium
pg_user=postgres

db_exists=$(psql -U $pg_user -l | grep $db_name | wc -l) 
if [ $db_exists == 0 ]; then
    createdb -U $pg_user --encoding=UTF8 $db_name
    echo "DB $db_name  successfully created"
else
    echo "DB $db_name already exists"
fi

user_exists=$(psql -U $pg_user -tAc "select count(1) from pg_roles where rolname='$root_user'")
if [ $user_exists == 0 ]; then
    createuser -U $pg_user  -s -d -r $root_user
    psql -U $pg_user -c "alter user $root_user password '$root_user_pwd'" template1
    echo "Root user '$root_user' successfully created"
else
    echo "Root user already exists"
fi

