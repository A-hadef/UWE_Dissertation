# UWE_Dissertation
Insurance User interface data capture

A Spring Data Application backed by MongoDB

### Requirements

Maven, Java 1.8

[MongoDB](https://docs.mongodb.com/manual/installation/)

### Usage

Install MongoDB and follow the instructions to start local hosted database

You'll need to create a directory /data/db/ anywhere you like and point mongo to this location

e.g. on mac:

`mongod --dbpath ./data/db/`

Then run the application, from another terminal (in the project directory) enter:

`mvn spring-boot:run`

The application will automatically search for a mongodb instance on localhost

To see the application writing to the database open another terminal and start the mongo shell

`mongo --shell`

Once you've created a customer in the application you should be able to see mongo has created a new collection, in the mongo shell type:

`show collections`

See the customers you have added to the collection with:

`db.customer.find()`

If you want to wipe the database and start again:

`db.dropDatabase()`

Currently this only supports the customers, adding policies breaks everything in a violent explosion, try it, it's fun :)