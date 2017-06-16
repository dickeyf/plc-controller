# Overview

A microservice that exposes a REST Interface to write and read from the
registers of a PLC (Click PLC).

# Configuration

Configured via Spring-boot's externalized Configuration.

These are the configurables values :
 
|    Name     |          Description         | Default Value |
| ----------- | ---------------------------- | ------------- |
| plc.address | PLC IP Address to connect to | 192.168.0.9   |
| plc.port    | PLC Modbus TCP Port          | 502           |

