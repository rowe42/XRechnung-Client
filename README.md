## Beispiel zum Empfang einer XRechnung mit selbst ersteller WSDL


### Starten:
```
mvn clean spring-boot:run
```

### Aufrufen:
```
curl --header "content-type: text/xml" -d @invoice.xml http://localhost:8080/ws
```

### WSDL:
```
http://localhost:8080/ws/invoices.wsdl
```
