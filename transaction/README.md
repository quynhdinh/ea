# Run ./scripts/db.sh to create the database

You need to set up the database by running the script:
```bash
./scripts/db.sh
```

Then you can run the application using:
```bash
mvn spring-boot:run
```

All the screenshots are put in `screenshot` folder.

I created an api to save a product, returning the product id. Write that product to stdout. Then save that product with a new price, write to stdout again where 
The sample output is as follows
```
Product created id: 36
ID: 36, Name: iPhone 17, Price: $2000.0, Version: 0
ID: 36, Name: iPhone 17, Price: $3000.0, Version: 1
Optimistic locking failure in thread 2!
```