#!/bin/bash
export MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,address=8123,server=y,suspend=n"

cmd="mvn"

while getopts 'pd' opt; do
    case "$opt" in
        p)
            cmd="$cmd clean package" 
            ;;     
        d)
            cmd="$cmd liquibase:update"
            ;;
    esac
done

cmd="$cmd jetty:run"

echo "Running: $cmd"
$cmd

