# Notes Application
A simple java spring boot application that saves, deletes, updates, and get individual or all notes via REST endpoint.

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