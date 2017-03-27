#!/bin/bash
cd Dropbox/Practica/ASW/participationSystem3b/kafka_2.12-0.10.2.0/
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic exampleTopic --from-beginning
