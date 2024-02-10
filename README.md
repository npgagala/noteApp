# Notes Application
A simple java spring boot application that saves, deletes, updates, and get individual or all notes via REST endpoint.

## How to run
Requirements:
1. Java 17

Instructions:
1. Download the jar file from the **Releases** page and save it to any directory.
2. Open the directory via cmd.
3. Run the jar file via cmd using ````java -jar notes-0.0.1-SNAPSHOT.jar````.

## How to use

Create new note:
````
curl --location 'http://localhost:8080/notes' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Sample Title",
    "body": "Sample Body"
}'
````

Get all notes:
````
curl --location 'http://localhost:8080/notes'
````

Get a specific note:
````
Format: curl --location 'http://localhost:8080/notes/{id}'
Sample: curl --location 'http://localhost:8080/notes/3'
````

Delete a specific note:
````
Format: curl --location --request DELETE 'http://localhost:8080/notes/{id}'
Sample: curl --location --request DELETE 'http://localhost:8080/notes/3'
````

Update a specific note:
````
curl --location --request PUT 'http://localhost:8080/notes/{id}' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Sample Old Title",
    "body": "Sample New Body"
}'
````

*Title and Body are required for creating and updating specific notes.