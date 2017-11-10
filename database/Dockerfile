FROM postgres:latest
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD pg123
ENV POSTGRES_DB imagehosting
ADD ./sql-scripts/ /docker-entrypoint-initdb.d/
EXPOSE 5433

