#!/bin/bash

function timeout() { perl -e 'alarm shift; exec @ARGV' "$@"; }

JAR_PATH=/Users/guilhermemorum/Documents/agente/out/artifacts/agente_jar/agente.jar
CRAWLER_DIRECTORY=/Users/guilhermemorum/Documents/crawler/demo/olx
DATA_DIRECTORY=/Users/guilhermemorum/Documents/crawler/demo/olx/data/electronics
SPIDER_NAME=electronics;
SPIDER_TIMEOUT=30s;

while true; do
	cd "$CRAWLER_DIRECTORY";
	timeout "$SPIDER_TIMEOUT" scrapy crawl "$SPIDER_NAME";
	timeout java -jar "$JAR_PATH" "$DATA_DIRECTORY";
	sleep 300s;
done