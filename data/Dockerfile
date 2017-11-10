FROM postgres:9.5
ENV POSTGRES_USER docker
ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB docker
ADD CreateDB.sql /docker-entrypoint-initdb.d/
