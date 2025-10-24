#!/bin/bash
# Script to modify pg_hba.conf to allow trust authentication for all connections
echo "Modifying pg_hba.conf to allow trust authentication..."

# Replace the last line (scram-sha-256) with trust for all host connections
sed -i 's/host all all all scram-sha-256/host all all all trust/' /var/lib/postgresql/data/pg_hba.conf

# Reload PostgreSQL configuration
pg_ctl reload -D /var/lib/postgresql/data

echo "PostgreSQL configuration updated to use trust authentication for all connections."