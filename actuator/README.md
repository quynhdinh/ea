Run the following command to start Prometheus with the provided configuration:
```bash
docker run -d --name prometheus -p 9090:9090 -v src/main/resources/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
```
And run the following command to start Grafana:
```bash
docker run -d --name grafana -p 3000:3000 grafana/grafana
```
Run the following command to start the application:
```bash
mvn spring-boot:run
```
Enter the following URL in your browser to access the Grafana dashboard: `http://localhost:3000`